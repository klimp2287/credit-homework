package ru.netology.listener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.netology.event.CreditApplicationEvent;
import ru.netology.event.CreditDecisionEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CreditApplicationListener {

    private final RabbitTemplate rabbitTemplate;

    public CreditApplicationListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "credit-applications", groupId = "credit-processing-group")
    public void process(CreditApplicationEvent event) {
        BigDecimal monthlyPayment = event.getAmount()
                .divide(BigDecimal.valueOf(event.getTermMonths()), 2, RoundingMode.HALF_UP);

        BigDecimal maxAllowed = event.getMonthlyIncome()
                .multiply(BigDecimal.valueOf(0.5));

        boolean approved = monthlyPayment.compareTo(maxAllowed) <= 0;

        CreditDecisionEvent decision = new CreditDecisionEvent();
        decision.setApplicationId(event.getApplicationId());
        decision.setApproved(approved);

        rabbitTemplate.convertAndSend("credit-decisions", decision);
    }
}