package cn.com.isurpass.iremotemessager.init;

import cn.com.isurpass.iremotemessager.jms.JMSUtil;
import cn.com.isurpass.iremotemessager.jms.JSMTaskManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class AppDestroy{
    private static Log log = LogFactory.getLog(AppDestroy.class);

    @PreDestroy
    public void destroy() throws Exception {
        log.info("destroy");

        JSMTaskManager.shutdown();
        JMSUtil.close();
    }
}
