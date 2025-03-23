package org.wr.tianji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report implements Serializable {
    private Long reportId;
    private String reportName;
    private String reportUrl;
    private String reportApp;
    private String reportContent;
    private String reportSource;
    // 工单状态
    private int status;
    private Long riskId;
    private Long vulnId;
    private Long secUserId;
    private Long devUserId;
    private LocalDateTime reportPeriod;
    private LocalDateTime CreateTime;
    private Long CreateUser;
    private String remark;
    private String feedback;
}
