package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryDTO implements Serializable {
    //员工姓名
    private String name;
    private String userNumber;
    // 页码
    private int page;
    // 每页显示的记录数
    private int pageSize;
}
