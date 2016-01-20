package com.longder.note.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 工具类
 * Created by Longder on 2016/1/8.
 */
public class NoteUtil {
    /**
     * 密码加密处理
     *
     * @param msg 明文
     * @return 加密之后的密文
     */
    public static String md5(String msg) {
        String value = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] input = msg.getBytes();
            byte[] output = md.digest(input);
            value = Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 生成ID
     *
     * @return
     */
    public static String createId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
