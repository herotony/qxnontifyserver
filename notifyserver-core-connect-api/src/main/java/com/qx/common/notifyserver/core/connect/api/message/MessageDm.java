package com.qx.common.notifyserver.core.connect.api.message;

import java.io.Serializable;

/**
 * Created by saosin on 2018/8/16.
 */
public class MessageDm implements Serializable {

    private static final long serialVersionUID = -4999082804244172347L;

    private String uuid;
    private String queueName;
    private byte[] content;

    private long contentId;
    private long id;

    private long db;
    private long redisOne;
    private long redisTwo;


    private byte status;
    private boolean result;

    private long beginTime;
    private long finishTime;
    private long delayTime;
    private long cacheTimestamp;

    private Exception exception;

    private long executeQueue;
    private long resultQueue;

    private Object objectOneLock; //一般为消息的阈值锁CountDownLatch
    private Object objectTwoLock; //一般为分配中心持有数据库的锁DistributeSetLocker
    private Object objectThreeLock; //一般为消息计数锁，AtomLong

    private boolean newMessage;//false - 重复消息,true - 新消息

    private long perfMonitorAfterPickOutFromDb;//消息从数据库取出的时间
    private long perfMonitorBeforeSaveToRedis;//消息保存到Redis之前的时间
    private long perfMonitorBeforeSaveToMemory;//消息保存到内存队列之前的时间
    private long perfMonitorAfterSendToConsumer;//消息被消费之后的时间

    public MessageDm(){}

    public MessageDm(
            String queueName,
            String uuid,
            byte[] content
    ){
        this.queueName = queueName;
        this.uuid = uuid;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDb() {
        return db;
    }

    public void setDb(long db) {
        this.db = db;
    }

    public long getRedisOne() {
        return redisOne;
    }

    public void setRedisOne(long redisOne) {
        this.redisOne = redisOne;
    }

    public long getRedisTwo() {
        return redisTwo;
    }

    public void setRedisTwo(long redisTwo) {
        this.redisTwo = redisTwo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getCacheTimestamp() {
        return cacheTimestamp;
    }

    public void setCacheTimestamp(long cacheTimestamp) {
        this.cacheTimestamp = cacheTimestamp;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public long getExecuteQueue() {
        return executeQueue;
    }

    public void setExecuteQueue(long executeQueue) {
        this.executeQueue = executeQueue;
    }

    public long getResultQueue() {
        return resultQueue;
    }

    public void setResultQueue(long resultQueue) {
        this.resultQueue = resultQueue;
    }

    public Object getObjectOneLock() {
        return objectOneLock;
    }

    public void setObjectOneLock(Object objectOneLock) {
        this.objectOneLock = objectOneLock;
    }

    public Object getObjectTwoLock() {
        return objectTwoLock;
    }

    public void setObjectTwoLock(Object objectTwoLock) {
        this.objectTwoLock = objectTwoLock;
    }

    public Object getObjectThreeLock() {
        return objectThreeLock;
    }

    public void setObjectThreeLock(Object objectThreeLock) {
        this.objectThreeLock = objectThreeLock;
    }

    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }

    public long getPerfMonitorAfterPickOutFromDb() {
        return perfMonitorAfterPickOutFromDb;
    }

    public void setPerfMonitorAfterPickOutFromDb(long perfMonitorAfterPickOutFromDb) {
        this.perfMonitorAfterPickOutFromDb = perfMonitorAfterPickOutFromDb;
    }

    public long getPerfMonitorBeforeSaveToRedis() {
        return perfMonitorBeforeSaveToRedis;
    }

    public void setPerfMonitorBeforeSaveToRedis(long perfMonitorBeforeSaveToRedis) {
        this.perfMonitorBeforeSaveToRedis = perfMonitorBeforeSaveToRedis;
    }

    public long getPerfMonitorBeforeSaveToMemory() {
        return perfMonitorBeforeSaveToMemory;
    }

    public void setPerfMonitorBeforeSaveToMemory(long perfMonitorBeforeSaveToMemory) {
        this.perfMonitorBeforeSaveToMemory = perfMonitorBeforeSaveToMemory;
    }

    public long getPerfMonitorAfterSendToConsumer() {
        return perfMonitorAfterSendToConsumer;
    }

    public void setPerfMonitorAfterSendToConsumer(long perfMonitorAfterSendToConsumer) {
        this.perfMonitorAfterSendToConsumer = perfMonitorAfterSendToConsumer;
    }
}
