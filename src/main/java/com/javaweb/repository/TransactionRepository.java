package com.javaweb.repository;

import com.javaweb.repository.entity.TransactionTypeEntity;
import com.javaweb.repository.entity.TransactionsEntity;

import java.util.List;

public interface TransactionRepository {
    List<TransactionsEntity> findByIdCustomer(Long customerId);
    void createTransaction(TransactionsEntity transactionsEntity);
    void updateTransaction(TransactionsEntity transactionsEntity);
    void deleteTransaction(Long id);
    TransactionsEntity findById(Long id);
    TransactionTypeEntity findByTransactionTypeId(Long id);
}
