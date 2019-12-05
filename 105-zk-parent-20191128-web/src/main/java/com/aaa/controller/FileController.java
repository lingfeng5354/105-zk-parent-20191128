package com.aaa.controller;

import com.aaa.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class FileController {
    @Autowired
    private FtpService ftpService;
    @PostMapping("/upload")
    public String upload(@RequestParam("uploadFile") MultipartFile file) {

        Map<String, Object> resultMap = ftpService.upload(file);
        if (!(Boolean) resultMap.get("result")) {
            // 说明上传失败！！需要跳转到错误页面
            return "error";
        } else {
            return "success";
        }
    }
}
