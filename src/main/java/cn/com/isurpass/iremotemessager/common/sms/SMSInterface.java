package cn.com.isurpass.iremotemessager.common.sms;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.messageparser.MessageParser;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SMSInterface {

	private static Log log = LogFactory.getLog(SMSInterface.class);
	private static Map<Integer ,ISmsSender> SmsSenderMap = new ConcurrentHashMap<Integer , ISmsSender>();
	
	public static void initSmsSender(int platform , String signmen)
	{
		synchronized(SmsSenderMap)
		{
			SmsSenderMap.put(platform, new TencentSender(signmen));
		}
	}
	
	@Deprecated
	public static String sendSmsRequest(String postData, String postUrl) 
	{
        try {
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.error("connect failed!");
                return "";
            }
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
        	log.error("", e);
        }
        return "";
    }
	 
	 public static int sendSMS(String countrycode ,String phonenumber , String message , int platform )
	 {
		 phonenumber = adjustPhonenumber(countrycode , phonenumber);
		 ISmsSender sender = getSmsSender(countrycode , platform  );
		 sender.sendSMS(countrycode, phonenumber, message);
		 return 0 ;
	 }
	 
	 private static ISmsSender getSmsSender(String countrycode , int platform)
	 {
		 if ( !IRemoteConstantDefine.DEFAULT_COUNTRYCODE.equals(countrycode) && platform == 4 )
			 return new NorthAmericanPhoneUserSmsSender();
		 else if ( platform == IRemoteConstantDefine.PLATFORM_AMETA )
			 return new AmetaPhoneUserSmsSender();
		 else 
		 {
			ISmsSender ss = SmsSenderMap.get(platform);
			if ( ss != null )
				return ss ;
			return new TencentSender0();
		 }
	 }
	 
	 private static String adjustPhonenumber(String contrycode , String phonenumber)
	 {
		 if ( phonenumber == null )
			 return phonenumber ;
		 if ( ( IRemoteConstantDefine.INTERNATIONAL_DIALING_CODE_AUSTRALIA.equals(contrycode)  
				 || IRemoteConstantDefine.INTERNATIONAL_DIALING_CODE_NEWZEALAND.equals(contrycode) )
				 && phonenumber.startsWith("0") )
			 return phonenumber.substring(1);
		 return phonenumber ;
	 }
	 
	 
	 //Sending how many errors raise per 10 minutes 
	 public static void main(String arg[])
	 {
//		 JSONObject p = new JSONObject();
//		 for ( int i = 3 ; i < arg.length - 1 ; i ++ )
//			 p.put(arg[i], arg[i+ 1]  );
//
//		 MessageParser mp = new MessageParser(arg[2] , null ,p);
//		 sendSMS(arg[0] , arg[1] , mp , 0 );
	 }
	 
}
