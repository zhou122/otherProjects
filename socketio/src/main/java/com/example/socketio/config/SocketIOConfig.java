package com.example.socketio.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @Author: liumin
 * @Date: 2019/9/10 14:34
 */
@SpringBootConfiguration
@Data
public class SocketIOConfig {

    private String host = "192.168.14.37";

    private Integer port = 18080;

    private int bossCount = 1;

    private int workCount = 100;

    private boolean allowCustomRequests;
    /**
     * 协议升级超时时间（ms），默认10秒。HTTP握手升级为ws协议超时时间
     */
    private int upgradeTimeout = 1000000;

    /**
     * Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
     */
    private int pingTimeout = 6000000;

    /**
     * Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
     */
    private int pingInterval = 25000;


    @Bean("socketServer")
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }
}
