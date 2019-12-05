package com.aaa.realm;


import com.aaa.model.PermInfo;
import com.aaa.model.UserInfo;
import com.aaa.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description :  
 * @Author      : cat
 * @exception   : 
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = ((String) principalCollection.getPrimaryPrincipal());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        List<PermInfo> permInfos = userService.getPermByUserName(userName);
        if (permInfos != null){
            for (PermInfo permInfo : permInfos) {
                simpleAuthorizationInfo.addStringPermission(permInfo.getResPermName());
            }
        }
        return simpleAuthorizationInfo;
    }
    /**
    * shiro验证方法
    * @param authenticationToken
    * @return org.apache.shiro.authc.AuthenticationInfo
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*获取用户名（账号）*/
        String principal = (String) authenticationToken.getPrincipal();
        UserInfo user = userService.getUserByUserName(principal);
        if (user == null){
            throw  new UnknownAccountException("用户不存在");
        }
        if (user.getUserStatus() == 0){
            throw  new UnknownAccountException("用户被冻结");
        }
        
        SimpleByteSource simpleByteSource = new SimpleByteSource(principal);
        /**
         * Object principal, 身份信息
         * Object hashedCredentials,  凭证信息
         * ByteSource credentialsSalt, 盐值 加点盐
         * String realmName 当前realm的名字
         */
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassWord(), simpleByteSource, this.getName());

        return simpleAuthenticationInfo;
    }
}
