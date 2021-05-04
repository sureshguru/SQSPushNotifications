package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.example.business.NotificationDecisionMaker;
import com.example.model.NotificationModel;
import com.example.notif.sender.NotificationSender;
import com.example.util.SQSConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Listener to read the data from queue and push the notifications
 * 
 * @author sgurusamy
 *
 */
@Component
public class QueueListener {

	@Autowired 
	NotificationDecisionMaker notificationDecisionMaker;
	
	/**
	 * consume the data from the queue, it will remove the queue items only if its success
	 * 
	 * @param event
	 * @throws Exception
	 */
	@SqsListener(value = SQSConstant.queueURL, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void notificationCollectore(Object event) throws Exception {
		System.out.println("event :" +event);
		ObjectMapper mapper = new ObjectMapper();
		//ObjectMapper MAPPER = JsonMapperUtil.createMapperForMetadata();
		NotificationModel notifModel = mapper.readValue(event.toString(), NotificationModel.class);
		NotificationSender notificationSender=notificationDecisionMaker.pickNotification(notifModel);
		if (notificationSender ==  null) {
			throw new Exception("Message type not supported by the consumer");
		}
		boolean flag = notificationSender.collectNotifications(notifModel);
		
		if (!flag) {
			throw new Exception("Message failed to send");
		} else {
			System.out.println("Messgae successfully sent");
		}
	}
}
