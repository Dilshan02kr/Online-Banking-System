package com.mybanking.bankingapp.controller;

import com.mybanking.bankingapp.dto.TransactionDTO;
import com.mybanking.bankingapp.dto.UserDTO;
import com.mybanking.bankingapp.exception.ShowUserException;
import com.mybanking.bankingapp.exception.TransactionException;
import com.mybanking.bankingapp.model.Payment;
import com.mybanking.bankingapp.model.Transaction;
import com.mybanking.bankingapp.model.User;
import com.mybanking.bankingapp.response.ApiResponse;
import com.mybanking.bankingapp.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    private BankingService bankingService;

    @Autowired
    public UserController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    //This only for development help
    @GetMapping("/users")
    public List<UserDTO> showUsers(){
        return bankingService.findAllUsers().stream().map(user -> new UserDTO(user.getId(),user.getName(), user.getUsername(), user.getBalance())).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> showUser(@PathVariable int userId){

        try {
            User user = bankingService.findUserById(userId);
            UserDTO userDTO = new UserDTO(user.getId(),user.getName(), user.getUsername(), user.getBalance());

            ApiResponse<UserDTO> response = new ApiResponse<>(userDTO, 1, "Successful Request!!");

            return ResponseEntity.ok(response);
        }
        catch (ShowUserException e){
            throw e;
        }
    }

    @PostMapping("/users/{userId}/transfers")
    public ResponseEntity<ApiResponse<Transaction>> foundTransfer(@PathVariable int userId, @RequestBody Transaction transaction){
        try {

            Transaction transaction1 = bankingService.foundTransfer(userId, transaction);
            ApiResponse<Transaction> response = new ApiResponse<>(transaction1, 1, "Transaction Successful!!!");

            return ResponseEntity.ok(response);

        } catch (TransactionException e) {

            throw e;
        }
    }

    @PostMapping("/users/{userId}/payments")
    public ResponseEntity<ApiResponse<Payment>> doPayment(@PathVariable int userId, @RequestBody Payment payment){

        try {
            Payment payment1 = bankingService.doPayment(userId, payment);
            ApiResponse<Payment> response = new ApiResponse<>(payment1, 1, "Payment Successful!!!");

            return ResponseEntity.ok(response);
        } catch (TransactionException e){
            throw e;
        }


    }

    @GetMapping("/users/{userId}/transactions")
    public List<TransactionDTO> showUserTransactions(@PathVariable int userId){
            return bankingService.findUserTransactions(userId);
    }

}
