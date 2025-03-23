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
public class LogVO {

    // 事件
    private String event;
    // 时间
    private LocalDateTime logTime;
}
