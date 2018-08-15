package com.qx.common.notifyserver.core.connect.api.abstr;

import com.qx.common.notifyserver.core.connect.api.interf.ConnectAgent;
import com.qx.common.notifyserver.core.connect.api.interf.QueueAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by saosin on 2018/8/15.
 */
public abstract class QueueAgentAbstract implements QueueAgent {

    private final static Logger logger = LoggerFactory.getLogger(QueueAgentAbstract.class);

    protected String queueName; //唯一子类使用的属性
    protected CopyOnWriteArrayList<ConnectAgent> connectAgentsInQueueAgent = new CopyOnWriteArrayList<ConnectAgent>();

    public String getQueueName() {
        return queueName;
    }

    public QueueAgentAbstract(String queueName){
        this.queueName = queueName;
    }

    public ConnectAgent getRandomActiveConnectAgent(){

        ConnectAgent connectAgent = null;
        int currentSize = 0;

        while((currentSize =  connectAgentsInQueueAgent.size())>0){

            int index = (int)Math.round(Math.random() * (currentSize - 1));

            try{

                connectAgent = connectAgentsInQueueAgent.get(index);
                if(!connectAgent.getConnectStatus()){
                    connectAgentsInQueueAgent.remove(connectAgent);
                    connectAgent = null;
                }else{
                    break;
                }

            }catch(ArrayIndexOutOfBoundsException e){
                //由于replace...方法的存在，可能随时超界异常
                continue;
            }

        }

        return connectAgent;
    }

    public ConnectAgent getAnotherRandomConnectAgent(ConnectAgent connectAgent){

        ConnectAgent _connectAgent = null;
        int currentSize = 0;

        while((currentSize =  connectAgentsInQueueAgent.size())>0){

            int index = (int)Math.round(Math.random() * (currentSize - 1));

            try{

                _connectAgent = connectAgentsInQueueAgent.get(index);
                if(!_connectAgent.getConnectStatus()){
                    connectAgentsInQueueAgent.remove(_connectAgent);
                    _connectAgent = null;
                }else{

                    if(_connectAgent == connectAgent){

                        if(currentSize ==1){
                            _connectAgent = null;
                            break;
                        }else{

                            index = (index+1) % currentSize;
                            _connectAgent = connectAgentsInQueueAgent.get(index);
                            if(!_connectAgent.getConnectStatus()){
                                connectAgentsInQueueAgent.remove(_connectAgent);
                                _connectAgent = null;
                            }else{
                                break;
                            }
                        }

                    }else{

                        break;
                    }
                }

            }catch(ArrayIndexOutOfBoundsException e){
                //由于replace...方法的存在，可能随时超界异常
                continue;
            }

        }

        return _connectAgent;
    }

    public List<ConnectAgent> getAllConnectdAgent(){

        return connectAgentsInQueueAgent;
    }

    public List<ConnectAgent> replaceWithNewConnectAgent(List<ConnectAgent> connectAgents){


        //没有简单clear再add，是确保getRandomActiveConnectAgent尽量避免有empty的情况发生。
        //用CopyOnWriteArrayList也是确保同样的情况。
        for(ConnectAgent connectAgent : connectAgents){

            if(!connectAgentsInQueueAgent.contains(connectAgent)){

                connectAgentsInQueueAgent.add(connectAgent);
            }
        }

        for(ConnectAgent connectAgent:connectAgentsInQueueAgent){
            if(!connectAgents.contains(connectAgent)){
                connectAgentsInQueueAgent.remove(connectAgent);
            }
        }

        if(connectAgentsInQueueAgent.size() == 0){
            logger.info(this.queueName+" have not connect available.");
        }

        return connectAgentsInQueueAgent;
    }
}
