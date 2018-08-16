package com.qx.common.notifyserver.core.connect.api.db;

/**
 * Created by saosin on 2018/8/16.
 */
public interface DbStatusChecker {

    void checkHealth() throws Exception;
    long checkNewLen(String queueName) throws Exception;
    long checkPortionLen(String queueName) throws Exception;
}
