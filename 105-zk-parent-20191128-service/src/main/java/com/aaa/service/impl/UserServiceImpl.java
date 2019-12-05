package com.aaa.service.impl;

import com.aaa.mapper.UserMapper;
import com.aaa.model.PermInfo;
import com.aaa.model.RoleInfo;
import com.aaa.model.UserInfo;
import com.aaa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserInfo getUserByUserName(String userName) {
        UserInfo userByUserName = userMapper.getUserByUserName(userName);
        if (userByUserName==null){
            logger.warn("user is null");
            return null;
        }else {
            return userByUserName;
        }
    }

    @Override
    public List<RoleInfo> getRolesByUsername(String userName) {
        List<RoleInfo> roleInfoList = userMapper.getRolesByUsername(userName);
        if (roleInfoList==null && roleInfoList.size()<=0){
            logger.warn("role is null");
            return null;
        }else {
            return roleInfoList;
        }

    }

    @Override
    public List<PermInfo> getPermByUserName(String userName) {
        List<PermInfo> permInfoList = userMapper.getPermByUserName(userName);
        if (permInfoList.size()<=0&&permInfoList==null){
            logger.warn("perm is null");
            return null;
        }else {
            return permInfoList;
        }
    }

    @Override
    public void addUser(UserInfo user) {
        if (user == null){
            logger.warn("user is null");
        }else {
            userMapper.addUser(user);
        }
    }
}
