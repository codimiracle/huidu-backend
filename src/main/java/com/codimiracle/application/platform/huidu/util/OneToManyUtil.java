package com.codimiracle.application.platform.huidu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OneToManyUtil {
    public static <T, U> List<T> merge(List<T> tList, List<U> uList, Function<T, U> mapper, Function<U, T> uToT) {
        Map<U, T> map = tList.stream().collect(Collectors.toMap(mapper, (t) -> t));
        uList.stream().map(uToT).forEach((t) -> {
            if (!map.containsKey(mapper.apply(t))) {
                map.put(mapper.apply(t), t);
            }
        });
        return new ArrayList<>(map.values());
    }
}
