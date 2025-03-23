package org.wr.tianji.pojo.dto;

import lombok.Data;

@Data
public class VulnDTO {
    // 修复类型
    private String vulnerabilityType;
    // 修改建议
    private String vulnerabilitySuggest;
}
