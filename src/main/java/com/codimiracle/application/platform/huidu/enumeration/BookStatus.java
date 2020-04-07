package com.codimiracle.application.platform.huidu.enumeration;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, Publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.Arrays;
import java.util.Objects;

public enum BookStatus {
    /**
     * 审批中
     */
    Examining("examining"),
    /**
     * 驳回
     */
    Rejected("rejected"),
    /**
     * 连载中
     */
    Serializing("serializing"),
    /**
     * 停更
     */
    Paused("paused"),
    /**
     * 完结
     */
    Ended("ended");

    private String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static BookStatus valueOfCode(String code) {
        if (Objects.isNull(code)) {
            return null;
        }
        return Arrays.stream(BookStatus.values()).filter((e) -> Objects.equals(e.status, code)).findFirst().orElseGet(() ->Enum.valueOf(BookStatus.class, code));
    }

    @Override
    public String toString() {
        return this.status;
    }
}