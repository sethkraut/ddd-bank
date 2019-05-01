package com.sethkraut.bank;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountNumber) {
        super("No account found with ID " + accountNumber);
    }
}
