package com.qx.common.notifyserver.core.connect.api.interf;

/**
 * Created by saosin on 2018/8/15.
 */
public interface ConnectManager {

    void start() throws Exception;
    void destroy() throws Exception;
    void startUpdateConnectAgentImmediately();
    QueueAgent getQueueAgent(String queueName);
    ConnectAgent getConnectAgent(long connectId);
    void setModuleId(long moduleId);
    //core:setConsoleRemotingInvoke

}
