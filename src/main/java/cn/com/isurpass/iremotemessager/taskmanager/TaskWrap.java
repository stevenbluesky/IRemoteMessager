package cn.com.isurpass.iremotemessager.taskmanager;

import cn.com.isurpass.iremotemessager.jms.JMSUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskWrap implements Runnable {

	private static Log log = LogFactory.getLog(TaskWrap.class);

    private Runnable task ;

	public TaskWrap(Runnable task) {
		super();
		this.task = task;
	}
	
	@Override
	public void run() {
		
		ThreadNameHelper.changeThreadname(task);

		try
		{
			task.run();
		}
		catch(Throwable t)
		{
			try
			{
				log.error(t.getMessage(), t);
            }
			catch(Throwable t1){};
		}

//		JMSUtil.commitmessage();
		ThreadNameHelper.clearThreadname();
	}

}
