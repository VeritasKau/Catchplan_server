package Sanhak.wakeUp.team.member.dto;

public  class ScrapRequest {
    public String uniqueUserInfo;
    public Long eventId;

    public String getUniqueUserInfo() {
        return uniqueUserInfo;
    }

    public void setUniqueUserInfo(String uniqueUserInfo) {
        this.uniqueUserInfo = uniqueUserInfo;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}