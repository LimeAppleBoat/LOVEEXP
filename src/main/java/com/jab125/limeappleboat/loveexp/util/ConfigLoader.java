package com.jab125.limeappleboat.loveexp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    static Boolean aBoolean = true;
    public static void loadConfigs(File directory) {
        try {
            File LMR = new File(directory, "loveexp.boolean");
            if (LMR.createNewFile()) {
                fsdf(LMR, aBoolean);
            } else {
                String fileContents = IOUtils.toString(LMR.toURI(), StandardCharsets.UTF_8);
                aBoolean = new ObjectMapper().readValue(fileContents, new TypeReference<Boolean>() {
                });
                System.out.println(fileContents);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void loadMobs(File directory) {
        if (aBoolean) {
            try {
                File LMR = new File(directory, "loveexp_mob_registry.json");
                if (LMR.createNewFile()) {
                    fsdf(LMR, Util.REGISTERED_MOBS);
                } else {
                    String fileContents = IOUtils.toString(LMR.toURI(), StandardCharsets.UTF_8);
                    Util.REGISTERED_MOBS = new ObjectMapper().readValue(fileContents, new TypeReference<Map<String, LoveExpFormat>>() {

                    });
                    System.out.println(fileContents);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File LMR = new File(directory, "loveexp_mob_registry_force_lv.json");
                if (LMR.createNewFile()) {
                    String valueToWrite = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(Util.REGISTERED_MOBS_AUTO_LV);
                    System.out.println(valueToWrite);
                    FileWriter fileWriter = new FileWriter(LMR);
                    fileWriter.write(valueToWrite);
                    fileWriter.close();
                } else {
                    String fileContents = IOUtils.toString(LMR.toURI(), StandardCharsets.UTF_8);
                    Util.REGISTERED_MOBS_AUTO_LV = new ObjectMapper().readValue(fileContents, new TypeReference<Map<String, LoveExpForceLVFormat>>() {
                    });
                    System.out.println(fileContents);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File LMR = new File(directory, "loveexp_mob_registry_force_exp.json");
                if (LMR.createNewFile()) {
                    String valueToWrite = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(Util.REGISTERED_MOBS_AUTO_EXP);
                    System.out.println(valueToWrite);
                    FileWriter fileWriter = new FileWriter(LMR);
                    fileWriter.write(valueToWrite);
                    fileWriter.close();
                } else {
                    String fileContents = IOUtils.toString(LMR.toURI(), StandardCharsets.UTF_8);
                    Util.REGISTERED_MOBS_AUTO_EXP = new ObjectMapper().readValue(fileContents, new TypeReference<Map<String, LoveExpForceExpFormat>>() {
                    });
                    System.out.println(fileContents);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void fsdf(File LMR, Object object) throws IOException {
        String valueToWrite = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(object);
        System.out.println(valueToWrite);
        FileWriter fileWriter = new FileWriter(LMR);
        fileWriter.write(valueToWrite);
        fileWriter.close();
    }
}

