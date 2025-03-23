package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private String name;
    private String username;
    // 0 管理员 1 运营专家 2 安全工程师 3 开发工程师
    private int role;
    private String userNumber;
}
