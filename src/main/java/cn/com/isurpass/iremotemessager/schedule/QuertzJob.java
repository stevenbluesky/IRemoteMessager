package cn.com.isurpass.iremotemessager.schedule;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class QuertzJob implements Job {

    private static Log log = LogFactory.getLog(QuertzJob.class);

    /*@Resource(name="transactionManager")
    private PlatformTransactionManager transactionManager;*/

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        if ( log.isInfoEnabled() )
        {
            JobKey jk = context.getJobDetail().getKey();
            log.info(String.format("Timer task fired, key = %s_%s", jk.getName() , jk.getGroup()));
        }

        JobDataMap data = context.getJobDetail().getJobDataMap();
        Object obj = data.get("task");
        if ( !( obj instanceof Runnable) )
            return ;

        /*TransactionStatus transaction = null;*/
        try
        {
            /*transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());*/

            Runnable run = (Runnable)obj;
            run.run();	// quertz job is running in a thread;

            /*transactionManager.commit(transaction);*/
        }
        catch(Throwable t)
        {
/*            transactionManager.rollback(transaction);*/
            log.error(t.getMessage() ,t);
        }
        finally
        {
//            JMSUtil.commitmessage();
        }

    }

}