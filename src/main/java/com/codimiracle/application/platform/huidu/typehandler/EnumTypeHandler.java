package com.codimiracle.application.platform.huidu.typehandler;/*
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

import com.codimiracle.application.platform.huidu.enumeration.*;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@MappedTypes({
        ActivityStatus.class,
        BookStatus.class,
        BookType.class,
        CommodityStatus.class,
        CommodityType.class,
        ContentStatus.class,
        ContentType.class,
        OperationType.class,
        PaymentType.class,
        OrderType.class,
        OrderStatus.class,
        PassingPointStatus.class,
        CategoryType.class,
        BookAudioEpisodeStatus.class,
        SubscribeType.class,
        NotificationType.class
})
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    private String parseToName(String value) {
        return Arrays.stream(value.split("-")).map((s) -> s.substring(0, 1).toUpperCase() + s.substring(1)).collect(Collectors.joining());
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String enumRaw = rs.getString(columnName);
        return Objects.isNull(enumRaw) ? null : Enum.valueOf(type, parseToName(enumRaw));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String enumRaw = parseToName(rs.getString(columnIndex));
        return Objects.isNull(enumRaw) ? null : Enum.valueOf(type, enumRaw);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String enumRaw = parseToName(cs.getString(columnIndex));
        return Objects.isNull(enumRaw) ? null : Enum.valueOf(type, enumRaw);
    }
}
