package ru.netology.dto;

import java.math.BigDecimal;

public class CreditApplicationRequest {
    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal monthlyIncome;
    private BigDecimal currentCreditLoad;
    private Integer creditRating;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
    public BigDecimal getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(BigDecimal monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public BigDecimal getCurrentCreditLoad() { return currentCreditLoad; }
    public void setCurrentCreditLoad(BigDecimal currentCreditLoad) { this.currentCreditLoad = currentCreditLoad; }
    public Integer getCreditRating() { return creditRating; }
    public void setCreditRating(Integer creditRating) { this.creditRating = creditRating; }
}
