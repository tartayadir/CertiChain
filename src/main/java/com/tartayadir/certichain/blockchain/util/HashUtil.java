package com.tartayadir.certichain.blockchain.util;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for SHA-256 hashing.
 */
public class HashUtil {

    /**
     * Applies SHA-256 hashing to the input string.
     */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error applying SHA-256", e);
        }
    }
}