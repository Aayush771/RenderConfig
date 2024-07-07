package project;

import java.time.LocalDateTime;

public class Meeting {

    private static int count = 0;
    
    private int meetingId;
    private int adId;
    private LocalDateTime meetingTime;
    private String customerName;

    public Meeting(int adId, LocalDateTime meetingTime, String customerName) {
        this.meetingId = count++;
        this.adId = adId;
        this.meetingTime = meetingTime;
        this.customerName = customerName;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(LocalDateTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Meeting [adId=" + adId + ", customerName=" + customerName + ", meetingId=" + meetingId
                + ", meetingTime=" + meetingTime + "]\n";
    }
}
