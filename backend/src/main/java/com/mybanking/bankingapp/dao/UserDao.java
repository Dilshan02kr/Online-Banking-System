package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.model.Payment;
import com.mybanking.bankingapp.model.Transaction;
import com.mybanking.bankingapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAllUsers();

    User findUserById(int id);

    User save(User user);

    Transaction foundTransfer(int id, Transaction transaction);

    Payment doPayment(int id, Payment payment);

    Optional<User> findUserByUsername(String username);

    // User findByUsername(String username);

}
