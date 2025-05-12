package com.tartayadir.certichain.blockchain.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tartayadir.certichain.blockchain.model.Block;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for JSON serialization and deserialization.
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts an object to JSON string.
     */
    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing to JSON", e);
        }
    }

    /**
     * Converts a JSON string to an object.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing from JSON", e);
        }
    }

    /**
     * Writes a list of objects to a JSON file.
     */
    public static void writeToFile(List<?> objects, String filePath) {
        try {
            mapper.writeValue(new File(filePath), objects);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }

    /**
     * Reads a list of objects from a JSON file.
     */
    public static List<Block> readFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            return mapper.readValue(file, new TypeReference<List<Block>>(){});
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filePath, e);
        }
    }
}