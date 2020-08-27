package com.kafka.demo.clients;

import com.alibaba.fastjson.JSONObject;
import com.kafka.demo.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaProducerSevice {

    @Autowired
    private KafkaConfig kafkaConfig;

    private KafkaProducer<String, String> producer;

    @PostConstruct
    private void  run(){
        log.info("初始化:producer");
        Map<String, Object> configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 500l);
        producer = new KafkaProducer<>(configs);
    }

    /**
     * 往kafka发送消息
     * @param msg
     */
    public void sendMsg(Object msg,String key){
        String jsonStr = JSONObject.toJSONString(msg);
        ProducerRecord<String, String> record = new ProducerRecord<>("topic.quick.initial", key, jsonStr);
        producer.send(record);
    }

}

