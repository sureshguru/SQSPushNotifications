package com.example.notif.sender.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.model.NotificationModel;
import com.example.notif.sender.NotificationSender;

@Component
@Qualifier("emailSender")
public class EmailSender implements NotificationSender{
	
	/**
	 * Send email notifications
	 * 
	 * Implementation will be enhanced at real time, Implemented mock code due to time constraint
	 */
	@Override
	public Boolean collectNotifications(NotificationModel model) {
		// TODO Auto-generated method stub
		System.out.println( "Email Notifiaction sent : " + model.getBody());
		return true;
	}

}
