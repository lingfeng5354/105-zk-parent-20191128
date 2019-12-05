package com.aaa.controller;


import com.aaa.model.UserInfo;
import com.aaa.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("outLogin")
    public String outLogin(){
        return "login";
    }

    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("login")
    public String login(String userName, String userPassword, ModelMap modelMap){
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);

            try {
                subject.login(token);
                return "index";
            } catch (UnknownAccountException uae) {
                modelMap.addAttribute("msg", uae.getMessage());
            } catch (IncorrectCredentialsException ice) {
                modelMap.addAttribute("msg", "密码错误");
                System.out.println("密码错误");
            } catch (LockedAccountException lae) {
                modelMap.addAttribute("msg", lae.getMessage());
                System.out.println("该账户已经锁定");
            }
            catch (AuthenticationException ae) {
                modelMap.addAttribute("msg", "认证失败");
            }
            return "login";
        } else {
            return "index";
        }
    }

    @RequestMapping("register")
    public String register(UserInfo user){
        System.out.println(user);
        System.out.println(user.getUserPassWord());
        String oldPassword = user.getUserPassWord();
        String username = user.getUserName();
        if(oldPassword == null || "".equals(oldPassword) || username == null || "".equals(username)) {

        }else {
            String hashAlgorithmName = "SHA-256";
            Integer hashIterations = 1024;
            SimpleHash newPassword = new SimpleHash(hashAlgorithmName, oldPassword, username, hashIterations);


            user.setUserPassWord(newPassword.toString());

            user.setUserStatus(1);
            userService.addUser(user);
        }
        return "login";
    }
}
