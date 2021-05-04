package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.NotificationModel;
import com.example.util.SQSConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/notification")
public class NotificationController {
	
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@RequestMapping(value = "/sender", method = RequestMethod.POST)
	public String  publishNotif(@RequestBody List<NotificationModel> notifModelList)  {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			for (NotificationModel notifModel: notifModelList) {
				String jsonString = mapper.writeValueAsString(notifModel);
				queueMessagingTemplate.send(SQSConstant.queueURL, MessageBuilder.withPayload(jsonString).build());
			}
			
		} catch (Exception e) {
			System.out.println("Exception during pushing the data :"+ e.getMessage());
			return SQSConstant.DATA_PUSH_FAILURE;
		}
		return SQSConstant.DATA_PUSH_SUCCESS;
	}
	
}
