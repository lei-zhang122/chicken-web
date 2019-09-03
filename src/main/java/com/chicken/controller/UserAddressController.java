package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.AccountUser;
import com.chicken.model.UserAddress;
import com.chicken.model.WechatUser;
import com.chicken.service.AccountUserService;
import com.chicken.service.UserAddressService;
import com.chicken.service.WechatUserService;
import com.chicken.util.*;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.UserAddressRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("userAddress")
public class UserAddressController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    UserAddressService userAddressService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/userAddressPage")
    @RequiresPermissions("userAddressPage")
    public Object userAddressPage(Model model) {

        UserAddressRequest info = new UserAddressRequest();
        PageInfo<Map> result = this.userAddressService.selectByAccountUser(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "useraddress/userAddressList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userAddressPageList", method = RequestMethod.POST)
    @RequiresPermissions("userAddressPageList")
    public Object userAddressPageList(@ModelAttribute UserAddressRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.userAddressService.selectByAccountUser(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "useraddress/userAddressList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, UserAddressRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("nickName",info.getNickName());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/userAddressEdit/{id}")
    public Object userAddressEdit(Model model, @PathVariable Integer id) {
        UserAddress userAddress = this.userAddressService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("userAddress", userAddress);
        model.addAttribute("createTime", sdf.format(userAddress.getCreateTime()));

        WechatUser wechatUser = this.wechatUserService.selectByPrimaryKey(userAddress.getUserId());
        model.addAttribute("nickName", wechatUser.getNickName());

        return "useraddress/userAddressAdd";
    }

    /**
     * 添加/修改信息
     *
     * @param info
     * @return
     */
    @PostMapping(value = "/insertUserAddress")
    @ResponseBody
    public Object insertUserAddress(@RequestBody UserAddressRequest info) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail(CodeEnum.NO_LOGIN_MESSAGE.getMsg());
        }

        /**
         * 校验用户信息
         */
        String errorMsg = UserAddressUtils.checkUserAddressInsertRequest(info);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("用户地址信息，数据校验失败，传入参数{}", info.toString());
            return CallResult.fail(errorMsg);
        }


        /**
         * 封装对象
         */
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(info, userAddress);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(info.getId())) {
            userAddress.setUserId(Integer.valueOf(info.getUserId()));
            userAddress.setId(Integer.valueOf(info.getId()));
            userAddress.setCreateTime(sdf.parse(info.getCreateTime()));
            this.userAddressService.updateByPrimaryKey(userAddress);
            logger.info("用户地址信息，数据修改，修改ID{}，修改人:{}", info.getId(), getUserSession().getUserName());
        }

        return CallResult.success();
    }
}
