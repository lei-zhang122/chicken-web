package com.chicken.base;

import com.chicken.model.SysPermission;
import com.chicken.model.User;
import com.chicken.service.SysPermissionService;
import com.chicken.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: 首页
 * @author: zhanglei11527
 * @create: 2018-07-30 17:04
 **/
@Controller
public class IndexController {


    @Autowired
    UserService userService;

    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping({"/","/index"})
    public Object index(){
        System.out.println("1231313");
        return "redirect:/main";
    }

    @RequestMapping("/login")
    public Object login(){
        System.out.println("3333333333");
        return "/user/login";
    }

    @RequestMapping("/main")
    public Object main(HttpSession session, Model model){

        /**
         * 根据用户查询权限
         */
        User user = (User)session.getAttribute("user_session");
        if(null == user){
            return "redirect:/login";
        }

        Map<String,String> parentList = new LinkedHashMap<>();
        Map<String,List<String>> childList = new LinkedHashMap();

        List<String> noteList =null;
        String id="-1";
        List<SysPermission> sysPermissionList = this.sysPermissionService.selectByUserId(user.getId());
        if(sysPermissionList.size()>0){
            for(SysPermission p:sysPermissionList){
                if(p.getParentId().equals(0) && !id.equals(p.getId())){
                    if(noteList !=null && noteList.size()>0){
                        childList.put(id,noteList);
                    }
                    noteList = new LinkedList<>();
                    parentList.put(p.getName(),p.getId().toString());
                    id = p.getId().toString();
                }else{
                    noteList.add("<a href='"+p.getUrl()+"' target=\"mainframe\">"+p.getName()+"</a>");
                }
            }
            if(noteList !=null && noteList.size()>0){
                childList.put(id,noteList);
            }
        }

        System.out.println(parentList.toString());
        model.addAttribute("parentList",parentList);
        model.addAttribute("childList",childList);
        return "main";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    /**
     * 注销操作
     *
     * @return
     */
    @GetMapping("/logout")
    public Object loginOut() {
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        if (subject != null) {
            subject.logout();//不为空，执行一次logout的操作，将session全部清空
        }
        return "redirect:/";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("phonenumber") String phonenumber,
                            @RequestParam("password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(phonenumber, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            System.out.println("获取到信息，开始验证！！");
            subject.login(token);//登陆成功的话，放到session中
            User user = (User) subject.getPrincipal();
            session.setAttribute("user_session", user);
            return "redirect:/main";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
