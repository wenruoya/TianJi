package org.wr.tianji.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wr.tianji.pojo.entity.RiskCount;
import org.wr.tianji.pojo.entity.StatusCount;
import org.wr.tianji.pojo.entity.VulnCount;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrendVO {
    private Integer total;
    private Integer extended;
    private Integer sevenDay;
    private Integer finish;
    private List<StatusCount> status;
    private List<RiskCount> risk;
    private List<VulnCount> vuln;
}
