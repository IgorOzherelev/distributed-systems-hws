package sber.ru.dss.services;

import sber.ru.dss.AccountPersister;
import sber.ru.dss.entities.Account;

public class AccountService {
    private final AccountPersister accountPersister;

    public AccountService(AccountPersister accountPersister) {
        this.accountPersister = accountPersister;
    }

    public synchronized void transact(long fromId, long toId, Long amount) {
        Account from = accountPersister.getAccountById(fromId);
        Account to = accountPersister.getAccountById(toId);

        checkBalance(from, amount);
        accountPersister.updateAccountBalance(fromId, from.getBalance() - amount);
        accountPersister.updateAccountBalance(toId, to.getBalance() + amount);
    }

    private void checkBalance(Account account, long amount) {
        if (account.getBalance() < amount)
            throw new IllegalStateException("There is wrong amount: " + amount +
                    " account balance: " + account.getBalance());
    }
}
