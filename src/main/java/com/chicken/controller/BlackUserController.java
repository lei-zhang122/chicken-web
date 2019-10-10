package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.BlackUser;
import com.chicken.model.GoodDetail;
import com.chicken.model.GoodInfo;
import com.chicken.service.BlackUserService;
import com.chicken.service.GoodDetailService;
import com.chicken.service.GoodInfoService;
import com.chicken.service.RedisService;
import com.chicken.util.*;
import com.chicken.vo.BlackUserRequest;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhanglei
 * @date 2019-09-01 15:12
 */
@Controller
@RequestMapping("/blackUser")
public class BlackUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    BlackUserService blackUserService;



    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/blackUserPage")
    @RequiresPermissions("blackUserPage")
    public Object blackUserPage(Model model) {

        BlackUserRequest info = new BlackUserRequest();
        PageInfo<BlackUser> result = this.blackUserService.selectByBlackUser(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("userName", info.getUserName());

        return "black/blackUserList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/blackUserPageList", method = RequestMethod.POST)
    public Object blackUserPageList(@ModelAttribute BlackUserRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<BlackUser> result = this.blackUserService.selectByBlackUser(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("userName", info.getUserName());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        return "black/blackUserList";
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/blackUserAdd")
    public Object addBlackUser(Model model) {

        BlackUser blackUser = new BlackUser();
        model.addAttribute("blackUser", blackUser);

        return "black/blackUserAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/blackUserEdit/{id}")
    public Object blackUserEdit(Model model, @PathVariable Integer id) {
        BlackUser blackUser = this.blackUserService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("blackUser", blackUser);
        model.addAttribute("createTime", sdf.format(blackUser.getCreateTime()));

        return "black/blackUserAdd";
    }

    /**
     * 添加/修改
     *
     * @return
     */
    @PostMapping(value = "/insertBlackUser")
    @ResponseBody
    public Object insertBlackUser(@RequestBody BlackUserRequest info) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail(CodeEnum.NO_LOGIN_MESSAGE.getMsg());
        }

        /**
         * 校验用户信息
         */
        String errorMsg = BlackUserUtils.checkBlackUserInsertRequest(info);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("黑名单，数据校验失败，传入参数{}", info.toString());
            return CallResult.fail(errorMsg);
        }


        /**
         * 封装对象
         */
        BlackUser blackUser = new BlackUser();
        BeanUtils.copyProperties(info, blackUser);
        blackUser.setUserId(Integer.valueOf(info.getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(info.getId())) {

            blackUser.setId(Integer.valueOf(info.getId()));
            blackUser.setCreateTime(sdf.parse(info.getCreateTime()));
            blackUser.setCreateUser(getUserSession().getUserName());
            this.blackUserService.updateByPrimaryKey(blackUser);
            logger.info("黑名单，数据修改，修改ID{}，修改人:{}", info.getId(), getUserSession().getUserName());
        } else {

            blackUser.setCreateTime(new Date());
            blackUser.setCreateUser(getUserSession().getUserName());
            blackUserService.insert(blackUser);
            logger.info("黑名单，添加内容，内容={}", info.toString());
        }

        return CallResult.success();
    }

    /**
     * 进入修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/blackUserDelete/{id}")
    public Object blackUserDelete(@PathVariable Integer id) {
        this.blackUserService.deleteByPrimaryKey(id);
        return "redirect:/blackUser/blackUserPage";
    }
}
