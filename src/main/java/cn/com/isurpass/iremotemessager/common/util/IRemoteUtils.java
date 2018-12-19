package cn.com.isurpass.iremotemessager.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        int i = sb.lastIndexOf(dest);
        if ( i != -1 )
        	sb.deleteCharAt(i);
        return sb.toString();
    }

    public static int[] jsontoIntArray(String ary){
        if ( ary == null || ary.length() == 0 )
            ary = "[]";
        JSONArray ja = JSON.parseArray(ary);
        Integer[] ia = ja.toArray(new Integer[0]);

        int[] ita = new int[ia.length];
        for ( int i = 0 ; i < ia.length; i ++ )
            ita[i] = ia[i] ;
        return ita;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }
}
