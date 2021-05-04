package com.example.enumI;

public enum NotifType {
	EMAIL("email"), 
	SLACK("slack");
	
	private String value;
	NotifType(String  value) {
		this.value = value;
	}
	
	public String getDisplayValue() {
		return this.value;
	}
}
