package org.wr.tianji.pojo.dto;

import lombok.Data;

@Data
public class VulnPageQueryDTO {
    // 漏洞名
    private String vulnerabilityType;
    // 页码
    private int page;
    // 每页显示的记录数
    private int pageSize;
}
