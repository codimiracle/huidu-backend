package com.codimiracle.application.platform.huidu.enumeration;
/*
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

/**
 * @author Codimiracle
 */

public enum ContentType {
    /**
     * 内容是一个话题
     */
    Topic("topic", 110),
    /**
     * 内容是一个点评
     */
    Review("review", 120),
    /**
     * 内容是一个评论
     */
    Comment("comment", 100),
    /**
     * 图书数据
     */
    Book("book", 200),
    /**
     * 电子书章节
     */
    Episode("episode", 210),

    /**
     * 有声书章节
     */
    AudioEpisode("audio-episode", 211);
    private final String type;
    private final int code;

    ContentType(String type, int code) {
        this.type = type;
        this.code = code;
    }

    public static ContentType valueOfCode(String code) {
        return Arrays.stream(ContentType.values()).filter((e) -> Objects.equals(e.type, code)).findFirst().orElseGet(() -> Enum.valueOf(ContentType.class, code));
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
