package com.qx.common.notifyserver.core.connect.api.interf;

/**
 * Created by saosin on 2018/8/15.
 */
public interface ConnectAgent {

     void connect() throws Exception;
     void close() throws Exception;
     long getConnectId();
     boolean getConnectStatus();
     void setConnectStatus(boolean status);
     String getIp();
     int getPort();
     String getUrl();
}
