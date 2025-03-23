package org.wr.tianji.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ReportVO {
    private Long reportId;
    private String reportName;
    private String reportUrl;
    private String reportApp;
    private String reportContent;
    private String reportSource;
    // 工单状态
    private int status;
    // 风险
    private Long riskId;
    private String riskName;
    // 漏洞
    private Long vulnId;
    private String vulnerabilityType;
    private String vulnerabilitySuggest;
    // 安全人员
    private Long secUserId;
    private String secName;
    // 开发人员
    private Long devUserId;
    private String devName;

    private LocalDateTime reportPeriod;
    private LocalDateTime CreateTime;
    private Long CreateUser;
    private String remark;
    private String feedback;
}
