package org.wr.tianji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationVO {
    private String notificationName;
    // 配置内容
    private String notificationContent;
}
