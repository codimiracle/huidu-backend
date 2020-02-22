package com.codimiracle.application.platform.huidu.contract;

import lombok.Data;

import java.util.List;

@Data
public class PageSlice<T> {
    private int page;
    private int limit;
    private List<T> list;
    private long total;
}
