package cn.com.isurpass.iremotemessager.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IRemoteUtils {
    public static boolean isNotBlank(Integer values){
        return values != null && values != 0;
    }

    public static boolean isNotBlank(List list) {
        return list != null && list.size() != 0;
    }
    public static boolean isNotBlank(Map map) {
        return map != null && map.size() != 0;
    }

    public static boolean isBlank(List list) {
        return !isNotBlank(list);
    }

    public static boolean isBlank(Map map) {
        return !isNotBlank(map);
    }

    public static boolean isBlank(Integer values){
        return !isNotBlank(values);
    }
    public static Date parseTime(String time)
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null ;
        try {
            d = sf.parse(time);
        } catch (ParseException e) {
            return null;
        }
        return d ;
    }

    public static String toString(Collection<String> destList, String dest) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = destList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (StringUtils.isNotBlank(s)) {
                sb.append(s);
                sb.append(dest);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(dest));
        return sb.toString();
    }
}
