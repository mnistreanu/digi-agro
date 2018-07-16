package com.arobs.utils;

import com.google.gson.Gson;

import java.util.Collection;

public class StaticUtil {

    public static Gson gson = new Gson();

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
