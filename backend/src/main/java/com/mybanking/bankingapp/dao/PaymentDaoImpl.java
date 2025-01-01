package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.dto.TransactionDTO;
import com.mybanking.bankingapp.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao{

    private EntityManager entityManager;

    @Autowired
    public PaymentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TransactionDTO> findAllPaymentsByUserId(int userId) {

        List<TransactionDTO> allTransactions = new ArrayList<>();

        TypedQuery<Payment> query = entityManager.createQuery("from Payment where user.id = :data", Payment.class);
        query.setParameter("data", userId);

        List<Payment> payments = query.getResultList();

        for (Payment payment : payments){
            TransactionDTO tdto = new TransactionDTO();
            tdto.setId(payment.getId());
            tdto.setReceiver(payment.getAccount());
            tdto.setAmount(payment.getAmount());
            tdto.setType("Payment");
            tdto.setDate(payment.getPaymentDate());
            tdto.setDescription(payment.getDescription());

            allTransactions.add(tdto);
        }

        return allTransactions;

    }
}
