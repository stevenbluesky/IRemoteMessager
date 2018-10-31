package cn.com.isurpass.iremotemessager.util;

import java.util.List;

public class IRmoteUtils {
    public static boolean isNotBlank(Integer values){
        return values != null && values != 0;
    }

    public static boolean isNotBlank(List list) {
        return list != null && list.size() != 0;
    }

    public static boolean isBlank(List list) {
        return !isNotBlank(list);
    }
}
