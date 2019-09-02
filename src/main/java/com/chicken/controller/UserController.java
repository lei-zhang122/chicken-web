package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.common.CommonConstant;
import com.chicken.common.FailureResponse;
import com.chicken.common.ResponseViewModel;
import com.chicken.model.SysRole;
import com.chicken.model.User;
import com.chicken.model.UserRole;
import com.chicken.service.SysRoleService;
import com.chicken.service.UserService;
import com.chicken.util.ContantUtil;
import com.chicken.util.MD5Util;
import com.chicken.util.UserUtils;
import com.chicken.vo.UserRequest;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhanglei
 * @create: 2018-07-30 14:13
 **/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    SysRoleService sysRoleService;

    /**
     * 进入查询列表页面
     * @param model
     * @return
     */
    @GetMapping(value = "/userPage")
    @RequiresPermissions("userPage")
    public Object userPage(Model model){

        UserRequest userRequest = new UserRequest();
        userRequest.setStatus("1");
        PageInfo<Map> result = this.userService.findByPage(userRequest, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list",result.getList());
        model.addAttribute("countPage",result.getPages());
        model.addAttribute("currentPage",result.getPageNum());
        model.addAttribute("userName",userRequest.getUserName());
        model.addAttribute("loginName",userRequest.getLoginName());

        return "user/userList";
    }

    /**
     * 查询列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/userPageList",method = RequestMethod.POST)
    @RequiresPermissions("userPageList")
    public Object userPageList(@ModelAttribute UserRequest userRequest, Model model){


        Integer pageNum=0;
        if(null != userRequest.getCurrentPage() && !"0".equals(userRequest.getCurrentPage())){
            pageNum = Integer.valueOf(userRequest.getCurrentPage());
        }

        PageInfo<Map> result = this.userService.findByPage(userRequest, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list",result.getList());
        model.addAttribute("countPage",result.getPages());
        model.addAttribute("currentPage",result.getPageNum());
        model.addAttribute("userName",userRequest.getUserName());
        model.addAttribute("loginName",userRequest.getLoginName());

        return "user/userList";
    }

    /**
     * 进入添加页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/userAdd")
    public Object addUser(Model model){

        User user = new User();
        user.setStatus("1");
        model.addAttribute("user",user);

        return "user/userAdd";
    }

    /**
     * 进入修改页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/userEdit/{id}")
    public Object getUser(Model model,@PathVariable Integer id){
        User user = userService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
        model.addAttribute("user",user);
        model.addAttribute("createTime",sdf.format(user.getCreateTime()));
        if(null != user.getEditTime()){
            model.addAttribute("editTime",sdf.format(user.getEditTime()));
        }

        return "user/userAdd";
    }

    /**
     * 添加/修改用户
     * @param userRequest
     * @return
     */
    @PostMapping(value = "/insertUser")
    @ResponseBody
    public Object insertUser(@RequestBody UserRequest userRequest) throws Exception{

        /**
         * 判断是否登陆
         */
        if(!userLogin()){
            return new FailureResponse(ContantUtil.NO_LOGIN_MESSAGE);
        }

        /**
         * 校验用户信息
         */
        String errorMsg = UserUtils.checkUserInsertRequest(userRequest);
        if (StringUtils.isNotBlank(errorMsg)) {
            if (StringUtils.isEmpty(userRequest.getId())) {
                logger.info("校验用户信息：" + errorMsg + ",用户内容：" + userRequest.toString(), getUserSession().getId(), null, null);
            } else {
                logger.info("校验用户信息：" + errorMsg + ",用户内容：" + userRequest.toString(), getUserSession().getId(), null, null);
            }

            return new FailureResponse(errorMsg);
        }
 
        /**
         * 封装对象
         */
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        if (!StringUtils.isEmpty(userRequest.getId())) {
            SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
            user.setId(Integer.valueOf(userRequest.getId()));
            user.setCreateUser(Integer.valueOf(userRequest.getCreateUser()));
            user.setCreateTime(sdf.parse(userRequest.getCreateTime()));
            user.setEditTime(new Date());
            this.userService.updateByPrimaryKey(user);
            logger.info("修改用户信息,id信息为：" + user.getId() + ",用户内容：" + user.toString(), getUserSession().getId(), null, null);
        }else{
            user.setUserPwd(MD5Util.md5(userRequest.getUserPwd()));
            user.setCreateUser(getUserSession().getId());
            user.setCreateTime(new Date());
            this.userService.insert(user);
            logger.info("添加用户信息,id信息为：" + user.getId() + ",用户内容：" + user.toString(), getUserSession().getId(), null, null);
        }

        return new ResponseViewModel();
    }

    /**
     * 进入修改密码页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/userResetPwd/{id}")
    public Object resetPwd(Model model,@PathVariable Integer id){
        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        return "user/userResetPwd";
    }

    /**
     * 修改用户密码
     * @param userRequest
     * @return
     */
    @PostMapping(value = "/resetPwdUser")
    @ResponseBody
    public Object resetPwdUser(@RequestBody UserRequest userRequest) throws Exception{

        /**
         * 判断是否登陆
         */
        if(!userLogin()){
            return new FailureResponse(ContantUtil.NO_LOGIN_MESSAGE);
        }

        /**
         * 校验用户信息
         */
        if (!StringUtils.isNotBlank(userRequest.getUserPwd()) || !StringUtils.isNotBlank(userRequest.getId())) {
            logger.info("校验用户信息：用户密码或用户id不能为空", getUserSession().getId(), null, null);
            return new FailureResponse("用户密码或用户id不能为空");
        }

        userService.updatePwdById(MD5Util.md5(userRequest.getUserPwd()),Integer.valueOf(userRequest.getId()));

        return new ResponseViewModel();
    }

    /**
     * 进入修改密码页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/userSetRole/{id}")
    public Object userSetRole(Model model,@PathVariable Integer id){

        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        //查询当前用户的角色
        List<SysRole> sysRoles = sysRoleService.selectSysRole(id);
        if(sysRoles.size()>0){
            model.addAttribute("myrole",sysRoles.get(0).getId());
        }
        List<SysRole> list = sysRoleService.selectAll();
        for(SysRole s:list){
            for(SysRole a:sysRoles){
                if(a.getId().equals(s.getId())){
                    s.setUserId(1);
                }
            }
        }
        model.addAttribute("list",list);
        return "user/userSetRole";
    }

    /**
     * 插入用户角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @PostMapping(value = "/insertUserRole")
    public Object insertUserRole(Model model, String userId, String roleId) {

        if (StringUtils.isNoneBlank(roleId) || StringUtils.isNoneBlank(userId)) {
            //删除角色
            sysRoleService.deleteUserRole(Integer.valueOf(userId));

            UserRole userRole = new UserRole();
            userRole.setRoleId(Integer.valueOf(roleId));
            userRole.setUserId(Integer.valueOf(userId));
            sysRoleService.insertUserRole(userRole);
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setStatus("1");
        PageInfo<Map> result = this.userService.findByPage(userRequest, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list",result.getList());
        model.addAttribute("countPage",result.getPages());
        model.addAttribute("currentPage",result.getPageNum());
        model.addAttribute("userName",userRequest.getUserName());
        model.addAttribute("loginName",userRequest.getLoginName());

        return "user/userList";
    }
}
