package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.dto.TransactionDTO;
import com.mybanking.bankingapp.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao{

    private EntityManager entityManager;

    @Autowired
    public TransactionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TransactionDTO> findTransactionsByUserId(int userId) {

        List<TransactionDTO> allTransactions = new ArrayList<>();

        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction where user.id = :data", Transaction.class);
        query.setParameter("data", userId);

        List<Transaction> transactions = query.getResultList();

        for (Transaction transaction : transactions){
            TransactionDTO tdto = new TransactionDTO();
            tdto.setId(transaction.getId());
            tdto.setReceiver(String.valueOf(transaction.getTargetUserId()));
            tdto.setAmount(transaction.getAmount());
            tdto.setType("Transaction");
            tdto.setDescription(transaction.getDescription());
            tdto.setDate(transaction.getDate());

            allTransactions.add(tdto);
        }

        return allTransactions;
    }

    @Override
    public List<TransactionDTO> findDepositsByUserId(int userId) {
        List<TransactionDTO> allTransactions = new ArrayList<>();

        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction where targetUserId = :data", Transaction.class);
        query.setParameter("data", userId);

        List<Transaction> transactions = query.getResultList();

        for (Transaction transaction : transactions){
            TransactionDTO tdto = new TransactionDTO();
            tdto.setId(transaction.getId());
            tdto.setReceiver(String.valueOf(transaction.getUser().getId()));
            tdto.setAmount(transaction.getAmount());
            tdto.setType("Deposit");
            tdto.setDescription(transaction.getDescription());
            tdto.setDate(transaction.getDate());

            allTransactions.add(tdto);
        }

        return allTransactions;
    }
}
