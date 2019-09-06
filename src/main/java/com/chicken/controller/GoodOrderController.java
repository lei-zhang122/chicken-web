package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.*;
import com.chicken.service.*;
import com.chicken.util.*;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.GoodInfoRequest;
import com.chicken.vo.GoodOrderRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhanglei
 * @date 2019-09-01 15:12
 */
@Controller
@RequestMapping("/goodOrder")
public class GoodOrderController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodOrderService goodOrderService;

    @Autowired
    GoodInfoService goodInfoService;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    UserService userService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/goodOrderPage")
    @RequiresPermissions("goodOrderPage")
    public Object goodOrderPage(Model model) {

        GoodOrderRequest info = new GoodOrderRequest();
        PageInfo<Map> result = this.goodOrderService.selectByGoodOrder(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);
        return "goodorder/goodOrderList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodOrderPageList", method = RequestMethod.POST)
    @RequiresPermissions("goodOrderPageList")
    public Object goodOrderPageList(@ModelAttribute GoodOrderRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.goodOrderService.selectByGoodOrder(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);
        return "goodorder/goodOrderList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, GoodOrderRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodId", StringUtils.isBlank(info.getGoodId()) ? 0 : Integer.valueOf(info.getGoodId()));
        model.addAttribute("nickName", info.getNickName());
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("exchangeStatus", StringUtils.isBlank(info.getExchangeStatus()) ? "0" : info.getExchangeStatus());
        model.addAttribute("orderNum", info.getOrderNum());
        model.addAttribute("expressNum", info.getExpressNum());
        model.addAttribute("expressName", info.getExpressName());

        List<GoodInfo> selectAll = this.goodInfoService.selectAll();
        model.addAttribute("goodInfoList", selectAll);
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/goodOrderEdit/{id}")
    public Object goodInfoEdit(Model model, @PathVariable Integer id) {
        GoodOrder goodOrder = this.goodOrderService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("goodOrder", goodOrder);
        model.addAttribute("createTime", sdf.format(goodOrder.getCreateTime()));
        model.addAttribute("exchangeTime", sdf.format(goodOrder.getExchangeTime()));

        if(null != goodOrder.getModifyTime()){
            model.addAttribute("modifyTime", sdf.format(goodOrder.getModifyTime()));
        }

        if(null != goodOrder.getModifyUser()){
            User u = this.userService.selectByPrimaryKey(goodOrder.getUserId());
            model.addAttribute("modifyUser", u.getUserName());
        }

        WechatUser wechatUser = this.wechatUserService.selectByPrimaryKey(goodOrder.getGoodId());
        model.addAttribute("nickName", wechatUser.getNickName());

        List<GoodInfo> selectAll = this.goodInfoService.selectAll();
        model.addAttribute("goodInfoList", selectAll);
        return "goodOrder/goodOrderAdd";
    }

    /**
     * 添加/修改
     *
     * @return
     */
    @PostMapping(value = "/insertGoodOrder")
    @ResponseBody
    public Object insertGoodOrder(@RequestBody GoodOrderRequest info) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail("未登录");
        }

        /**
         * 校验用户信息
         */
        String errorMsg = GoodOrderUtils.checkGoodOrderInsertRequest(info);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("商品订单信息，数据校验失败，传入参数{}", info.toString());
            return CallResult.fail(errorMsg);
        }

        /**
         * 封装对象
         */
        GoodOrder goodOrder = new GoodOrder();
        BeanUtils.copyProperties(info, goodOrder);
        goodOrder.setModifyTime(new Date());
        goodOrder.setModifyUser(getUserSession().getId());
        goodOrder.setGoodId(Integer.valueOf(info.getGoodId()));
        goodOrder.setUserId(Integer.valueOf(info.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(info.getId())) {
            goodOrder.setExchangeTime(sdf.parse(info.getExchangeTime()));
            goodOrder.setId(Integer.valueOf(info.getId()));
            goodOrder.setCreateTime(sdf.parse(info.getCreateTime()));
            this.goodOrderService.updateByPrimaryKey(goodOrder);
            logger.info("商品订单信息，修改内容，内容ID，{}", goodOrder.getId());
        }

        return CallResult.success();
    }
}
