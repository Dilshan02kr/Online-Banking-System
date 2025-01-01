package com.mybanking.bankingapp.service;

import com.mybanking.bankingapp.dao.PaymentDao;
import com.mybanking.bankingapp.dao.TransactionDao;
import com.mybanking.bankingapp.dao.UserDao;
import com.mybanking.bankingapp.dto.TransactionDTO;
import com.mybanking.bankingapp.exception.UsedUsernameException;
import com.mybanking.bankingapp.model.Payment;
import com.mybanking.bankingapp.model.Transaction;
import com.mybanking.bankingapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankingServiceImpl implements BankingService{

    private UserDao userDao;
    private TransactionDao transactionDao;
    private PaymentDao paymentDao;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public BankingServiceImpl(UserDao userDao, TransactionDao transactionDao, PaymentDao paymentDao) {
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.paymentDao = paymentDao;
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public User save(User user) {

        System.out.println("In BankingService");

        if (userDao.findUserByUsername(user.getUsername()).isPresent()){
            throw new UsedUsernameException("Username Already Used!");
        }
        else {

            user.setPassword(encoder.encode(user.getPassword()));

            return userDao.save(user);
        }

    }

    @Override
    @Transactional
    public Transaction foundTransfer(int id, Transaction transaction) {
        return userDao.foundTransfer(id, transaction);
    }

    @Override
    @Transactional
    public Payment doPayment(int id, Payment payment) {
        return userDao.doPayment(id, payment);
    }

    @Override
    public List<TransactionDTO> findUserTransactions(int userId) {
        List<TransactionDTO> allTransactionsAndPayments = new ArrayList<>();

        List<TransactionDTO> list1 = transactionDao.findTransactionsByUserId(userId);
        List<TransactionDTO> list2 = paymentDao.findAllPaymentsByUserId(userId);
        List<TransactionDTO> list3 = transactionDao.findDepositsByUserId(userId);

        List<TransactionDTO> list4 = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());

        allTransactionsAndPayments = Stream.concat(list3.stream(), list4.stream()).collect(Collectors.toList());

        Collections.sort(allTransactionsAndPayments, (a, b) -> b.getDate().compareTo(a.getDate()));

        return allTransactionsAndPayments;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

//    @Override
//    public User findByUsername(String username) {
//        return userDao.findByUsername(username);
//    }
}
