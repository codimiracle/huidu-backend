package com.codimiracle.application.platform.huidu.entity.dto;

import lombok.Data;

@Data
public class SiginUpDTO {
    private String accountType;
    private String password;
    private UserDTO userdata;
}
