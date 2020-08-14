package com.example.socketio.controller;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private SocketIOServer socketServer;

    @ResponseBody
    @RequestMapping("/sendMsg")
    public void sendMsg(@RequestParam String userId){
        socketServer.getNamespace("/socket.io").getRoomOperations(userId).sendEvent("messageType", "messageData");
    }

}
