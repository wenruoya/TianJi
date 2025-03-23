package org.wr.tianji.pojo.dto;

import lombok.Data;

@Data
public class ReportPageQueryByRoleDTO {
    private String reportName;
    private Integer riskId;
    private Integer period;
    private String username;

    private int page;
    private int pageSize;
}
