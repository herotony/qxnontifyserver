package com.qx.common.notifyserver.core.connect.api.message;

import java.io.Serializable;

/**
 * Created by saosin on 2018/8/16.
 */
public class MessageQueueDm implements Serializable {

    private static final long serialVersionUID = 6603086511996105149L;

    private String uuid = "";
    private String queueName = "";

    private long contentId;
    private long id;

    private long db;
    private long redisOne;
    private long redisTwo;

    private long beginTime;
    private long finishTime;
    private long cacheTimestamp;

    private long perfMonitorAfterPickOutFromDb;//queue从数据库取出的时间
    private long perfMonitorBeforeSaveToRedis;//queue保存到Redis之前的时间
    private long perfMonitorBeforeSaveToMemory;//queue保存到内存队列之前的时间
    private long perfMonitorAfterSendToConsumer;//queue被消费之后的时间

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

    public long getCacheTimestamp() {
        return cacheTimestamp;
    }

    public void setCacheTimestamp(long cacheTimestamp) {
        this.cacheTimestamp = cacheTimestamp;
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
