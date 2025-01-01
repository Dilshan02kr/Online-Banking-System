package com.mybanking.bankingapp.dto;

import java.time.LocalDateTime;

public class TransactionDTO {

    private int id;

    private String receiver;

    private Double amount;

    private String type;

    private LocalDateTime date;

    private String description;

    public TransactionDTO() {
    }

    public TransactionDTO(int id, String receiver, Double amount, String type, LocalDateTime date, String description) {
        this.id = id;
        this.receiver = receiver;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
