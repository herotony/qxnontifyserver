package com.qx.common.notifyserver.core.connect.api.db;

/**
 * Created by saosin on 2018/8/16.
 */
public interface DbConnectAgent {

    void createTable(String queueName) throws Exception;
}
