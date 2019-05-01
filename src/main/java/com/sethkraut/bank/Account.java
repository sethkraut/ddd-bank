package com.sethkraut.bank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@TypeAlias("Account")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Account {
    private String id;
    private double amount;

    public static Account initialDeposit(double amount) {
        requirePositiveAmount(amount);
        return new Account(UUID.randomUUID().toString(), amount);
    }

    public void deposit(double amount) {
        requirePositiveAmount(amount);
        this.amount += amount;
    }

    public void debit(double amount) {
        requirePositiveAmount(amount);
        if (this.amount < amount) {
            throw new InsufficientFundsException();
        }
        this.amount -= amount;
    }

    private static void requirePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must be positive");
        }
    }
}
