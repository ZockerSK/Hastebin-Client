package de.zockersk.hastebinclient.util;

/**
 * Created by steven on 22.05.16.
 */
public class StringUtils {

    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

}
