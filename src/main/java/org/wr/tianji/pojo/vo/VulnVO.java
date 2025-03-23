package org.wr.tianji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VulnVO {
    private Long vulnId;
    // 修复类型
    private String vulnerabilityType;
    // 修改建议
    private String vulnerabilitySuggest;
}
