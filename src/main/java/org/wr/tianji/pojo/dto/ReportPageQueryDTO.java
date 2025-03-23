package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportPageQueryDTO implements Serializable {
    private String reportName;
    private Integer status;
    private Integer riskId;
    private Integer period;

    private int page;
    private int pageSize;
}
