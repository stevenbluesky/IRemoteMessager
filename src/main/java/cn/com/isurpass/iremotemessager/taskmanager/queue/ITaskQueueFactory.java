package cn.com.isurpass.iremotemessager.taskmanager.queue;

public interface ITaskQueueFactory<T extends Runnable> 
{
	ITaskQueue<T> creatTaskQueue();
}
