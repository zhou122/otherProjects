package com.kafka.demo.clients;

import com.kafka.demo.config.KafkaConfig;
import com.kafka.demo.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class KafkaConsumerFactory {

	private static KafkaConsumerFactory factory = new KafkaConsumerFactory();

	private KafkaConsumerFactory() {

	};

	public static KafkaConsumerFactory getInstance() {
		return factory;
	}

	public KafkaConsumer<String, String> createKafkaConsumer(String topic, String groupId) {
		KafkaConfig kafkaConfig = (KafkaConfig) SpringUtil.getBean("kafkaConfig");
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
		configs.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
		configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getMaxPollRecords());
		configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getAutoOffsetReset());
		configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConfig.getAutoCommit());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConfig.getKeyDeserializer());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConfig.getValueDeserializer());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
		consumer.subscribe(Arrays.asList(topic));
		return consumer;
	}

}
