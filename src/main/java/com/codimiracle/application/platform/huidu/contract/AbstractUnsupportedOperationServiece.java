package com.codimiracle.application.platform.huidu.contract;/*
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


import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public abstract class AbstractUnsupportedOperationServiece<K, T> implements Service<K, T> {
    @Override
    public void save(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(List<T> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(K id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteByIds(String ids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findById(K id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByIds(String ids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PageSlice<T> findAll(Page page) {
        throw new UnsupportedOperationException();
    }
}
