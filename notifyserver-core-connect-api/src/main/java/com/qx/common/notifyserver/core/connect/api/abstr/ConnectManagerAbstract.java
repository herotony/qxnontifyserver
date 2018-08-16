package com.qx.common.notifyserver.core.connect.api.abstr;

import com.qx.common.notifyserver.core.connect.api.interf.ConnectAgent;
import com.qx.common.notifyserver.core.connect.api.interf.ConnectManager;
import com.qx.common.notifyserver.core.connect.api.interf.QueueAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by saosin on 2018/8/16.
 */
public abstract class ConnectManagerAbstract implements ConnectManager {

    private final static Logger logger = LoggerFactory.getLogger(ConnectAgentAbstract.class);

    private long suspendTime = 5*60*1000;

    //确保当前线程修改为最新值时，其他线程都知道
    protected volatile boolean stopSign = false;

    protected ConcurrentMap<String,QueueAgent> queueAgentMap = new ConcurrentHashMap<String, QueueAgent>();
    protected ConcurrentMap<Long,ConnectAgent> connectAgentMap = new ConcurrentHashMap<Long, ConnectAgent>();

    protected ExecutorService executorService = Executors.newSingleThreadExecutor();
    protected CountDownLatch stopCountDownLatch;
    protected long moduleId;

    protected abstract void updateConnectAgent();
    protected abstract void destroyConnectAgent();


    public synchronized void startUpdateConnectAgentImmediately(){

        //notifyAll是针对整个ConnectManagerAbstract对象而言，这里也就是针对suspendUpdateConnectAgent内的wait方法而言
        //即无需等待5分钟，可以直接立刻唤醒并执行
        notifyAll();
    }

    protected synchronized void suspendUpdateConnectAgent(){

        try{
            wait(suspendTime);
        }catch(InterruptedException e){
            //被别的线程强制中断，一般不会发生
            logger.error("suspendUpdateConnectAgent[moudeId:"+moduleId+"] wait Interrupted:"+e);
        }

    }


    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }


    public void start() throws Exception{

        updateConnectAgent();

        stopCountDownLatch = new CountDownLatch(1);

        //每5分钟更新一次
        executorService.execute(new Runnable() {
            public void run() {
                while(true){
                    suspendUpdateConnectAgent();
                    if(stopSign){
                        destroyConnectAgent();
                        break;
                    }
                    updateConnectAgent();
                }
            }
        });

    }

    public void destroy() throws Exception{

        stopSign = true;
        startUpdateConnectAgentImmediately();
        stopCountDownLatch.await();//优雅结束countDownLatch.countDown()所在的业务模块
        executorService.shutdown();

    }

    public QueueAgent getQueueAgent(String queueName){

        return queueAgentMap.get(queueName);
    }

    public ConnectAgent getConnectAgent(long connectId){

        return connectAgentMap.get(connectId);
    }
}
