package org.wr.tianji.pojo.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}
