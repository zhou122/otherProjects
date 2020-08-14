package com.example.socketio.init;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liumin
 */
@Component
@SpringBootConfiguration
@Order(0)
@Slf4j
public class SocketIORunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(SocketIORunner.class);

    @Autowired
    private SocketIOServer socketServer;

    @Override
    @SuppressWarnings("unchecked")
    public void run(String... strings) {
        try {
            SocketIONamespace socketNamespace = socketServer.addNamespace("/socket.io");
            //添加客户端连接监听器
            socketNamespace.addConnectListener(socketIOClient -> {
                try {
                    String remoteAddress = socketIOClient.getRemoteAddress().toString();
                    String clientIp = remoteAddress.substring(1, remoteAddress.indexOf(":"));
                    String userId = this.getUserId(socketIOClient);
                    if (userId==null||"".equals(userId)) {
                        log.error("客户端（ip:[{}]）握手数据未包含token，连接已断开！", clientIp);
                        socketIOClient.disconnect();
                        return;
                    }
                    log.info("客户端（ip:[{}]）userId=[{}]建立与socket服务器的连接", clientIp, userId);
                    //设置推送房间
                    socketIOClient.joinRoom(userId);
                    //设置用户信息
                    socketIOClient.set("userId", userId);
                } catch (Exception e) {
                    log.error("SocketIOClient onConnect error", e);
                    e.printStackTrace();
                }
            });

            //添加客户端断开连接事件
            socketNamespace.addDisconnectListener(socketIOClient -> {
                try {
                    String remoteAddress = socketIOClient.getRemoteAddress().toString();
                    String clientIp = remoteAddress.substring(1, remoteAddress.indexOf(":"));

                    Set<String> allRooms = socketIOClient.getAllRooms();
                    for (String room : allRooms) {
                        socketIOClient.leaveRoom(room);
                    }
                    String userId = socketIOClient.get("userId");
                    if (userId!=null&&!"".equals(userId)) {
                        socketIOClient.leaveRoom(userId);
                    }
                    log.info("客户端（ip:[{}]）userId=[{}]已断开与socket服务器的连接", clientIp, userId);
                } catch (Exception e) {
                    log.info("SocketIOClient onDisconnect error", e);
                    e.printStackTrace();
                } finally {
                    socketIOClient.disconnect();
                }
            });
            socketServer.start();
        } catch (Exception e) {
            logger.error("StartupRunner exception", e);
            e.printStackTrace();
        }
    }

    /**
     * SocketIO自销毁(避免重启项目服务端口占用问题)
     */
    @PreDestroy
    private void autoStop() throws Exception {
        if (socketServer != null) {
            socketServer.stop();
            socketServer = null;
        }
        log.info("socket.io销毁成功！");
    }

    private String getUserId(SocketIOClient socketIOClient) throws Exception {
        Map<String, List<String>> urlParams = socketIOClient.getHandshakeData().getUrlParams();
        if (urlParams != null && urlParams.containsKey("userId")) {
            List<String> tokens = urlParams.get("userId");
            if (tokens != null && !tokens.isEmpty()) {
                return tokens.get(0);
            }
        }
        return null;
    }
}
