package com.codimiracle.application.platform.huidu.contract;

import lombok.Data;

@Data
public class Page {
    private int page = 1;
    private int limit = 10;

    public int getOffset() {
        return limit * (page - 1);
    }
}
