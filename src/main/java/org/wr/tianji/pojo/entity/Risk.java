package org.wr.tianji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Risk implements Serializable {
    private Long riskId;
    private String riskName;
    private int repairPeriod;
}
