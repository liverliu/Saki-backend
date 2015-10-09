package com.saki.backend.utils;

import org.apache.commons.beanutils.BeanMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liverliu on 14-6-12.
 */
public class BeanUtil {

    private BeanUtil(){

    }

    public static Map<String, Object> toMap(Object... objs) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Object object : objs) {
            if (object != null) {
                map.putAll(toMap(object));
            }
        }
        return map;
    }

    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        BeanMap beanMap = new BeanMap(obj);
        Iterator<String> it = beanMap.keyIterator();
        while (it.hasNext()) {
            String name = it.next();
            Object value = beanMap.get(name);
            // 去除类名
            if (value != null && !name.equals("class")) {
                if(value instanceof String) {
                    if(!StringUtil.isEmpty((String) value)) {
                        map.put(name, value);
                    }
                } else {
                    map.put(name, value);
                }
            }
        }
        return map;
    }
}
