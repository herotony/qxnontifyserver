package com.qx.common.notifyserver.core.connect.api.db.mysql;

import com.qx.common.notifyserver.core.connect.api.abstr.ConnectAgentAbstract;

/**
 * Created by saosin on 2018/8/16.
 */
public class MysqlDbConnectAgent {

    public void createTable(String queueName) throws Exception {

        //if (connectStatus.get() == false) {
            //throw new ConnectionUnableException("Connection disable for the db connect agent");
        //}

        StringBuilder sqlContentTableStringBuilder = new StringBuilder();

        //CT表，msg_queueName_ct表，消息【内容】表
        sqlContentTableStringBuilder.append("CREATE TABLE IF NOT EXISTS `MSG_");
        sqlContentTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqlContentTableStringBuilder.append("_CT` (");
        sqlContentTableStringBuilder.append("	`ID` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',");
        sqlContentTableStringBuilder.append("	`CONTENT` longblob NOT NULL COMMENT '消息内容',");
        sqlContentTableStringBuilder.append("	PRIMARY KEY (`ID`)");
        sqlContentTableStringBuilder.append(") ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");

        StringBuilder sqlTableStringBuilder = new StringBuilder();

        //消息存储介质信息表【该消息存到哪了以及消息对应的消费Queue】
        sqlTableStringBuilder.append("CREATE TABLE IF NOT EXISTS `MSG_");
        sqlTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqlTableStringBuilder.append("_IF` (");
        sqlTableStringBuilder.append("	`ID` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',");
        sqlTableStringBuilder.append("	`UUID` char(32) NOT NULL COMMENT '消息UUID',");
        sqlTableStringBuilder.append("	`QUEUE_NAME` char(32) NOT NULL COMMENT '队列名称',");
        sqlTableStringBuilder.append("	`CONTENT_ID` bigint(16) NOT NULL COMMENT '对应消息体表主键',");
        sqlTableStringBuilder.append("	`EXECUTE_QUEUE` bigint(8) NOT NULL COMMENT '分配消费者组',");
        sqlTableStringBuilder.append("	`RESULT_QUEUE` bigint(8) NOT NULL COMMENT '执行结果消费者组',");
        sqlTableStringBuilder.append("	`STATUS` tinyint(1) NOT NULL COMMENT '状态',");
        sqlTableStringBuilder.append("	`REDIS_ONE` bigint(8) NOT NULL COMMENT '存放缓存一id',");
        sqlTableStringBuilder.append("	`REDIS_TWO` bigint(8) NOT NULL COMMENT '存放缓存二id',");
        sqlTableStringBuilder.append("	`BEGIN_TIME` bigint(16) NOT NULL COMMENT '开始时间',");
        sqlTableStringBuilder.append("	`FINISH_TIME` bigint(16) NOT NULL COMMENT '结束时间',");
        sqlTableStringBuilder.append("	PRIMARY KEY (`ID`)");
        sqlTableStringBuilder.append(") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");


        StringBuilder sqlBackupTableStringBuilder = new StringBuilder();

        //消息的状态信息表【同样保存了消息对应的消费Queue】
        sqlBackupTableStringBuilder.append("CREATE TABLE IF NOT EXISTS `MSG_");
        sqlBackupTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqlBackupTableStringBuilder.append("_BU` (");
        sqlBackupTableStringBuilder.append("	`ID` bigint(16) NOT NULL COMMENT '主键',");
        sqlBackupTableStringBuilder.append("	`UUID` char(32) NOT NULL COMMENT '消息UUID',");
        sqlBackupTableStringBuilder.append("	`QUEUE_NAME` char(32) NOT NULL COMMENT '队列名称',");
        sqlBackupTableStringBuilder.append("	`CONTENT_ID` bigint(16) NOT NULL COMMENT '消息体id',");
        sqlBackupTableStringBuilder.append("	`EXECUTE_QUEUE` bigint(8) NOT NULL COMMENT '分配消费者组',");
        sqlBackupTableStringBuilder.append("	`RESULT_QUEUE` bigint(8) NOT NULL COMMENT '执行结果消费者组',");
        sqlBackupTableStringBuilder.append("	`STATUS` tinyint(1) NOT NULL COMMENT '状态',");
        sqlBackupTableStringBuilder.append("	`BEGIN_TIME` bigint(16) NOT NULL COMMENT '开始时间',");
        sqlBackupTableStringBuilder.append("	`FINISH_TIME` bigint(16) NOT NULL COMMENT '结束时间',");
        sqlBackupTableStringBuilder.append("	PRIMARY KEY (`ID`)");
        sqlBackupTableStringBuilder.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


        StringBuilder sqLockTableStringBuilder = new StringBuilder();

        //消息超时表？
        sqLockTableStringBuilder.append("CREATE TABLE IF NOT EXISTS `MSG_");
        sqLockTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqLockTableStringBuilder.append("_LK` (");
        sqLockTableStringBuilder.append("	`ID` bigint(16) NOT NULL COMMENT '主键',");
        sqLockTableStringBuilder.append("	`OVERTIME` bigint(16) NOT NULL COMMENT '超时时间',");
        sqLockTableStringBuilder.append("	`SET_ID` bigint(16) NOT NULL COMMENT '配置id',");
        sqLockTableStringBuilder.append("	PRIMARY KEY (`ID`)");
        sqLockTableStringBuilder.append(") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");


        StringBuilder sqLocklInsertTableStringBuilder = new StringBuilder();

        //
        sqLocklInsertTableStringBuilder.append("INSERT INTO MSG_");
        sqLocklInsertTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqLocklInsertTableStringBuilder.append("_LK ");
        sqLocklInsertTableStringBuilder.append("SELECT 1 AS ID, 0 AS OVERTIME, 0 AS SET_ID FROM DUAL ");
        sqLocklInsertTableStringBuilder.append("WHERE (SELECT COUNT(*) FROM MSG_");
        sqLocklInsertTableStringBuilder.append(queueName.toUpperCase().replace(".", "_"));
        sqLocklInsertTableStringBuilder.append("_LK) = 0");

        /*
        try {
            jdbcTemplate.update(sqlContentTableStringBuilder.toString());
            jdbcTemplate.update(sqlTableStringBuilder.toString());
            jdbcTemplate.update(sqlBackupTableStringBuilder.toString());
            jdbcTemplate.update(sqLockTableStringBuilder.toString());
            jdbcTemplate.update(sqLocklInsertTableStringBuilder.toString());
            if(businessLogger.isDebugEnabled()){
                businessLogger.debug("the "+queueName+"'s tables has created."
                        + "DB: " + connectId
                        + ", Ip: " + ip
                        + ", Port: " + port);
            }
        } catch (RecoverableDataAccessException e) {
            connectStatus.set(false);
            if (logger.isErrorEnabled()) {
                logger.error("CreateTable -> "
                        + "DB: " + connectId
                        + ", Ip: " + ip
                        + ", Port: " + port
                        + ", Create Table -> " ,e);
            }
            if (businessLogger.isErrorEnabled()) {
                businessLogger.error("CreateTable -> "
                        + "DB: " + connectId
                        + ", Ip: " + ip
                        + ", Port: " + port
                        + ", Create Table -> " ,e);
            }
            throw new ConnectionResetException("Connection reset for create mysql db connect agent");
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("CreateTable -> "
                        + "DB: " + connectId
                        + ", Ip: " + ip
                        + ", Port: " + port
                        + ", Create Table -> " ,e);
            }
            if (businessLogger.isErrorEnabled()) {
                businessLogger.error("CreateTable -> "
                        + "DB: " + connectId
                        + ", Ip: " + ip
                        + ", Port: " + port
                        + ", Create Table -> " ,e);
            }
            throw e;
        }*/
    }
}
