package ru.netology.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.netology.entity.ApplicationStatus;
import ru.netology.event.CreditDecisionEvent;
import ru.netology.repository.CreditApplicationRepository;

@Component
public class CreditDecisionListener {

    private final CreditApplicationRepository repository;

    public CreditDecisionListener(CreditApplicationRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "credit-decisions")
    public void handleDecision(CreditDecisionEvent event) {
        repository.findById(event.getApplicationId()).ifPresent(app -> {
            app.setStatus(event.isApproved()
                    ? ApplicationStatus.APPROVED
                    : ApplicationStatus.REJECTED);
            repository.save(app);
        });
    }
}