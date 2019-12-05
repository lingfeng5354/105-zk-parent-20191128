package com.aaa.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

public class FtpClient {
    public static Boolean upLoadFile(String host, int port, String username,
                                     String password, String basePath,
                                     String filePath, String filename,
                                     InputStream inputStream) throws IOException {
        //创建临时路径
        String tempPath="";
        //创建FTPClient对象（是ftp进行连接、断开连接和上传的重要对象)
        FTPClient ftpClient = new FTPClient();
        //连接ftp服务器
        ftpClient.connect(host,port);
        //登录ftp用户
        ftpClient.login(username,password);
        //连接后返回状态码
        //replyCode:230表示连接成功，530表示连接和登录失败
        int replyCode = ftpClient.getReplyCode();
        //判断状态码是否在200和300之间
        if (!FTPReply.isPositiveCompletion(replyCode)){
            ftpClient.disconnect();
            return false;
        }
        // changeWorkingDirectory()：检测目录地址(/home/ftp/www/2019/11/22)是否存在
        // basePath:/home/ftp/www
        // filePath:当前的日期(2019/11/22)
        // /home/ftp/www/2019/11/22
        // 返回Boolean类型，如果返回true则说明目录存在，不需要创建目录，
        // 如果返回false则说明目录不存在需要创建
        String fullPath = basePath+"/"+filePath;
        if (!ftpClient.changeWorkingDirectory(fullPath)){
            tempPath = basePath;
            String[] split = filePath.split("/");
            for (String s : split) {
                if (s==null||"".equals(s)){
                    continue;
                }
                tempPath +="/"+s;
                if (!ftpClient.changeWorkingDirectory(tempPath)){
                    if (!ftpClient.makeDirectory(tempPath)){
                     return false;
                    }
                }
            }
            if (!ftpClient.changeWorkingDirectory(tempPath)) {
                return false;
            }
        }
        // 11.把文件以字符流的形式进行上传(开启字符流上传的模式)
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //判断上传是否成功
        if (!ftpClient.storeFile(filename,inputStream)){
            return false;
        }
        //关闭资源
        inputStream.close();
        ftpClient.logout();
        if (ftpClient.isConnected()){
            //断开连接
            ftpClient.disconnect();
        }
        return true;
    }
}
