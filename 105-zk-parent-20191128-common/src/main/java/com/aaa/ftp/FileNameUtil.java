package com.aaa.ftp;

import java.util.Random;

/**
 * @Author:祁继港
 * @Date:2019/11/28 16:38
 */
public class FileNameUtil {
    public static String getFileName(){
        //获取当前系统时间的毫秒数
        long timeMillis = System.currentTimeMillis();
        //生成随机数
        Random random = new Random();
        int ran = random.nextInt(999);
        // %:占位符   03:三位(如果不足三位往前补0)  d:数字
        String fileName=timeMillis+String.format("%03d",ran);
        return fileName;
    }
}
