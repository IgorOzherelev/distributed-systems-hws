package sber.ru.dss;

import lombok.AllArgsConstructor;
import sber.ru.dss.dao.AccountDao;
import sber.ru.dss.entities.Account;

import java.sql.PreparedStatement;
import java.util.List;

@AllArgsConstructor
public class AccountPersister {
    private final List<PreparedStatement> preparedStatementsForUpdate;
    private final List<PreparedStatement> preparedStatementsForFindById;

    public Account getAccountById(long fromId) {
        return preparedStatementsForFindById
                .stream()
                .map(preparedStatement -> AccountDao.getAccountById(
                        preparedStatement, fromId))
                .filter(acc -> acc != null && acc.getId() != 0)
                .toList().get(0);
    }

    public void updateAccountBalance(long accountId, long amount) {
        preparedStatementsForUpdate
                .forEach(preparedStatement ->
                        AccountDao.updateAccountBalance(
                                preparedStatement, accountId, amount)
                );
    }
}

