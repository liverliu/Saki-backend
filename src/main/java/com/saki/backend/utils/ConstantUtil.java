package com.saki.backend.utils;

import com.betterwood.devine.core.order.dto.orderInfo.OrderInfoDB;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by liverliu on 14-6-12.
 */
public class ConstantUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstantUtil.class);

    private ConstantUtil() {

    }

    private static Properties constant = new Properties();

    static {
        InputStreamReader is = null;
        try {
            //read properties
            is = new InputStreamReader(ConstantUtil.class.getResourceAsStream("/properties/constant.properties"), "UTF-8");
            if (is != null) {
                constant.load(is);
            }
        } catch (IOException ex) {
            LOGGER.error("读取constant.properties出错", ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    LOGGER.error("读取constant.properties出错", ex);
                }
            }
        }
    }
    public static String getProperty(String key) {
        return constant.getProperty(key, "");
    }

    public static int getInt(String key) {
        return Integer.parseInt(constant.getProperty(key, "0"));
    }

    public static int getInt(String key, int defaultCount) {
    	return StringUtil.toInt(constant.getProperty(key, Integer.toString(defaultCount)));
    }
    public static boolean getBool(String key) {
        return Boolean.parseBoolean(constant.getProperty(key, "false"));
    }


    public static final String SELECT_COUNT = "selectCount";
    public static final String SELECT = "select";
    public static final String SELECT_BY_ID = "selectById";
    public static final String SELECT_PAGE = "selectPage";
    public static final String SELECT_PAGE_COUNT = "selectPageTotal";

    public static final String INSERT = "insert";
    public static final String UPDATE = "update";

}
