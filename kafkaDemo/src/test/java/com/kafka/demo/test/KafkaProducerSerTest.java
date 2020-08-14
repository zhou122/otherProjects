package com.kafka.demo.test;

import com.kafka.demo.clients.KafkaProducerSevice;
import com.kafka.demo.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(classes = com.kafka.demo.DemoApplication.class)
@RunWith(SpringRunner.class)
public class KafkaProducerSerTest {

    @Autowired
    private KafkaProducerSevice kafkaProducer;

    @Test
    public void test(){
        try{
            Message message = new Message("1","测试消息");
            kafkaProducer.sendMsg(message);

            Thread.sleep(3*1000);
        }catch (Exception e){
            log.error("发送消息失败",e);
        }
    }

}
