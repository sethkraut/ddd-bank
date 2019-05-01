package com.sethkraut.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
public class AccountController {
    public static final ModelAndView REDIRECT_TO_HOME = new ModelAndView("redirect:/");
    private final AccountService accountService;

    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home", "accounts", accountService.allAccounts());
    }

    @GetMapping("/{id}")
    public ModelAndView account(@PathVariable String id) {
        Map<String, Object> model = new HashMap<>();
        model.put("account", accountService.findAccount(id));
        model.put("transactions", accountService.findTransactionsFor(id).stream()
                                    .map(t -> AccountEntry.from(id, t))
                                    .collect(Collectors.toList())
        );
        return new ModelAndView("account", model);
    }

    @GetMapping("openAccount")
    public ModelAndView openAccountPage() {
        return new ModelAndView("open-account");
    }

    @PostMapping("openAccount")
    public ModelAndView openAccount(OpenAccountForm form) {
        accountService.openAccount(form.getAmount());
        return REDIRECT_TO_HOME;
    }

    @GetMapping("transfer")
    public ModelAndView transferPage() {
        return new ModelAndView("transfer", "accounts", accountService.allAccounts());
    }

    @PostMapping("transfer")
    public ModelAndView transfer(TransferForm form) {
        accountService.perform(new Transaction(form.getFromAccount(), form.getToAccount(), form.getAmount()));
        return REDIRECT_TO_HOME;
    }

    @Data
    private static class OpenAccountForm {
        private double amount;
    }

    @Data
    private static class TransferForm {
        private String fromAccount;
        private String toAccount;
        private double amount;
    }

    @Getter
    @AllArgsConstructor
    private static class AccountEntry {
        private final String account;
        private final double amount;

        private static AccountEntry from(String accountNumber, Transaction transaction) {
            if (transaction.getFromAccount().equals(accountNumber)) {
                return new AccountEntry(transaction.getToAccount(), -transaction.getAmount());
            } else {
                return new AccountEntry(transaction.getFromAccount(), transaction.getAmount());
            }
        }
    }
}
