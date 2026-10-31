package ru.netology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.entity.CreditApplication;


public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}