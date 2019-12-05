package com.aaa.service;

import com.aaa.model.PermInfo;
import com.aaa.model.RoleInfo;
import com.aaa.model.UserInfo;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21 15:29
 * @Version     : 1.0
 */
public interface UserService {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return com.aaa.model.User
     * @Author: cat
     * @Date: 2019/11/8
     */
    UserInfo getUserByUserName(String userName);
    /**
     * 根据用户名查询对应的角色id
     * @param userName
     * @return java.util.List<com.aaa.model.RoleInfo>
     * @Author: cat
     * @Date: 2019/11/21
     */
    List<RoleInfo> getRolesByUsername(String userName);
    /**
     * 根据用户名获取对应角色信息所对应的权限信息
     * @param userName
     * @return java.util.List<com.aaa.model.PermInfo>
     * @Author: cat
     * @Date: 2019/11/21
     */
    List<PermInfo> getPermByUserName(String userName);

    /**
     * 添加用户 用户状态默认为1
     * @param user
     * @return void
     * @Author: cat
     * @Date: 2019/11/21
     */
    void addUser(UserInfo user);
}
