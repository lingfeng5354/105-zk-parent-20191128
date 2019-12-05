package com.aaa.mapper;


import com.aaa.model.PermInfo;
import com.aaa.model.RoleInfo;
import com.aaa.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Mapper
public interface UserMapper {
    /**
    * 根据用户名获取用户信息
    * @param userName
    * @return com.aaa.model.User
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Select("select * from user_info where user_name =#{userName}")
    UserInfo getUserByUserName(String userName);
    /**
    * 根据用户名查询对应的角色id
    * @param userName
    * @return java.util.List<com.aaa.model.RoleInfo>
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Select("SELECT * from role_info WHERE role_id IN(select role_id from user_role_info WHERE user_id IN" +
            "(select user_id from user_info WHERE user_name=#{userName}))")
    List<RoleInfo> getRolesByUsername(String userName);
    /**
     * 根据用户名获取对应角色信息所对应的权限信息
     * @param userName
     * @return java.util.List<com.aaa.model.PermInfo>
     * @Author: cat
     * @Date: 2019/11/21
     */
    @Select("SELECT * from perm_info WHERE perm_id IN(SELECT perm_id from role_perm_info WHERE role_id IN(select role_id from user_role_info WHERE user_id IN(select user_id from user_info WHERE user_name =#{username})))")
    List<PermInfo> getPermByUserName(String userName);
    /**
    * 添加用户 用户状态默认为1
    * @param user
    * @return void
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Insert("insert into user_info(user_name,user_password,user_status) value (#{userName},#{userPassWord},#{userStatus})")
    void addUser(UserInfo user);
}
