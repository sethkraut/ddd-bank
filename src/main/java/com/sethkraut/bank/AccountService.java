package com.sethkraut.bank;

import java.util.List;

public interface AccountService {
    List<Account> allAccounts();

    Account openAccount(double amount);

    void perform(Transaction transaction);

    Account findAccount(String accountId);

    List<Transaction> findTransactionsFor(String id);
}
