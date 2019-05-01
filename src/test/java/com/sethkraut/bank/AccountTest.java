package com.sethkraut.bank;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for AccountTest
 */
public class AccountTest {
    private static final double DELTA = .00001;

    @Test
    public void initialDeposit_positiveNumber_hasSameValue() {
        Account account = Account.initialDeposit(10);
        assertEquals(10, account.getAmount(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initialDeposit_negativeNumber_throwsError() {
        Account account = Account.initialDeposit(-10);
    }

    @Test
    public void deposit_positiveNumber_hasCorrectValue() {
        Account account = Account.initialDeposit(10);
        account.deposit(10);
        assertEquals(20, account.getAmount(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deposit_negativeNumber_throwsError() {
        Account account = Account.initialDeposit(10);
        account.deposit(-10);
    }

    @Test
    public void debit_positiveNumber_hasCorrectValue() {
        Account account = Account.initialDeposit(10);
        account.debit(5);
        assertEquals(5, account.getAmount(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void debit_negativeNumber_throwsError() {
        Account account = Account.initialDeposit(10);
        account.debit(-10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void debit_moreThanDeposit_throwsInsufficientFunds() {
        Account account = Account.initialDeposit(10);
        account.debit(25);
    }
}