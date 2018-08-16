package com.qx.common.notifyserver.core.connect.api.db;

import com.qx.common.notifyserver.core.connect.api.message.MessageDm;

import java.util.List;

/**
 * Created by saosin on 2018/8/16.
 */
public interface DbConnectAgent {

    void createTable(String queueName) throws Exception;
    void insert(MessageDm messageDm) throws Exception;
    void update(MessageDm messageDm) throws Exception;
    void delete(MessageDm messageDm) throws Exception;

    /**
     * 根据数据库主键和消息状态查询消息
     * @param queueName 队列名称
     * @param start 起始id（包含）
     * @param end 	结束id（包含）
     * @param status 消息类型
     * @return 消息对象
     * @throws Exception
     */
    public List<MessageDm> select(String queueName, long start, long end, byte status) throws Exception;
    public long selectCount(String queueName, long start, long end, byte status) throws Exception;
    public List<MessageDm> selectTimeout(String queueName, long start, long end, byte status, long timeout) throws Exception;
    public MessageDm selectById(String queueName, long id) throws Exception;
    /**
     * 查询MSG_IF表最大的id
     */
    public long maxId(String queueName) throws Exception;
    /**
     * 查询MSG_IF表最大的id
     * @param queueName
     * @param timeMillis beginTime< timeMillis
     * @return 0，如果表中无数据 ，MSG_XX_IF中最大的主键
     * @throws Exception
     */
    public long maxIdDelay(String queueName, long delay) throws Exception;
    /**
     * 查询MSG_IF表最小的id
     */
    public long minId(String queueName) throws Exception;
    /**
     * 查询MSG_IF表中未发送消息（status=3)中最小的id
     */
    public long minUnFinishId(String queueName) throws Exception;
    /**
     * 查询MSG_IF表中status状态的数据中最小的id
     */
    public long minIdByStatus(String queueName, byte status) throws Exception;
    /**
     * 查询数据库与本机服务器时间的差值(System.currentTimeMillis() - mysql.CURRENT_TIMESTAMP);
     * @return
     * @throws Exception
     */
    public long getDiffTime() throws Exception;
    /**
     * 分配中心抢占队列锁。
     * @return true，抢占成功，false，抢占失败
     * @param queueName 队列名称
     * @param id  分配中心编号
     * @param diffTime 本地服务器与Mysql服务器的时间差
     * @param intervalTime 超时时间（超过这个时间，锁定将失效）
     * @throws Exception
     */
    public boolean getAlive(String queueName, long id, long diffTime, long intervalTime) throws Exception;
    /**
     * 保持队列锁
     * @return true，保持成功，false，保持失败
     * @param queueName 队列名称
     * @param id  分配中心编号
     * @param diffTime 本地服务器与Mysql服务器的时间差
     * @param intervalTime 超时时间（超过这个时间，锁定将失效）
     * @throws Exception
     */
    public boolean keepAlive(String queueName, long id, long diffTime, long intervalTime) throws Exception;
    /**
     * 释放队列锁
     * @return true，释放成功，false，释放失败
     * @param queueName 队列名称
     * @param id 分配中心编号
     * @throws Exception
     */
    public void releaseAlive(String queueName, long id) throws Exception;
}
