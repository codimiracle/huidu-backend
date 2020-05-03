package com.codimiracle.application.platform.huidu.util;/*
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

import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.service.TagService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TagUtil {
    public static List<Tag> mutateToPersistent(TagService service, List<String> queryTagNames) {
        List<Tag> tagList = service.findByTagNames(queryTagNames);
        tagList = OneToManyUtil.merge(tagList, queryTagNames, Tag::getName, Tag::new);
        return tagList;
    }

    public static List<Tag> filterNewTags(List<Tag> tags) {
        return tags.stream().filter((tag -> Objects.isNull(tag.getId()))).collect(Collectors.toList());
    }

    public static List<Tag> filterExistsTags(List<Tag> tags) {
        return tags.stream().filter(tag -> Objects.nonNull(tag.getId())).collect(Collectors.toList());
    }
}
