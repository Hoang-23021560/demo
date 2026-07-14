package com.javaweb.controller;

import com.javaweb.model.TransactionRequest;
import com.javaweb.model.TransactionResponse;
import com.javaweb.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionAPI {

    @Autowired
    private TransactionService transactionService;

    /**
     * GET /api/transaction/?customerId=1
     * Lấy danh sách lịch sử giao dịch của một khách hàng
     */
    @GetMapping("/api/transaction/")
    public List<TransactionResponse> getTransaction(@RequestParam Long customerId) {
        return transactionService.findByCustomerId(customerId);
    }

    /**
     * POST /api/transaction/
     * Thêm mới một giao dịch cho khách hàng
     * Body: { "customerId": 1, "transactionTypeId": 1, "note": "..." }
     */
    @PostMapping("/api/transaction/")
    @Transactional
    public void createTransaction(@RequestBody TransactionRequest request) {
        transactionService.createTransaction(request);
    }

    /**
     * PUT /api/transaction/
     * Cập nhật giao dịch (cần có id trong body)
     * Body: { "id": 1, "transactionTypeId": 2, "note": "...", "customerId": 1 }
     */
    @PutMapping("/api/transaction/")
    @Transactional
    public void updateTransaction(@RequestBody TransactionRequest request) {
        transactionService.updateTransaction(request);
    }

    /**
     * DELETE /api/transaction/{id}
     * Xóa một giao dịch theo id
     */
    @DeleteMapping("/api/transaction/{id}")
    @Transactional
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
