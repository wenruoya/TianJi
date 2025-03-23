package org.wr.tianji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {

    private Long id;
    // 关联工单
    private Long reportId;
    // 事件
    private String event;
    // 时间
    private LocalDateTime logTime;

}
