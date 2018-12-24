package cn.com.isurpass.iremotemessager.common.constant;

public class IRemoteConstantDefine {
    public static final int DEFAULT_PLATFORM = 999999;
    public static final String DEFAULT_EVENT_CODE = "defaultEventCode";
    public static final int PROCESSOR_CLASS_TYPE_PUSH_TARGET_DECISION = 1;
    public static final int PROCESSOR_CLASS_TYPE_PUSH_METHOD= 2;
    public static final int PROCESSOR_CLASS_TYPE_CONTENT_PARSER = 3;
    public static final int PROCESSOR_CLASS_TYPE_PUSH = 4;

    public static final int PROCESSOR_SUB_TYPE_NOTIFICATION_JPUSH_NOTIFICATION = 1;
    public static final int PROCESSOR_SUB_TYPE_NOTIFICATION_JPUSH_MESSAGE = 2;
    public static final int PROCESSOR_SUB_TYPE_NOTIFICATION_SMS = 3;
    public static final int PROCESSOR_SUB_TYPE_NOTIFICATION_MAIL = 4;
    
    public final static int MESSAGE_PARSE_TYPE_JPUSHMESSAGE = 1;
    public final static int MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION = 2;
    public final static int MESSAGE_PARSE_TYPE_SMS = 3;
    public final static int MESSAGE_PARSE_TYPE_MAIL = 4;

    public final static int MESSAGE_SENDER_TYPE_JPUSHMESSAGE = 1;
    public final static int MESSAGE_SENDER_TYPE_JPUSHNOTIFICATION = 2;
    public final static int MESSAGE_SENDER_TYPE_SMS = 3;
    public final static int MESSAGE_SENDER_TYPE_MAIL = 4;

    public static final int USER_SHARE_STATUS_WAIT_FOR_RESPONSE = 0 ;
    public static final int USER_SHARE_STATUS_NORMAL = 1 ;

    public static final int USER_SHARE_TYPE_NORMAL = 0;//share type :friend
    public static final int USER_SHARE_TYPE_FAMILY = 1;

    public static final int USER_SHARE_DEVICE_TYPE_ALL = 0;
    public static final int USER_SHARE_DEVICE_TYPE_SPECIFY = 1;

    public static final int DEVICE_TYPE_ZWAVE_DEVICE = 1;
    public static final int DEVICE_TYPE_INFRARED_DEVICE = 2;
    public static final int DEVICE_TYPE_CAMERA = 3;

    public static final int PHONEUSER_ARM_STATUS_ARM = 1;

    public static final String DEVICE_MAJORTYPE_ZWAVE = "zWave";
    public static final String DEVICE_MAJORTYPE_INFRARED = "infrared";
    public static final String DEVICE_MAJORTYPE_CAMERA = "camera";

    public final static int NOTIFICATION_SETTING_TYPE_NOTIFICATION = 4 ;
    public static final int WARNING_SETTING_TYPE_MAIL = 5;

    public static final int REMOTE_POWER_TYPE_BATTERY = 1 ;
    public static final int REMOTE_POWER_TYPE_UNKNOW = 2 ;

    public static final int SCENE_ENABLESTATUS_NO = 0;
    public static final int SCENE_ENABLESTATUS_YES = 1;

    public static final String DEFAULT_COUNTRYCODE = "86";
    public static final String INTERNATIONAL_DIALING_CODE_CHINE = "86";
    public static final String INTERNATIONAL_DIALING_CODE_NORTH_AMERICA = "1";
    public static final String INTERNATIONAL_DIALING_CODE_AUSTRALIA = "61";
    public static final String INTERNATIONAL_DIALING_CODE_NEWZEALAND = "64";


    public static final int PLATFORM_ZHJW = 0;
    public static final int PLATFORM_ASININFO = 1;
    public static final int PLATFORM_CHUANGJIA = 2;
    public static final int PLATFORM_DORLINK = 3;
    public static final int PLATFORM_NORTH_AMERICAN = 4;
    //public static final int PLATFORM_NORTH_COBBE = 5;
    public static final int PLATFORM_XIAOHU = 6;
    public static final int PLATFORM_SINGAPORE = 7;
    public static final int PLATFORM_KEEMPLE = 8;
    public static final int PLATFORM_AMETA = 9;

    public static final String SYSTEMPARAMETER_USER_MAIL_SERVER_HOST = "user_mail_server_host";
    public static final String SYSTEMPARAMETER_USER_MAIL_SERVER_POST = "user_mail_server_post";
    public static final String SYSTEMPARAMETER_USER_MAIL_USERNAME = "user_mail_username";
    public static final String SYSTEMPARAMETER_USER_MAIL_PASSWORD = "user_mail_password";
    public static final String SYSTEMPARAMETER_USER_MAIL_FROMADDRESS = "user_mail_fromaddress";
    public static final String SYSTEMPARAMETER_USER_MAIL_VALIDATE = "user_mail_validate";

    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_SERVER_HOST = "support_mail_server_host";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_SERVER_POST = "support_mail_server_post";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_USERNAME = "support_mail_username";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_PASSWORD = "support_mail_password";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_FROMADDRESS = "support_mail_fromaddress";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_VALIDATE = "support_mail_validate";
    public static final String SYSTEMPARAMETER_SUPPORT_MAIL_TOADDRESS = "support_mail_toaddress";

    public static final String QUARTZ_GROUP_DEVICE_TIMER = "QUARTZ_GROUP_DEVICE_TIMER";

    public static final String SMS_COUNT = "smscount";
    public static final String SMS_RUN_OUT = "smsrunout";
    public static final String REMAINING_NUMBER = "remainingnumber";
    public static final String KEY_DEFAULT_LANGUAGE = "defaultlanguage";
    public static final String DEFAULT_LANGUAGE = "en_US";
}
