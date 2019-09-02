package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.AccountUser;
import com.chicken.model.WechatUser;
import com.chicken.service.AccountUserService;
import com.chicken.service.UserInviteService;
import com.chicken.service.WechatUserService;
import com.chicken.util.AccountUserUtils;
import com.chicken.util.CallResult;
import com.chicken.util.CodeEnum;
import com.chicken.util.ContantUtil;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.UserInviteRequest;
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
@RequestMapping("userInvite")
public class UserInviteController extends BaseController {


    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    UserInviteService userInviteService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/userInvitePage")
    @RequiresPermissions("userInvitePage")
    public Object userInvitePage(Model model) {

        UserInviteRequest info = new UserInviteRequest();
        PageInfo<Map> result = this.userInviteService.selectByUserInvite(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "userinvite/userInviteList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userInvitePageList", method = RequestMethod.POST)
    @RequiresPermissions("userInvitePageList")
    public Object userInvitePageList(@ModelAttribute UserInviteRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.userInviteService.selectByUserInvite(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "userinvite/userInviteList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, UserInviteRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("userId", StringUtils.isBlank(info.getUserId()) ? 0 : Integer.valueOf(info.getUserId()));
        model.addAttribute("inviteUserId", StringUtils.isBlank(info.getInviteUserId()) ? 0 : Integer.valueOf(info.getInviteUserId()));

        model.addAttribute("countPage", result.getPages());
        List<WechatUser> selectAll = this.wechatUserService.selectAll();
        model.addAttribute("userList", selectAll);
    }
}
