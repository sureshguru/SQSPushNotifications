# SQSPushNotifications
Amazon SQS based push notifications
This is the code repository for push notification application using Amazon SQS built using maven. 

### This application will allow you to perform following operations
* Send the Notification to Amazon SQS queue
* Consume the notifications from queue - delete the notificaiton if it is a successful read.
* Push the notifications to multiple channels such as email, slack, ... 

### Runing the application on local IDE
---
1. Clone the repo
	* http : https://github.com/sureshguru/SQSPushNotifications.git
	* ssh : git@github.com:sureshguru/SQSPushNotifications.git
2. Import as maven prject from your local ide.
3. run mvn clean install
4. run the main application "NotificationConsumerApplication"
	
	
### Send the Notification to SQS queue
---
Import the following curl snippet in your post man
```
curl --location --request POST 'http://localhost:8080/api/notification/sender' \
--header 'Content-Type: application/json' \
--data-raw '[{
    "from": "sureshgurusamy88@gmail.com",
    "to": "xyz@gmail.com",
    "body": "Notification 1",
    "type": "email"
    
},
{
    "from": "sureshgurusamy88@gmail.com",
    "to": "xyzh@gmail.com",
    "body": "Notification 2",
    "type": "slack"
},
{
    "from": "sureshgurusamy88@gmail.com",
    "to": "xyz@gmail.com",
    "body": "Notification 3",
    "type": "email"
}
]'
```
### Console Output
---
View the result in console
* dequeue the items using SQS listener
* perform the unmarshalling
* send the notification based on notificaiton type.

Sample output
```
event :{"from":"sureshgurusamy88@gmail.com","to":"xyz@gmail.com","body":"Notification 1","type":"email"}
Email Notifiaction sent : Notification 1
Messgae successfully sent
event :{"from":"sureshgurusamy88@gmail.com","to":"xyz@gmail.com","body":"Notification 2","type":"slack"}
Slack Notifiaction sent : Notification 2
Messgae successfully sent
event :{"from":"sureshgurusamy88@gmail.com","to":"xyz@gmail.com","body":"Notification 3","type":"email"}
Email Notifiaction sent : Notification 3
Messgae successfully sent
```
	
	
		
