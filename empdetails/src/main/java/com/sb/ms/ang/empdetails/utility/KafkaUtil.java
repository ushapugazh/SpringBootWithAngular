package com.sb.ms.ang.empdetails.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.sb.ms.ang.empdetails.kafka.KafkaMessageProducer;

public class KafkaUtil {

	String key = "key-abc";
	
	public  long sendKafkaMsg(String emp) {
		 String brokers = "localhost:9092";
	        String topic = "my-topic";
	        String user ="User";

	        KafkaMessageProducer producer = new KafkaMessageProducer(brokers, topic);

	        try {
	        	//later lets change it to random String
	             return producer.produceMessage(key+1, emp);
	           
	        } finally {
	            producer.close();
	        }
			
	    }

	public  String sendEmail() {
		 final String fromEmail = "pugazhenthiusha@gmail.com"; //requires valid gmail id
	        final String password = "tohb zbpc bxkz ynux"; // correct password for gmail id
	        final String toEmail = "ushapugazhenthi1425@gmail.com"; // can be any email id

	        System.out.println("SSLEmail Start");
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
	        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
	        props.put("mail.smtp.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
	        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
	        props.put("mail.smtp.port", "465"); //SMTP Port

	        Authenticator auth = new Authenticator() {
	            //override the getPasswordAuthentication method
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };

	        Session session = Session.getDefaultInstance(props, auth);
	        System.out.println("Session created");
	        EmailSender.sendEmail(session, toEmail,"Test Email from Gmail Via Java Program", "Test Email Hello Usha from Gmail Via Java Program ");
	        return("Email Sent");
	}
	       
}
