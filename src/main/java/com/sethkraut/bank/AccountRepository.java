package com.sethkraut.bank;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *
 */
public interface AccountRepository extends MongoRepository<Account, String> {
//    List<AccountNumber> findAllAccountNumbers();
}
