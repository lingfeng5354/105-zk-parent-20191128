package com.aaa.service;

import com.aaa.config.FtpClientProperties;
import com.aaa.ftp.FileNameUtil;
import com.aaa.ftp.FtpClient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FtpService {
    private static Logger logger = LoggerFactory.getLogger(FtpService.class);
    @Autowired
    private FtpClientProperties ftpClientProperties;

    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.获取文件的原始名称
        String oldFileName=file.getOriginalFilename();
        // 2.创建新的文件名称
        String newFileName= FileNameUtil.getFileName();
        // 3.截取原始名称的后缀
        String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
        // 4.把截取出的后缀拼接到新的文件名中
        newFileName=newFileName+substring;
        // 5.创建文件目标的目录
        String filePath = new DateTime().toString("yyyy/MM/dd");
        // 6.使用工具类进行连接Ftp和上传功能
        // 如何获取输入流？  file.getInputStream(); 所有的异常信息尽可能的精确！！！！
        try {
            Boolean aBoolean = FtpClient.upLoadFile(ftpClientProperties.getHost(), ftpClientProperties.getPort()
                    , ftpClientProperties.getUsername(), ftpClientProperties.getPassword()
                    , ftpClientProperties.getBasePath(), filePath, newFileName
                    , file.getInputStream());
            resultMap.put("result",aBoolean);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("result",false);
            logger.error(e.getMessage());
        }
        return resultMap;
    }
}
