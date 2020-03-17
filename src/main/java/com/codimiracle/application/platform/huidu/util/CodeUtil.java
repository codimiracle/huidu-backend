package com.codimiracle.application.platform.huidu.util;

import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

public class CodeUtil {

    /**
     * 获取 18 位随机码
     *
     * @param plainText String
     * @return 18 位随机码
     */
    private static String randomCode(String plainText, int size) {
        String base64Str = Base64.encodeBase64URLSafeString(DigestUtils.sha256(plainText));
        int start = RandomUtils.nextInt(0, base64Str.length() - size);
        return base64Str.substring(start, start + (size - 1));
    }

    /**
     * 20 位引用数据 id
     *
     * @return
     */
    public static String getReferenceId() {
        return "RD" + randomCode(String.format("reference-data-%d-%s", System.currentTimeMillis(), UUID.randomUUID().toString()), 18);
    }

    /**
     * 获取迷你码
     */
    public static String getMiniCode() {
        return randomCode(String.format("mini-code-%d-%s", System.currentTimeMillis(), UUID.randomUUID().toString()), 6);
    }

    /**
     * 给内容预先生成一个 id
     * 格式如下所示：
     * 000      00000000 00000
     * ---      -------- -----
     * 内容码   时间码   随机码
     *
     * @param contentType
     * @return
     */
    public synchronized static String getContentId(ContentType contentType) {
        int contentCode = contentType.getCode();
        long timeCode = System.currentTimeMillis() % 100000000;
        String randomCode = RandomStringUtils.randomNumeric(5);
        return String.format("%d%d%s", contentCode, timeCode, randomCode);
    }
}