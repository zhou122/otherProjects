package com.kafka.demo.clients;

import com.kafka.demo.anno.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class KafkaConsumerService implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                KafkaConsumer<String, String> consumer = KafkaConsumerFactory.getInstance().createKafkaConsumer("topic.quick.initial", "new");
                while(true){
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    if(records.isEmpty()){
                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception e){
                            log.info("",e);
                        }
                        continue;
                    }
                    for(ConsumerRecord<String,String> record:records){
                        log.info("消费到消息:{},消息所在分区:{}",record.value(),record.partition());
                    }
                    consumer.commitAsync();
                }
            }
        });
    }
}
