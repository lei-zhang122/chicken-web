package com.chicken.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chicken.base.BaseController;
import com.chicken.common.FailureResponse;
import com.chicken.model.SysPermission;
import com.chicken.service.SysPermissionService;
import com.chicken.util.ContantUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: BrandInfoController
 * @author: zhanglei11527
 * @create: 2018-07-30 14:13
 **/
@Controller
@RequestMapping("/syspermission")
public class SysPermissionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

    @Autowired
    SysPermissionService sysPermissionService;


    /**
     * 进入查询列表页面
     * @return
     */
    @GetMapping(value = "/sysPermissionPage")
    @RequiresPermissions("sysPermissionPage")
    public Object sysPermissionPage() {

        return "sys/sysPermissionList";
    }

    /**
     * 获取树结构
     * @return
     */
    @GetMapping(value = "/getTree")
    @ResponseBody
    public Object getTree(){
        JSONObject root = new JSONObject();
        root.put("id","0");
        root.put("text","root");
        root.put("value","0");
        root.put("showcheck",true);
        root.put("isexpand",true);
        root.put("complete",true);
        root.put("checkstate",0);
        root.put("hasChildren",true);
        List<SysPermission> sysPermissionList = this.sysPermissionService.selectByParentId(0);
        if(sysPermissionList.size()>0) {
            JSONArray lists = new JSONArray();
            for (SysPermission p:sysPermissionList) {
                JSONObject sub = new JSONObject();
                sub.put("id",p.getId()+"");
                sub.put("text",p.getName());
                sub.put("value",p.getId()+"");
                sub.put("showcheck",true);
                sub.put("isexpand",false);
                sub.put("complete",true);
                sub.put("checkstate",0);
                JSONArray subNotes = new JSONArray();
                List<SysPermission> subPermissionList = this.sysPermissionService.selectByParentId(p.getId());
                if (subPermissionList.size()>0) {
                    sub.put("hasChildren",true);
                    for(SysPermission s:subPermissionList){
                        JSONObject note = new JSONObject();
                        note.put("id",s.getId()+"");
                        note.put("text",s.getName());
                        note.put("value",s.getId()+"");
                        note.put("showcheck",true);
                        note.put("isexpand",false);
                        note.put("complete",true);
                        note.put("checkstate",0);
                        note.put("hasChildren",false);
                        subNotes.add(note);
                    }
                }else{
                    sub.put("hasChildren",false);
                }
                sub.put("ChildNodes",subNotes);
                lists.add(sub);
            }
            root.put("ChildNodes",lists);
        }
        return root;
    }


    /**
     * 进入添加页面
     * @param model
     * @return
     */
    @GetMapping(value = "/permissionList/{id}")
    public Object permissionList(Model model,@PathVariable Integer id){
        SysPermission sysPermission = new SysPermission();
        sysPermission.setParentId(id);
        PageInfo<SysPermission> result = this.sysPermissionService.findByPage(sysPermission, 0, ContantUtil.DEFAULT_PAGE_SIZE);
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("parentId", id);

        return "sys/sysPermissionPage";
    }


    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/permissionPageList", method = RequestMethod.POST)
    public Object permissionPageList(@ModelAttribute SysPermission sysPermission, Model model) {

        Integer pageNum=0;
        if(null != sysPermission.getCurrentPage() && !"0".equals(sysPermission.getCurrentPage())){
            pageNum = Integer.valueOf(sysPermission.getCurrentPage());
        }
        PageInfo<SysPermission> result = this.sysPermissionService.findByPage(sysPermission, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("parentId", sysPermission.getParentId());

        return "sys/sysPermissionPage";
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/sysPermissionAdd/{parentId}")
    public Object addSysPermission(Model model,@PathVariable Integer parentId) {

        SysPermission sysPermission = new SysPermission();
        sysPermission.setAvailable("1");
        sysPermission.setParentId(parentId);
        sysPermission.setResourceType("menu");
        model.addAttribute("sysPermission", sysPermission);

        return "sys/sysPermissionAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/sysPermissionEdit/{id}")
    public Object getSysPermission(Model model, @PathVariable Integer id) {
        SysPermission sysPermission = sysPermissionService.selectByPrimaryKey(id);
        model.addAttribute("sysPermission", sysPermission);
        return "sys/sysPermissionAdd";
    }

    /**
     * 添加/修改品牌
     * @param sysPermission
     * @return
     */
    @PostMapping(value = "/insertSysPermission")
    public Object insertSysPermission(Model model, SysPermission sysPermission) throws Exception{

        /**
         * 判断是否登陆
         */
        if(!userLogin()){
            return new FailureResponse(ContantUtil.NO_LOGIN_MESSAGE);
        }

        /**
         * 校验用户信息
         */
        String errorMsg = null;
        if(StringUtils.isBlank(sysPermission.getName())){
            errorMsg="名称不能为空";
        }
        if(StringUtils.isBlank(sysPermission.getPermission())){
            errorMsg="权限字符串不能为空";
        }
        if(null == sysPermission.getParentId()){
            errorMsg="父ID不能为空";
        }

        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("校验角色信息：" + errorMsg + ",角色内容：" + sysPermission.toString(), getUserSession().getId(), null, null);
            return new FailureResponse(errorMsg);
        }

        /**
         * 封装对象
         */


        if (null != sysPermission.getId()) {
            this.sysPermissionService.updateByPrimaryKey(sysPermission);
            logger.info("修改角色信息,id信息为：" + sysPermission.getId() + ",角色内容：" + sysPermission.toString(), getUserSession().getId(), null, null);
        }else{
            this.sysPermissionService.insert(sysPermission);
            logger.info("添加角色信息,id信息为：" + sysPermission.getId() + ",品牌内容：" + sysPermission.toString(), getUserSession().getId(), null, null);
        }

        SysPermission permission = new SysPermission();
        permission.setParentId(sysPermission.getParentId());
        PageInfo<SysPermission> result = this.sysPermissionService.findByPage(permission, 0, ContantUtil.DEFAULT_PAGE_SIZE);
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("parentId", sysPermission.getParentId());

        return "sys/sysPermissionPage";
    }
}
