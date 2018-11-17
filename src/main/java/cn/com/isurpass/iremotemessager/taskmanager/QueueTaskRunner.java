package cn.com.isurpass.iremotemessager.taskmanager;

import cn.com.isurpass.iremotemessager.taskmanager.queue.ITaskQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.BlockingDeque;

public class QueueTaskRunner<T extends Runnable> implements Runnable {

	private static Log log = LogFactory.getLog(QueueTaskRunner.class);
	protected Object waitobj;
	protected BlockingDeque<ITaskQueue<T>> taskqueue;
	
	public QueueTaskRunner(BlockingDeque<ITaskQueue<T>> taskqueue , Object waitobj) {
		
		super();
		this.waitobj = waitobj;
		this.taskqueue = taskqueue;
	}


	@Override
	public void run() 
	{
		for ( ; ; )
		{
			try
			{
				if ( taskqueue.isEmpty() )
				{
					synchronized(waitobj)
					{
						waitobj.notify();
					}
				}
				
				ITaskQueue<T> q = taskqueue.takeFirst();
				
				if ( q.getowner() == false )
					continue;
				try
				{
					T t = q.getTask();
					if ( t != null )
					{
						TaskWrap tw = new TaskWrap(t);
						tw.run();
					}
				}
				finally
				{
					q.release();
				}
			}
			catch(InterruptedException e)
			{
				log.info(e.getMessage()); 
				return ;
			}
			catch(Throwable t )
			{
				log.error(t.getMessage(),t);
			}
			if ( Thread.currentThread().isInterrupted())
				return ;
		}
	}

}
