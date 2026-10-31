package ru.netology.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_application")
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal monthlyIncome;
    private BigDecimal currentCreditLoad;
    private Integer creditRating;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.IN_PROCESS;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}