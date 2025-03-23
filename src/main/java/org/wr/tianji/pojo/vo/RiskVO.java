package org.wr.tianji.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiskVO implements Serializable {
    private Long riskId;
    private String riskName;
    private int repairPeriod;
}
