package com.eleandy.admin.login.vo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
    饿汉式调用Hash加密
* */
public class HashSha {
    private static HashSha hashSha = new HashSha();
    private MessageDigest messageDigest;

    public HashSha() {
        super();
    }

    public static HashSha getHashSha() {
        return hashSha;
    }

    public String UserHash(String userPassword) {
        return Sha256(userPassword);
    }

    private String Sha256(String userPassword) {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        byte[] digest = messageDigest.digest(userPassword.getBytes(StandardCharsets.UTF_8));
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString(b & 0xFF));
        }
        return stringBuilder.toString();
    }
}
