package com.codimiracle.application.platform.huidu.service;/*
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

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.entity.vt.Topic;

import java.util.List;

public interface TopicService extends Service<String, Topic> {
    void update(Topic topic, List<ContentReference> oldRefers);
    int deleteByIdLogically(String id);

    TopicVO findByIdIntegrally(String id);

    List<TopicVO> findAllIntegrally();

    PageSlice<TopicVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<TopicVO> findHotIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<TopicVO> findTopIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<TopicVO> findFocusTopicByReferenceIdIntegrally(String bookId, Filter filter, Sorter sorter, Page page);
}
