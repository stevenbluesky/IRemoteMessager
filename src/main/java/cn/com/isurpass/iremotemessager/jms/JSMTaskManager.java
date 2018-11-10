package cn.com.isurpass.iremotemessager.jms;


import cn.com.isurpass.iremotemessager.taskmanager.QueueTaskManager;

public class JSMTaskManager
{
	private static QueueTaskManager<Runnable> taskmgr = new QueueTaskManager<Runnable>();
	
	public static void addTask(String key , Runnable task ) throws IllegalStateException 
	{
		taskmgr.addTask(key, task);
	}
	
	public static void shutdown()
	{
		taskmgr.shutdown();
	}
}
