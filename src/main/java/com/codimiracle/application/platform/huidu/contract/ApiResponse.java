package com.codimiracle.application.platform.huidu.contract;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Codimiracle
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;
}
