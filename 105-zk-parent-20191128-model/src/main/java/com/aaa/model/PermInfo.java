package com.aaa.model;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */

public class PermInfo {
    private Integer permId;
    private String resPermName;

    @Override
    public String toString() {
        return "PermInfo{" +
                "permId=" + permId +
                ", resPermName='" + resPermName + '\'' +
                '}';
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getResPermName() {
        return resPermName;
    }

    public void setResPermName(String resPermName) {
        this.resPermName = resPermName;
    }
}
