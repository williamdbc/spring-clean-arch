package com.colatina.app.service.dataprovider.repository;

import com.colatina.app.service.dataprovider.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByAccountOriginIdAndCreatedAtBetweenAndValueGreaterThanEqual(Integer accountId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minValue);
}
