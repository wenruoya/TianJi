package org.wr.tianji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vuln implements Serializable {
    private Long vulnId;
    // 修复类型
    private String vulnerabilityType;
    // 修改建议
    private String vulnerabilitySuggest;
}
