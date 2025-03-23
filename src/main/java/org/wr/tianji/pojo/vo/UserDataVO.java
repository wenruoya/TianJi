package org.wr.tianji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataVO {
    private String name;
    private List<String> roles;
    private String accessToken;
    private String username;
}
