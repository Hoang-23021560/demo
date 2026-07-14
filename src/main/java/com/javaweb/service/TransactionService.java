package com.javaweb.service;

import com.javaweb.model.TransactionRequest;
import com.javaweb.model.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> findByCustomerId(Long customerId);
    void createTransaction(TransactionRequest request);
    void updateTransaction(TransactionRequest request);
    void deleteTransaction(Long id);
}
