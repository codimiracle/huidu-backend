package com.codimiracle.application.platform.huidu.enumeration;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
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

import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;

import java.util.Objects;

public enum PassingPointStatus {
    /**
     * 正在经过这个出发地
     */
    Doing("doing"),
    /**
     * 在这个出发点的工作已经完成
     */
    Done("done")
    ;
    private final String status;

    PassingPointStatus(String status) {
        this.status = status;
    }

    public static PassingPointStatus valueOfCode(String code) {
        if (Objects.equals(code, "doing")) {
            return Doing;
        }
        if (Objects.equals(code, "done")) {
            return Done;
        }
        return Enum.valueOf(PassingPointStatus.class, code);
    }

    @Override
    public String toString() {
        return this.status;
    }
}
