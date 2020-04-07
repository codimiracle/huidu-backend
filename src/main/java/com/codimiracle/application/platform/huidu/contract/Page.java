package com.codimiracle.application.platform.huidu.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private int page = 1;
    private int limit = 10;

    public int getOffset() {
        return limit * (page - 1);
    }
}
