package com.example.notif.sender;

import com.example.model.NotificationModel;

/**
 * All the sender has to implement NotificationSender
 * 
 * only implementation logic is required. framework will send the notification automatically.
 * 
 * @author sgurusamy
 *
 */
public interface NotificationSender {

	Boolean collectNotifications(NotificationModel model);
}
