package com.example.demo.entities;

public class RegistrationResult {
    private final String memberName;
    private final String eventName;
    public RegistrationResult(String memberName, String eventName) {
        this.memberName = memberName;
        this.eventName = eventName;
    }
    public String getMemberName() {
        return memberName;
    }
    public String getEventName() {
        return eventName;
    }
  
   
}
