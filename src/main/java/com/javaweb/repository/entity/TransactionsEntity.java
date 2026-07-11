package com.javaweb.repository.entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note")
    private String note;
    @Column(name = "createDate")
    private Date createdDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customers;

    @ManyToOne
    @JoinColumn(name = "transactionTypeId")
    private TransactionTypeEntity transactionTypes;
    public Long getId() {
        return id;
    }

    public CustomerEntity getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerEntity customers) {
        this.customers = customers;
    }

    public TransactionTypeEntity getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(TransactionTypeEntity transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
