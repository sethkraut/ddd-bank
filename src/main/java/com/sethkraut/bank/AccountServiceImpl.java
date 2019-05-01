package com.sethkraut.bank;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    public List<Transaction> findTransactionsFor(String accountNumber) {
        return transactionRepository.findAllByFromAccountOrToAccount(accountNumber, accountNumber);
    }

    @Override
    public Account openAccount(double amount) {
        log.debug("Opening account with amount {}", amount);
        return accountRepository.save(Account.initialDeposit(amount));
    }

    @Override
    public void perform(Transaction transaction) {
        if (transaction.getFromAccount().equals(transaction.getToAccount())) {
            throw new IllegalArgumentException("You can't transfer between the same account");
        }

        Account fromAccount = findAccount(transaction.getFromAccount());
        Account toAccount = findAccount(transaction.getToAccount());

        fromAccount.debit(transaction.getAmount());
        toAccount.deposit(transaction.getAmount());

        accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
        transactionRepository.save(transaction);
    }

    @Override
    public Account findAccount(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
