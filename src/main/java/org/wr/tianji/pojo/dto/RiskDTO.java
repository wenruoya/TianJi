package org.wr.tianji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RiskDTO implements Serializable {
    private String riskName;
    private int repairPeriod;
}
