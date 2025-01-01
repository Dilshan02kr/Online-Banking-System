package com.mybanking.bankingapp.dao;

import com.mybanking.bankingapp.exception.ShowUserException;
import com.mybanking.bankingapp.exception.TransactionException;
import com.mybanking.bankingapp.model.Payment;
import com.mybanking.bankingapp.model.Transaction;
import com.mybanking.bankingapp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("from User", User.class);

        return query.getResultList();
    }

    @Override
    public User findUserById(int id) {

        User user = entityManager.find(User.class, id);

        if (user == null){
            throw new ShowUserException("No any Account for this id: "+id);
        }

        return user;
    }

    @Override
    public User save(User user) {

        System.out.println("In UserDao");

        User dbUser = entityManager.merge(user);

        return dbUser;
    }

    @Override
    public Transaction foundTransfer(int id, Transaction transaction) {

        User sender = entityManager.find(User.class, id);

        User targetUser = entityManager.find(User.class, transaction.getTargetUserId());

        Double amount = transaction.getAmount();

        if (targetUser == null || targetUser.getId() == id){

            throw new TransactionException("Invalid Account Number!!!");

        } else if (amount < 0) {

            throw new TransactionException("Invalid Amount!!!");

        } else {

            //update sender balance

            Double senderPreBalance = sender.getBalance();
            sender.setBalance(senderPreBalance - amount);

            //update targetUser balance

            Double targetUserPreBalance = targetUser.getBalance();
            targetUser.setBalance(targetUserPreBalance + amount);

            sender.addTransaction(transaction);

            entityManager.merge(sender);
            entityManager.merge(targetUser);

        }

        return transaction;

    }

    @Override
    public Payment doPayment(int id, Payment payment) {

        User sender = entityManager.find(User.class, id);

        Double amount = payment.getAmount();

        if (amount < 0) {
            throw new TransactionException("Invalid Amount!!!");
        } else {

            //update sender balance

            Double senderPreBalance = sender.getBalance();
            sender.setBalance(senderPreBalance - amount);

            sender.addPayment(payment);


            entityManager.merge(sender);
        }

        return payment;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username = :data", User.class);
        query.setParameter("data", username);

        try {

            User user = query.getSingleResult();
            return Optional.of(user);
        }
        catch (NoResultException e){
            return Optional.empty();
        }

    }

}
