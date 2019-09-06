package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.GoodDetail;
import com.chicken.model.GoodInfo;
import com.chicken.service.GoodDetailService;
import com.chicken.service.GoodInfoService;
import com.chicken.service.RedisService;
import com.chicken.util.*;
import com.chicken.vo.GoodInfoRequest;
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
import java.util.UUID;

/**
 * @author zhanglei
 * @date 2019-09-01 15:12
 */
@Controller
@RequestMapping("/goodInfo")
public class GoodInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodInfoService goodInfoService;

    @Autowired
    GoodDetailService goodDetailService;

    @Autowired
    RedisService redisService;

    private final ResourceLoader resourceLoader;

    @Value("${web.upload-path}")
    private String path;

    @Autowired
    public GoodInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/goodInfoPage")
    @RequiresPermissions("goodInfoPage")
    public Object goodInfoPage(Model model) {

        GoodInfoRequest info = new GoodInfoRequest();
        PageInfo<GoodInfo> result = this.goodInfoService.selectByGoodInfo(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodType", info.getGoodType());
        model.addAttribute("goodName", info.getGoodName());
        model.addAttribute("goodStatus", "0");

        return "goodinfo/goodInfoList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodInfoPageList", method = RequestMethod.POST)
    @RequiresPermissions("goodInfoPageList")
    public Object goodInfoPageList(@ModelAttribute GoodInfoRequest info, Model model) {

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

        return "goodinfo/goodInfoList";
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodInfoAdd")
    public Object addGoodInfo(Model model) {

        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setStatus("0");
        model.addAttribute("goodInfo", goodInfo);

        return "goodinfo/goodInfoAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/goodInfoEdit/{id}")
    public Object goodInfoEdit(Model model, @PathVariable Integer id) {
        GoodInfo goodInfo = this.goodInfoService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("goodInfo", goodInfo);
        model.addAttribute("createTime", sdf.format(goodInfo.getCreateTime()));

        return "goodinfo/goodInfoAdd";
    }

    /**
     * 添加/修改
     *
     * @return
     */
    @PostMapping(value = "/insertGoodInfo")
    public Object insertGoodInfo(@RequestParam("fileName") MultipartFile file, @RequestParam String goodType,
                                 @RequestParam String goodName, @RequestParam String goodNum,
                                 @RequestParam String goodPrice, @RequestParam String goodVirtual,
                                 @RequestParam String goodDownVirtual, @RequestParam String goodDetail,
                                 @RequestParam String status, @RequestParam String createUser,
                                 @RequestParam String createTime, @RequestParam String id,
                                 @RequestParam String goodStatus,
                                 Model model) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail("未登录");
        }

        GoodInfoRequest goodInfoRequest = new GoodInfoRequest();
        goodInfoRequest.setCreateTime(createTime);
        goodInfoRequest.setCreateUser(createUser);
        goodInfoRequest.setGoodDetail(goodDetail);
        goodInfoRequest.setGoodDownVirtual(goodDownVirtual);
        goodInfoRequest.setGoodName(goodName);
        goodInfoRequest.setGoodNum(goodNum);
        goodInfoRequest.setGoodPrice(goodPrice);
        goodInfoRequest.setGoodType(goodType);
        goodInfoRequest.setGoodVirtual(goodVirtual);
        goodInfoRequest.setId(id);
        goodInfoRequest.setStatus(status);
        goodInfoRequest.setGoodStatus(goodStatus);

        /**
         * 校验用户信息
         */
        String errorMsg = GoodInfoUtils.checkGoodInfoInsertRequest(goodInfoRequest);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("商品信息，数据校验失败，传入参数{}", goodInfoRequest.toString());
            return CallResult.fail(errorMsg);
        }

        /**
         * 上传文件
         */
        String imgName = null;
        if (!file.isEmpty() && !StringUtils.isBlank(file.getOriginalFilename())) {
            imgName = uploadMethod(file);
        }

        /**
         * 封装对象
         */
        GoodInfo goodInfo = new GoodInfo();
        BeanUtils.copyProperties(goodInfoRequest, goodInfo);
        goodInfo.setModifyTime(new Date());
        goodInfo.setGoodDownVirtual(Integer.valueOf(goodInfoRequest.getGoodDownVirtual()));
        goodInfo.setGoodVirtual(Integer.valueOf(goodInfoRequest.getGoodVirtual()));
        goodInfo.setGoodNum(Integer.valueOf(goodInfoRequest.getGoodNum()));
        goodInfo.setGoodPrice(Double.valueOf(goodInfoRequest.getGoodPrice()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        goodInfo.setGoodImg(imgName);
        if (!StringUtils.isEmpty(goodInfoRequest.getId())) {

            GoodInfo goodInfoOld = this.goodInfoService.selectByPrimaryKey(Integer.valueOf(goodInfoRequest.getId()));

            goodInfo.setId(Integer.valueOf(goodInfoRequest.getId()));
            goodInfo.setCreateTime(sdf.parse(goodInfoRequest.getCreateTime()));
            goodInfo.setCreateUser(Integer.valueOf(goodInfoRequest.getCreateUser()));
            this.goodInfoService.updateByPrimaryKey(goodInfo);
            logger.info("商品信息，修改内容，内容ID，{}", goodInfo.getId());

            if (goodInfoOld.getGoodNum() - goodInfo.getGoodNum() != 0) {

                Integer num = 0;
                if (goodInfoOld.getGoodNum() > Integer.valueOf(goodNum)) {
                    num = (goodInfoOld.getGoodNum() - Integer.valueOf(goodNum)) * -1;
                } else {
                    num = Integer.valueOf(goodNum) - goodInfoOld.getGoodNum();
                }

                insertGoodDetail(goodInfo.getId(), "修改商品" + goodInfo.getGoodName() + "，商品数量从" +
                        goodInfoOld.getGoodNum() + "修改为" + goodInfo.getGoodNum(), num);

                insertCache(goodInfo.getId(), num);
            }
        } else {
            goodInfo.setCreateTime(new Date());
            //未上架
            goodInfo.setGoodStatus("1");
            goodInfo.setCreateUser(getUserSession().getId());
            goodInfoService.insert(goodInfo);
            logger.info("商品信息，添加内容，内容={}", goodInfoRequest.toString());

            insertGoodDetail(goodInfo.getId(), "添加商品" + goodInfo.getGoodName() + "，商品数量" + goodInfo.getGoodNum(), goodInfo.getGoodNum());

            insertCache(goodInfo.getId(), goodInfo.getGoodNum());

        }

        return "redirect:/goodInfo/goodInfoPage";
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
        goodDetail.setGoodId(goodId);
        goodDetail.setRemark(remark);
        goodDetail.setGoodNum(goodNum);
        goodDetail.setCreateUser(getUserSession().getId());
        this.goodDetailService.insert(goodDetail);
        logger.info("商品信息成功插入到记录表，商品ID={}", goodId);
    }

    public String uploadMethod(MultipartFile file) throws Exception {
        COSClientUtil cosClientUtil = new COSClientUtil();
        String name = cosClientUtil.uploadFile2Cos(file);
        String imgUrl = cosClientUtil.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }


    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @GetMapping("/imgDelete/{id}")
    public Object getShopsInfo(@PathVariable Integer id) {

        GoodInfo goodInfo = this.goodInfoService.selectByPrimaryKey(id);
        String name = goodInfo.getGoodImg();
        goodInfo.setGoodImg(null);
        goodInfoService.updateByPrimaryKey(goodInfo);

        COSClientUtil cosClientUtil = new COSClientUtil();
        String[] names = name.split("/");
        cosClientUtil.deleteImg(names[names.length - 1]);
        return "redirect:/goodInfo/goodInfoEdit/" + id;
    }
}
