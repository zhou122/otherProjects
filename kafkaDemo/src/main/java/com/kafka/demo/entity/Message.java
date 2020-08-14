package com.kafka.demo.entity;

import lombok.Data;

@Data
public class Message {

    public Message(){

    }

    public Message(String id,String data){
        this.id = id;
        this.data = data;
    }

    private String id;

    private String data;

}
