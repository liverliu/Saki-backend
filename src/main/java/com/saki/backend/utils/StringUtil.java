package com.saki.backend.utils;

import com.saki.backend.exception.UtilException;

/**
 * Created by liverliu on 14-6-13.
 */
public class StringUtil {

    private StringUtil() {

    }

    public static boolean isEmpty(String S) {
        return S == null || S.isEmpty() ;
    }

    public static boolean isInteger(String S) {
        try {
            int val = Integer.parseInt(S);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveInteger(String S) {
        try {
            int tmp = Integer.parseInt(S);
            if(tmp <=0 ) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String S) {
        try {
            double val = Double.parseDouble(S);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String S) {
        try {
        	float val = Float.parseFloat(S);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String S) {
        try {
            long val = Long.parseLong(S);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static float toFloat(String S) {
        try {
            return Float.parseFloat(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Float", ex);
        }
    }

    public static int toInt(String S) {
        try {
            return Integer.parseInt(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Int", ex);
        }
    }
    
    public static int toInteger(String S) {
    	if(isEmpty(S)) return 0;
        try {
            return Integer.parseInt(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Int", ex);
        }
    }

    public static long toLong(String S) {
        try {
            return Long.parseLong(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Long", ex);
        }
    }

    public static short toShort(String S) {
        try {
            return Short.parseShort(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Short", ex);
        }
    }

    public static double toDouble(String S) {
        try {
            return Double.parseDouble(S);
        } catch (NumberFormatException ex) {
            throw new UtilException("not Double", ex);
        }
    }
    
}
