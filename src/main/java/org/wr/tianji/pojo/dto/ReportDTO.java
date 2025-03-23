package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDTO {
    private String reportName;
    private String reportUrl;
    private String reportApp;
    private String reportSource;
    private Long riskId;
    private Long vulnId;

    private String reportContent;

}
