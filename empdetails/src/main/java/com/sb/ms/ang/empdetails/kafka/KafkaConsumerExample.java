package com.sb.ms.ang.empdetails.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sb.ms.ang.empdetails.utility.KafkaUtil;

@Component
public class KafkaConsumerExample {
	
	
	//0 * * * * MON-FRI
	// @Scheduled(cron = "0 0/1 * * * ?") // Runs every minute
	@Scheduled(cron="*/5  * * * *")// for every 5 minutes
	public void consumeKafkaMessage() {
        String bootstrapServers = "localhost:9092";
        String groupId = "my-group";
        String topic = "my-topic";

        // Set consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        System.out.println("Lets send the Message");

        // Create Kafka consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList(topic));

            // Poll for messages
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: key = %s, value = %s, partition = %s, offset = %s%n",
                            record.key(), record.value(), record.partition(), record.offset());
                    System.out.println("Let us Send the Fetched Value to the User above the Data ");
                    
                    
                    //Email Code
                 String msg = new  KafkaUtil().sendEmail();
                 System.out.println(msg);
                    
                }
            }
        }
    }
}
