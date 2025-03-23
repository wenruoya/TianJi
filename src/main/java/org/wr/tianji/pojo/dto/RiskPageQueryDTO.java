package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RiskPageQueryDTO implements Serializable {
    //风险名称
    private String riskName;
    // 页码
    private int page;
    // 每页显示的记录数
    private int pageSize;
}
