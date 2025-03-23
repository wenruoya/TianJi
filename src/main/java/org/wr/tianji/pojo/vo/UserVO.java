package org.wr.tianji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO implements Serializable {
    private Long userId;
    private String name;
    private String username;

    // 0 管理员 1 运营专家 2 安全工程师 3 开发工程师
    private int role;
    private String userNumber;
    private LocalDateTime createTime;
}
