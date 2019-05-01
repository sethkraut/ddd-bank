package com.sethkraut.bank;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByFromAccountOrToAccount(String fromAccount, String toAccount);
}
