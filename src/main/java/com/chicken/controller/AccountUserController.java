package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.AccountUser;
import com.chicken.model.WechatUser;
import com.chicken.service.AccountUserService;
import com.chicken.service.RedisService;
import com.chicken.service.WechatUserService;
import com.chicken.util.*;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.WechatUserRequest;
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
@RequestMapping("accountUser")
public class AccountUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    AccountUserService accountUserService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/accountUserPage")
    @RequiresPermissions("accountUserPage")
    public Object accountUserPage(Model model) {

        AccountUserRequest info = new AccountUserRequest();
        PageInfo<Map> result = this.accountUserService.selectByAccountUser(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "accountuser/accountUserList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/accountUserPageList", method = RequestMethod.POST)
    @RequiresPermissions("accountUserPageList")
    public Object accountUserPageList(@ModelAttribute AccountUserRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.accountUserService.selectByAccountUser(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "accountuser/accountUserList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, AccountUserRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("userId", StringUtils.isBlank(info.getUserId()) ? 0 : Integer.valueOf(info.getUserId()));

        List<WechatUser> selectAll = this.wechatUserService.selectAll();
        model.addAttribute("userList", selectAll);
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/accountUserEdit/{id}")
    public Object accountUserEdit(Model model, @PathVariable Integer id) {
        AccountUser accountUser = this.accountUserService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("accountUser", accountUser);
        model.addAttribute("createTime", sdf.format(accountUser.getCreateTime()));

        WechatUser wechatUser = this.wechatUserService.selectByPrimaryKey(accountUser.getUserId());
        model.addAttribute("nickName", wechatUser.getNickName());

        return "accountuser/accountUserAdd";
    }

    /**
     * 添加/修改信息
     *
     * @param info
     * @return
     */
    @PostMapping(value = "/insertAccountUser")
    @ResponseBody
    public Object insertAccountUser(@RequestBody AccountUserRequest info) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail(CodeEnum.NO_LOGIN_MESSAGE.getMsg());
        }

        /**
         * 校验用户信息
         */
        String errorMsg = AccountUserUtils.checkAccountUserInsertRequest(info);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("账户信息，数据校验失败，传入参数{}", info.toString());
            return CallResult.fail(errorMsg);
        }


        /**
         * 封装对象
         */
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(info, accountUser);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(info.getId())) {
            accountUser.setAttentCount(Double.valueOf(info.getAttentCount()));
            accountUser.setUserId(Integer.valueOf(info.getUserId()));
            accountUser.setBalance(Double.valueOf(info.getBalance()));
            accountUser.setConsumeCount(Double.valueOf(info.getConsumeCount()));
            accountUser.setId(Integer.valueOf(info.getId()));
            accountUser.setCreateTime(sdf.parse(info.getCreateTime()));
            this.accountUserService.updateByPrimaryKey(accountUser);
            logger.info("账户信息，数据修改，修改ID{}，修改人:{}", info.getId(), getUserSession().getUserName());
        }

        return CallResult.success();
    }
}
