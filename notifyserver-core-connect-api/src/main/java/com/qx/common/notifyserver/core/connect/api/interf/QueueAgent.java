package com.qx.common.notifyserver.core.connect.api.interf;

import java.util.List;

/**
 * Created by saosin on 2018/8/15.
 */
public interface QueueAgent {

    String getQueueName();
    ConnectAgent getRandomActiveConnectAgent();
    //根据传入的connectAgent提取不同的另外一个connectAgent，所以必定有一系列connectAgent的集合对应改QueueAgent
    ConnectAgent getAnotherRandomConnectAgent(ConnectAgent connectAgent);
    List<ConnectAgent> getAllConnectdAgent();
    //剔除connectAgents中已存在的connectAgent,只追加现有集合中没有的connectAgent
    List<ConnectAgent> replaceWithNewConnectAgent(List<ConnectAgent> connectAgents);

}
