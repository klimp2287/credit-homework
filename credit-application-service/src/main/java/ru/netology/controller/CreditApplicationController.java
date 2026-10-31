package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.dto.CreditApplicationRequest;
import ru.netology.entity.ApplicationStatus;
import ru.netology.service.CreditApplicationService;

@RestController
@RequestMapping("/api/credit-applications")
public class CreditApplicationController {

    private final CreditApplicationService service;

    public CreditApplicationController(CreditApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public Long create(@RequestBody CreditApplicationRequest request) {
        return service.createApplication(request);
    }

    @GetMapping("/{id}/status")
    public ApplicationStatus getStatus(@PathVariable Long id) {
        return service.getStatus(id);
    }
}