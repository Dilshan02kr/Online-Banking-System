package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.dto.TransactionDTO;
import com.mybanking.bankingapp.model.Payment;

import java.util.List;

public interface PaymentDao {

    List<TransactionDTO> findAllPaymentsByUserId(int userId);
}
