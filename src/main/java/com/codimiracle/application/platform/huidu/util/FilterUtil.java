package com.codimiracle.application.platform.huidu.util;

import com.codimiracle.application.platform.huidu.contract.Filter;

import java.util.Objects;

public class FilterUtil {
    public static Filter ensureNotNull(Filter filter) {
        return Objects.isNull(filter) ? new Filter() : filter;
    }
}
