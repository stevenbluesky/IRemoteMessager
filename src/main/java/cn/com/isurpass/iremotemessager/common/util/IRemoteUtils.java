package cn.com.isurpass.iremotemessager.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
}
