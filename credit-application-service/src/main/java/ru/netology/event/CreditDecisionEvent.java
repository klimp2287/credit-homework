package ru.netology.event;

public class CreditDecisionEvent {
    private Long applicationId;
    private boolean approved;

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
}