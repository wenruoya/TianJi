package org.wr.tianji.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportPageVO {
    private Long reportId;
    private String reportName;
    private String riskName;
    private String vulnerabilityType;
    private String status;
    private LocalDateTime reportPeriod;
    private String secOwner;
    private String devOwner;
}
