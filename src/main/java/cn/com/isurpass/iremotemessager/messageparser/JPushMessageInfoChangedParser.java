package cn.com.isurpass.iremotemessager.messageparser;

import cn.com.isurpass.iremotemessager.framework.IMessageParser;
import cn.com.isurpass.iremotemessager.jpush.JPushHelper;
import cn.com.isurpass.iremotemessager.jpush.vo.Payload;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.messageparser.JPushMessageInfoChangedParser")
public class JPushMessageInfoChangedParser implements IMessageParser<JPushMessageData> {

    @Override
    public List<JPushMessageData> parse(EventData data, JPushMessageData targetdata) {
        String extdata = getInfoChangedMessage();

        Payload payload = JPushHelper.createMessage(targetdata.getAliases(), JSONObject.parseObject(extdata));
        targetdata.setPayload(payload);

        return Arrays.asList(new JPushMessageData[]{targetdata});
    }

    public String getInfoChangedMessage(){
        JSONObject json = new JSONObject();
        json.put("type", "infochanged");
        json.put("pushtime", System.currentTimeMillis());
        return json.toJSONString();
    }
}
