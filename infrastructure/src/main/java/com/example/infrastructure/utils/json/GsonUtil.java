package com.example.infrastructure.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * @program: aioh_sw_robot
 * @description: JSON工具类
 * @author:
 * @create: 2019-11-07 17:02
 **/
public class GsonUtil {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }

    public static Object fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }

    public static Object fromJson(Reader reader, Type type) {
        return gson.fromJson(reader, type);
    }
}
