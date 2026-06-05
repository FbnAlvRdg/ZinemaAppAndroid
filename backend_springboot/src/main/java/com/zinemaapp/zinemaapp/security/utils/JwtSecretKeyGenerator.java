package com.zinemaapp.zinemaapp.security.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

public class JwtSecretKeyGenerator {
    public static void generateSecretKey(){
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(base64);
    }
}
