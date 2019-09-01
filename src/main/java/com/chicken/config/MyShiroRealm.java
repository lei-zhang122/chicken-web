package com.chicken.config;

import com.chicken.model.SysPermission;
import com.chicken.model.SysRole;
import com.chicken.model.User;
import com.chicken.service.SysPermissionService;
import com.chicken.service.SysRoleService;
import com.chicken.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();
        //查询用户角色
        List<SysRole> sysRoles = sysRoleService.selectSysRole(user.getId());
        if(sysRoles.size()>0){
            for(SysRole role:sysRoles){
                authorizationInfo.addRole(role.getRole());
                List<SysPermission> sysPermissions = sysPermissionService.selectByRoleId(role.getId());
                if(sysPermissions.size()>0){
                    //查询角色权限
                    for(SysPermission p:sysPermissions){
                        authorizationInfo.addStringPermission(p.getPermission());
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken  userpasswordToken = (UsernamePasswordToken) token;//这边是界面的登陆数据，将数据封装成token
        String username = userpasswordToken.getUsername();
        //获取用户的输入的账号
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.selectByLoginName(username);
        System.out.println("----->>user="+user);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getUserPwd(), //密码
                this.getClass().
                getName()  //realm name
        );
        return authenticationInfo;
    }

}