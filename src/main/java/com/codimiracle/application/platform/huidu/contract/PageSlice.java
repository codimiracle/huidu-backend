package com.codimiracle.application.platform.huidu.contract;

import lombok.Data;

import java.util.List;

@Data
public class PageSlice<T> {
    private int page;
    private int limit;
    private List<T> list;
    private long total;
    public PageSlice() {}
    public PageSlice(List<T> list) {
        this.page = 1;
        this.limit = list.size();
        this.list = list;
        this.total = list.size();
    }
}
