package com.mybanking.bankingapp.dto;

import com.mybanking.bankingapp.model.User;

public class UserDTO {

    private int id;

    private String name;

    private String username;

    private Double balance;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String username, Double balance) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }

    public UserDTO convertToDTO(User user){
        return new UserDTO(user.getId(),user.getName(), user.getUsername(), user.getBalance());
    }
}
