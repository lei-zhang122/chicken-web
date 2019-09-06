package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.GoodDetail;
import com.chicken.model.GoodInfo;
import com.chicken.service.GoodDetailService;
import com.chicken.service.GoodInfoService;
import com.chicken.service.RedisService;
import com.chicken.util.CallResult;
import com.chicken.util.ContantUtil;
import com.chicken.vo.GoodInfoRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhanglei
 * @date 2019-09-01 21:54
 */
@Controller
@RequestMapping("/goodStock")
public class GoodStockController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodInfoService goodInfoService;

    @Autowired
    GoodDetailService goodDetailService;

    @Autowired
    RedisService redisService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/goodStockPage")
    @RequiresPermissions("goodStockPage")
    public Object goodStockPage(Model model) {

        GoodInfoRequest info = new GoodInfoRequest();
        PageInfo<GoodInfo> result = this.goodInfoService.selectByGoodInfo(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodType", info.getGoodType());
        model.addAttribute("goodName", info.getGoodName());
        model.addAttribute("goodStatus", "0");

        return "goodinfo/goodStockList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodStockPageList", method = RequestMethod.POST)
    @RequiresPermissions("goodStockPageList")
    public Object goodStockPageList(@ModelAttribute GoodInfoRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<GoodInfo> result = this.goodInfoService.selectByGoodInfo(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodStatus", StringUtils.isBlank(info.getGoodStatus()) ? "0" : info.getGoodStatus());
        model.addAttribute("goodType", info.getGoodType());
        model.addAttribute("goodName", info.getGoodName());

        return "goodinfo/goodStockList";
    }


    /**
     * 修改商品状态
     *
     * @param id
     * @return
     */
    @GetMapping("/goodStockEditGoodStatus/{id}/{goodStatus}")
    public Object goodInfoEdit(@PathVariable Integer id, @PathVariable String goodStatus) {

        this.goodInfoService.updateGoodStatusById(id, goodStatus);
        logger.info("商品信息，修改商品为{}状态，商品id{}", goodStatus, id);

        insertCacheBystatus(id, goodStatus);

        return "redirect:/goodStock/goodStockPage";
    }

    private void insertCacheBystatus(Integer id, String goodStatus) {
        redisService.set("good:status:" + id, goodStatus);
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/goodStockEdit/{id}")
    public Object goodInfoEdit(Model model, @PathVariable Integer id) {
        GoodInfo goodInfo = this.goodInfoService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("goodInfo", goodInfo);
        model.addAttribute("createTime", sdf.format(goodInfo.getCreateTime()));

        return "goodinfo/goodStockAdd";
    }

    /**
     * 添加/修改
     *
     * @return
     */
    @PostMapping(value = "/updateGoodNum")
    public Object updateGoodNum(@RequestParam String goodNum, @RequestParam String id) {

        if (StringUtils.isBlank(goodNum) || StringUtils.isBlank(id)) {
            return "redirect:/goodStock/goodStockPage";
        }

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail("未登录");
        }

        //查询原始数据
        GoodInfo goodInfoOld = this.goodInfoService.selectByPrimaryKey(Integer.valueOf(id));

        goodInfoService.updateGoodNumById(Integer.valueOf(id), Integer.valueOf(goodNum));

        logger.info("商品信息，原库存数{},修改后库存数{}，商品id{}", goodInfoOld.getGoodNum(), goodInfoOld.getGoodNum() - Integer.valueOf(goodNum), id);

        Integer val = 0;
        if (goodInfoOld.getGoodNum() > Integer.valueOf(goodNum)) {
            val = (goodInfoOld.getGoodNum() - Integer.valueOf(goodNum)) * -1;
        } else {
            val = Integer.valueOf(goodNum) - goodInfoOld.getGoodNum();
        }
        insertGoodDetail(Integer.valueOf(id), "原库存数：" + goodInfoOld.getGoodNum() + "，库存数量修改为：" + goodNum, val);

        insertCache(Integer.valueOf(id), val);

        return "redirect:/goodStock/goodStockPage";
    }

    /**
     * 插入到缓存
     *
     * @param id
     * @param num
     */
    private void insertCache(Integer id, Integer num) {
        redisService.set("good:id:" + id, num);
        logger.info("商品{}，加入到缓存，库存数量{}", id, num);
    }


    /**
     * 插入到记录表
     *
     * @param goodId
     * @param remark
     */
    private void insertGoodDetail(Integer goodId, String remark, Integer goodNum) {
        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setCreateTime(new Date());
        goodDetail.setRemark(remark);
        goodDetail.setGoodNum(goodNum);
        goodDetail.setGoodId(goodId);
        goodDetail.setCreateUser(getUserSession().getId());
        this.goodDetailService.insert(goodDetail);
        logger.info("商品信息成功插入到记录表，商品ID={}", goodId);
    }
}
