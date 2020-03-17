package com.codimiracle.application.platform.huidu.util;
/*
 *  MIT License
 *
 *  Copyright (c) 2019 Huang Lin
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, Publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.*;
import java.util.function.Function;


/**
 * 用于把字符串与集合等数据结构的互转
 * 只能处理每个元素没有包含英文逗号的情况
 *
 * @author Codimiracle
 */
public class StringifizationUtil {
    public static final String SEPARATOR_STRINGIFIED_SET = ",";

    public static String toString(Set<String> set) {
        return toString(set, s -> s);
    }

    public static String toString(List<String> set) {
        return toString(set, s -> s);
    }

    public static <T> String toString(Iterator<T> iterator, Function<T, String> tToString) {
        StringBuilder builder = new StringBuilder();
        String sep = "";
        while (iterator.hasNext()) {
            T t = iterator.next();
            builder.append(sep);
            builder.append(tToString.apply(t));
            sep = SEPARATOR_STRINGIFIED_SET;
        }
        return builder.toString();
    }

    public static <T> String toString(List<T> list, Function<T, String> tToString) {
        return toString(list.iterator(), tToString);
    }

    public static <T> String toString(Set<T> set, Function<T, String> tToString) {
        return toString(set.iterator(), tToString);
    }

    public static Set<String> toSet(String stringifiedSet) {
        return toSet(stringifiedSet, s -> s);
    }

    public static <T> Set<T> toSet(String stringifiedSet, Function<String, T> stringToT) {
        if (Objects.isNull(stringifiedSet)) {
            return Collections.emptySet();
        }
        Set<T> set = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(stringifiedSet, SEPARATOR_STRINGIFIED_SET);
        while (tokenizer.hasMoreTokens()) {
            set.add(stringToT.apply(tokenizer.nextToken()));
        }
        return Collections.unmodifiableSet(set);
    }

    public static <T> List<T> toList(String stringifiedSet, Function<String, T> stringToT) {
        if (Objects.isNull(stringifiedSet)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(stringifiedSet, SEPARATOR_STRINGIFIED_SET);
        while (tokenizer.hasMoreTokens()) {
            list.add(stringToT.apply(tokenizer.nextToken()));
        }
        return Collections.unmodifiableList(list);
    }

    public static List<String> toList(String stringifiedSet) {
        return new ArrayList<>(toList(stringifiedSet, s -> s));
    }
}
