package com.mybanking.bankingapp.controller;

import com.mybanking.bankingapp.response.LoginRequest;
import com.mybanking.bankingapp.response.LoginResponse;
import com.mybanking.bankingapp.model.User;
import com.mybanking.bankingapp.response.ApiResponse;
import com.mybanking.bankingapp.service.BankingService;
import com.mybanking.bankingapp.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private BankingService bankingService;
    private JWTService jwtService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, BankingService bankingService, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.bankingService = bankingService;
        this.jwtService = jwtService;
    }

    //This only for development help
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@RequestBody User user){

        if (bankingService.findUserByUsername(user.getUsername()).isPresent()){

            ApiResponse<LoginResponse> response = new ApiResponse<>(0, "Already Registered User!");

            return ResponseEntity.badRequest().body(response);
        }
        user.setId(0);
        user.setRole("USER");
        User user1 = bankingService.save(user);
        LoginResponse responseLogin = new LoginResponse(user1.getId(), user1.getUsername(), jwtService.generateToken(user1.getUsername()));
        ApiResponse<LoginResponse> response = new ApiResponse<>(responseLogin, 1, "Successful Request");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        if (!authentication.isAuthenticated()){
            throw new UsernameNotFoundException("No any user for this username!");
        }

        Optional<User> user = bankingService.findUserByUsername(request.getUsername());

        System.out.println("Login Passed: "+user.get().getUsername());

        LoginResponse responseUser = new LoginResponse(user.get().getId(), user.get().getUsername(), jwtService.generateToken(user.get().getUsername()));

        ApiResponse<LoginResponse> response = new ApiResponse<>(responseUser, 1, "Login successful");

        return ResponseEntity.ok(response);
    }
}
