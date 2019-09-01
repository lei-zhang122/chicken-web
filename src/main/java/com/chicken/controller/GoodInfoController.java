package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.GoodInfo;
import com.chicken.service.GoodInfoService;
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

    private final ResourceLoader resourceLoader;

    @Value("${web.upload-path}")
    private String path;

    @Autowired
    public GoodInfoController(ResourceLoader resourceLoader){
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
    public Object insertGoodInfo(@RequestParam("fileName") MultipartFile file,@RequestParam String goodType,
                                 @RequestParam String goodName,@RequestParam String goodNum,
                                 @RequestParam String goodPrice,@RequestParam String goodVirtual,
                                 @RequestParam String goodDownVirtual,@RequestParam String goodDetail,
                                 @RequestParam String status, @RequestParam String createUser,
                                 @RequestParam String createTime, @RequestParam String id,
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
        if (!file.isEmpty() && !StringUtils.isBlank(file.getOriginalFilename())) {

            String extname = getSuffix(file.getOriginalFilename()).split("\\.")[1];
            String allowImgFormat = "gif,jpg,jpeg,png";
            if (allowImgFormat.contains(extname.toLowerCase())) {
                String fileName = getFileName(file.getOriginalFilename());
                String riqi= DateUtil.currentYYYYMMDD();
                String paths = path+"/"+riqi;
                String val = upload(file, paths, fileName);
                goodInfoRequest.setGoodImg(val);
            }
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
        if (!StringUtils.isEmpty(goodInfoRequest.getId())) {
            goodInfo.setId(Integer.valueOf(goodInfoRequest.getId()));
            goodInfo.setCreateTime(sdf.parse(goodInfoRequest.getCreateTime()));
            goodInfo.setCreateUser(Integer.valueOf(goodInfoRequest.getCreateUser()));
            this.goodInfoService.updateByPrimaryKey(goodInfo);
            logger.info("商品信息，修改内容，内容ID，{}", goodInfo.getId());
        } else {
            goodInfo.setCreateTime(new Date());
            goodInfo.setCreateUser(getUserSession().getId());
            goodInfoService.insert(goodInfo);
            logger.info("商品信息，添加内容，内容={}", goodInfoRequest.toString());
        }


        GoodInfoRequest info = new GoodInfoRequest();
        PageInfo<GoodInfo> result = this.goodInfoService.selectByGoodInfo(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodType", info.getGoodType());
        model.addAttribute("goodName", info.getGoodName());

        return "goodinfo/goodInfoList";
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return
     */
    public String getFileName(String fileOriginName) {
        return UUID.randomUUID().toString().replace("-", "")
                + getSuffix(fileOriginName);
    }

    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public String upload(MultipartFile file, String path, String fileName) {
        //使用原文件名
        String realPath = path + "/" + fileName;
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return realPath;
        } catch (IllegalStateException e) {
            logger.info("上传图片出错："+e.getMessage());
            return "";
        } catch (IOException e) {
            logger.info("上传图片出错："+e.getMessage());
            return "";
        }
    }


    /**
     * 删除图片
     * @param id
     * @return
     */
    @GetMapping("/imgDelete/{id}")
    public Object getShopsInfo(@PathVariable Integer id){

        GoodInfo goodInfo = this.goodInfoService.selectByPrimaryKey(id);
        goodInfo.setGoodImg(null);
        goodInfoService.updateByPrimaryKey(goodInfo);
        return "redirect:/goodInfo/goodInfoEdit/"+id;
    }
}
