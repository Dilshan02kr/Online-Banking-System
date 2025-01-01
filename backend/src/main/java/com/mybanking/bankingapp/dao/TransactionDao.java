package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.dto.TransactionDTO;

import java.util.List;

public interface TransactionDao {

    List<TransactionDTO> findTransactionsByUserId(int userId);
    List<TransactionDTO> findDepositsByUserId(int userId);
}
