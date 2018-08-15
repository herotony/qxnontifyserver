package com.qx.common.notifyserver.core.connect.api.abstr;

import com.qx.common.notifyserver.core.connect.api.interf.ConnectAgent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by saosin on 2018/8/15.
 */
public abstract class ConnectAgentAbstract implements ConnectAgent {

    protected AtomicBoolean connectStatus = new AtomicBoolean(false);

    protected long connectId;

    protected String ip;
    protected int port;
    protected String url;

    public ConnectAgentAbstract(String ip,int port,String url,long connectId){

        this.ip = ip;
        this.port = port;
        this.connectId = connectId;
        this.url = url;
    }

    public void connect() throws Exception{

        try{

            connectActual();

        }catch (Exception e){

            connectStatus.set(false);
            throw  e;
        }
    }

    public void close() throws Exception{

        connectStatus.set(false);
        closeActual();
    }

    public long getConnectId(){

        return connectId;
    }

    public boolean getConnectStatus(){

        return connectStatus.get();
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    public void setConnectStatus(boolean status){

        connectStatus.set(status);

    }

    protected abstract void connectActual() throws Exception;
    protected abstract void closeActual() throws  Exception;


}
