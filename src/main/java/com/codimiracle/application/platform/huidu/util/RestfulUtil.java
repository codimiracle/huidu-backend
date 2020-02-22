package com.codimiracle.application.platform.huidu.util;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.EntityData;
import com.codimiracle.application.platform.huidu.contract.PageSlice;

public class RestfulUtil {
    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, "success", data);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse<>(500, message, null);
    }

    public static ApiResponse warn(String message) {
        return new ApiResponse<>(600, message, null);
    }

    public static <T> ApiResponse entity(T entity) {
        return new ApiResponse<>(200, "success", new EntityData<>(entity));
    }

    public static <T> ApiResponse list(PageSlice<T> slice) {
        return new ApiResponse<>(200, "success", slice);
    }
}
