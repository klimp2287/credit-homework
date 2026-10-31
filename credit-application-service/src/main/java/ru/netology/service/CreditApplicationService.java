package ru.netology.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.dto.CreditApplicationRequest;
import ru.netology.entity.ApplicationStatus;
import ru.netology.entity.CreditApplication;
import ru.netology.event.CreditApplicationEvent;
import ru.netology.repository.CreditApplicationRepository;

@Service
public class CreditApplicationService {

    private final CreditApplicationRepository repository;
    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;

    public CreditApplicationService(CreditApplicationRepository repository,
                                    KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Long createApplication(CreditApplicationRequest request) {
        CreditApplication app = new CreditApplication();
        app.setAmount(request.getAmount());
        app.setTermMonths(request.getTermMonths());
        app.setMonthlyIncome(request.getMonthlyIncome());
        app.setCurrentCreditLoad(request.getCurrentCreditLoad());
        app.setCreditRating(request.getCreditRating());
        repository.save(app);

        CreditApplicationEvent event = new CreditApplicationEvent();
        event.setApplicationId(app.getId());
        event.setAmount(app.getAmount());
        event.setTermMonths(app.getTermMonths());
        event.setMonthlyIncome(app.getMonthlyIncome());
        event.setCurrentCreditLoad(app.getCurrentCreditLoad());
        event.setCreditRating(app.getCreditRating());

        kafkaTemplate.send("credit-applications", event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println(">>> KAFKA SEND FAILED: " + ex.getMessage());
                    } else {
                        System.out.println(">>> KAFKA SEND OK: offset=" + result.getRecordMetadata().offset());
                    }
                });
        return app.getId();
    }

    public ApplicationStatus getStatus(Long id) {
        return repository.findById(id)
                .map(CreditApplication::getStatus)
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
    }
}