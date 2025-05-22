package com.sb.ms.ang.empdetails.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.sb.ms.ang.empdetails.model.Employee;

public class KafkaMessageProducer {
	  private final KafkaProducer<String, String> producer;
	    private  String topic= "my-topic";
	    String brokers = "localhost:9092";
       // String user ="User";
	    RecordMetadata rm;



	    public KafkaMessageProducer(String brokers, String topic) {
	        Properties props = new Properties();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

	        producer = new KafkaProducer<>(props);
	        this.topic = topic;
	    }

	    public long produceMessage(String key, String message) {
	        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
	        long responseOffset =0;
	        Future<RecordMetadata> status =producer.send(record, (metadata, exception) -> {
	            if (exception != null) {
	                System.err.println("Failed to send message: " + exception.getMessage());
	                
	            } else {
	                System.out.println("Message sent successfully to topic: " + metadata.topic() + ", partition: " + metadata.partition() + ", offset: " + metadata.offset());
	               
	            }
	        });
	        if(status.isDone())
	        {
	        	try {
					 rm=	status.get();
					 responseOffset = rm.offset();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        return responseOffset;
	    }

	    public void close() {
	        producer.close();
	    }

		/*
		 * public static void main(String[] args) { // Replace with your Kafka brokers
		 * and topic String brokers = "localhost:9092"; String topic = "my-topic";
		 * String user ="User";
		 * 
		 * KafkaMessageProducer producer = new KafkaMessageProducer(brokers, topic);
		 * 
		 * try { producer.produceMessage("Hello User1", "Hello, !"+user + "Welcome");
		 * producer.produceMessage("Hello User1",
		 * "Lets Learn,code, practice and repeat"); } finally { producer.close(); } }
		 */
}
