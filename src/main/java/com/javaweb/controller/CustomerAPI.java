package com.javaweb.controller;

import com.javaweb.model.CustomerRequest;
import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.model.CustomerResponse;
import com.javaweb.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/api/customer/")
    public List<CustomerResponse> getCustomer(CustomerSearchRequest request){
        List<CustomerResponse> result = customerService.findALl(request);
        return result;
    }
//    @Transactional là annotation của Spring dùng để quản lý transaction (giao dịch).
//
//    Hiểu đơn giản:
//
//    Một nhóm các thao tác với database sẽ được thực hiện như một khối.
//
//    Thành công hết → COMMIT.
//    Có lỗi giữa chừng → ROLLBACK (quay lại như chưa làm gì).
    @PostMapping("/api/customer/")
    @Transactional
    public void createCustomer(@RequestBody CustomerRequest request){
        customerService.createOrUpdate(request);
    }
    @PutMapping("/api/customer/")
    @Transactional
    public void updateCustomer(@RequestBody CustomerRequest request){
        customerService.createOrUpdate(request);
    }
    @DeleteMapping("/api/customer/{id}")
    @Transactional
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
