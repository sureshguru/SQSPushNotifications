package com.example.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.enumI.NotifType;
import com.example.model.NotificationModel;
import com.example.notif.sender.NotificationSender;

@Component
public class NotificationDecisionMaker {
	
	@Autowired
    @Qualifier("emailSender")
    private NotificationSender emailSender;
	
	@Autowired
    @Qualifier("slackSender")
    private NotificationSender slackSender;
	
	/**
	 * Identify the notification sender based on notification type
	 * 
	 * @param notificationModel
	 * @return
	 */
	public NotificationSender pickNotification(NotificationModel notificationModel) {
		NotificationSender notificationSender = null;
		String type = notificationModel.getType();
		
		if (type.equals(NotifType.EMAIL.getDisplayValue()))
			return emailSender;
		if (type.equals(NotifType.SLACK.getDisplayValue()))
			return slackSender;
		else
			return notificationSender;
		
	}
}
