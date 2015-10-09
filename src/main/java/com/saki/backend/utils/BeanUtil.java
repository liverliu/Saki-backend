package com.saki.backend.utils;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanMap;

import java.util.Map;

/**
 * Created by liverliu on 14-6-12.
 */
public class BeanUtil {

    private BeanUtil(){

    }

    public static Map<String, Object> toMap(Object... objs) {
        Map<String, Object> map = Maps.newHashMap();
        for (Object object : objs) {
            if (object != null) {
                map.putAll(toMap(object));
            }
        }
        return map;
    }

    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = Maps.newHashMap();
        if (obj != null) {
            new BeanMap(obj).entrySet().stream()
                    .filter(e -> !e.getKey().toString().equals("class")) //去除类名
                    .filter(e -> e.getValue() != null) //去null
                    .filter(e -> !e.getValue().toString().equals("")) //去空串
                    .forEach(e -> map.put((String) e.getKey(), e.getValue()));
        }
        return map;
    }
}
