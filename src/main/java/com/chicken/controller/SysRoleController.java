package com.chicken.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chicken.base.BaseController;
import com.chicken.common.FailureResponse;
import com.chicken.common.ResponseViewModel;
import com.chicken.model.RolePermission;
import com.chicken.model.SysPermission;
import com.chicken.model.SysRole;
import com.chicken.service.SysPermissionService;
import com.chicken.service.SysRoleService;
import com.chicken.util.ContantUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: BrandInfoController
 * @author: zhanglei11527
 * @create: 2018-07-30 14:13
 **/
@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysPermissionService sysPermissionService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/sysRolePage")
    @RequiresPermissions("sysRolePage")
    public Object sysRolePage(Model model) {

        SysRole sysRole = new SysRole();
        PageInfo<SysRole> result = this.sysRoleService.findByPage(sysRole, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("role", sysRole.getRole());

        return "sys/sysRoleList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sysRolePageList", method = RequestMethod.POST)
    @RequiresPermissions("sysRolePageList")
    public Object sysRolePageList(@ModelAttribute SysRole sysRole, Model model) {


        Integer pageNum = 0;
        if (null != sysRole.getCurrentPage() && !"0".equals(sysRole.getCurrentPage())) {
            pageNum = Integer.valueOf(sysRole.getCurrentPage());
        }

        PageInfo<SysRole> result = this.sysRoleService.findByPage(sysRole, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("role", sysRole.getRole());

        return "sys/sysRoleList";
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sysRoleAdd")
    public Object addSysRole(Model model) {

        SysRole sysRole = new SysRole();
        sysRole.setAvailable("1");
        model.addAttribute("sysRole", sysRole);

        return "sys/sysRoleAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/sysRoleEdit/{id}")
    public Object getBrandInfo(Model model, @PathVariable Integer id) {
        SysRole sysRole = sysRoleService.selectByPrimaryKey(id);

        model.addAttribute("sysRole", sysRole);
        return "sys/sysRoleAdd";
    }

    /**
     * 添加/修改角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/insertSysRole")
    @ResponseBody
    public Object insertSysRole(@RequestBody SysRole sysRole) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return new FailureResponse(ContantUtil.NO_LOGIN_MESSAGE);
        }

        /**
         * 校验用户信息
         */
        String errorMsg = null;
        if (StringUtils.isBlank(sysRole.getRole())) {
            errorMsg = "角色名称不能为空";
        }
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("校验角色信息：" + errorMsg + ",角色内容：" + sysRole.toString(), getUserSession().getId(), null, null);
            return new FailureResponse(errorMsg);
        }

        /**
         * 封装对象
         */

        if (null != sysRole.getId()) {
            this.sysRoleService.updateByPrimaryKey(sysRole);
            logger.info("修改角色信息,id信息为：" + sysRole.getId() + ",角色内容：" + sysRole.toString(), getUserSession().getId(), null, null);
        } else {
            this.sysRoleService.insert(sysRole);
            logger.info("添加角色信息,id信息为：" + sysRole.getId() + ",角色内容：" + sysRole.toString(), getUserSession().getId(), null, null);
        }

        return new ResponseViewModel();
    }


    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/rolePermission")
    @RequiresPermissions("rolePermission")
    public Object rolePermission(Model model) {

        List<SysRole> sysRoles = this.sysRoleService.selectAll();
        model.addAttribute("sysRoles", sysRoles);
        return "sys/rolePermission";
    }

    /**
     * 获取树结构
     *
     * @return
     */
    @GetMapping(value = "/getAllTree/{id}")
    @ResponseBody
    public Object getAllTree(@PathVariable Integer id) {

        Map map = new HashedMap();
        if (id != 0) {
            List<Map> permissionMap = this.sysRoleService.selectPermissionByRole(id);
            if (permissionMap.size() > 0) {
                for (Map m : permissionMap) {
                    map.put(Integer.valueOf(m.get("permission_id") + ""), m.get("permission_id"));
                }
            }
        }

        JSONObject root = new JSONObject();
        root.put("id", "0");
        root.put("text", "root");
        root.put("value", "0");
        root.put("showcheck", true);
        root.put("isexpand", true);
        root.put("complete", true);
        root.put("checkstate", 0);
        root.put("hasChildren", true);
        List<SysPermission> sysPermissionList = this.sysPermissionService.selectByParentId(0);
        if (sysPermissionList.size() > 0) {
            JSONArray lists = new JSONArray();
            for (SysPermission p : sysPermissionList) {
                JSONObject sub = new JSONObject();
                sub.put("id", p.getId() + "");
                sub.put("text", p.getName());
                sub.put("value", p.getId() + "");
                sub.put("showcheck", true);
                sub.put("isexpand", false);
                sub.put("complete", true);
                if (map.containsKey(p.getId())) {
                    sub.put("checkstate", 1);
                } else {
                    sub.put("checkstate", 0);
                }
                JSONArray subNotes = new JSONArray();
                List<SysPermission> subPermissionList = this.sysPermissionService.selectByParentId(p.getId());
                if (subPermissionList.size() > 0) {
                    sub.put("hasChildren", true);
                    for (SysPermission s : subPermissionList) {
                        JSONObject note = new JSONObject();
                        note.put("id", s.getId() + "");
                        note.put("text", s.getName());
                        note.put("value", s.getId() + "");
                        note.put("showcheck", true);
                        note.put("isexpand", false);
                        note.put("complete", true);
                        if (map.containsKey(s.getId())) {
                            note.put("checkstate", 1);
                        } else {
                            note.put("checkstate", 0);
                        }
                        note.put("hasChildren", false);
                        subNotes.add(note);
                    }
                } else {
                    sub.put("hasChildren", false);
                }
                sub.put("ChildNodes", subNotes);
                lists.add(sub);
            }
            root.put("ChildNodes", lists);
        }
        return root;
    }


    /**
     * 插入角色功能
     *
     * @param roleId
     * @param perIds
     * @return
     */
    @PostMapping(value = "/insertRolePermission")
    public Object insertRolePermission(Model model, String roleId, String perIds) {
        if (StringUtils.isNoneBlank(roleId) || StringUtils.isNoneBlank(perIds)) {
            //删除数据
            sysRoleService.deletePermissionRole(Integer.valueOf(roleId));

            List<RolePermission> rolePermissions = new ArrayList<>();
            String[] strs = perIds.split(",");
            for(String s:strs){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(Integer.valueOf(s));
                rolePermission.setRoleId(Integer.valueOf(roleId));
                rolePermissions.add(rolePermission);
            }

            if(rolePermissions.size()>0){
                sysRoleService.insertBatch(rolePermissions);
            }
        }

        List<SysRole> sysRoles = this.sysRoleService.selectAll();
        model.addAttribute("sysRoles", sysRoles);
        return "sys/rolePermission";
    }
}
