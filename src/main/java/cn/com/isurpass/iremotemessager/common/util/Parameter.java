package cn.com.isurpass.iremotemessager.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parameter {
    @Value("${cn.com.isurpass.activemq.brokerurl}")
    public String brokerUrl;

    @Value("${cn.com.isurpass.activemq.username}")
    public String username;

    @Value("${cn.com.isurpass.activemq.password}")
    public String password;
}
