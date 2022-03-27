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

    public Account getAccountById(long accountId) {
        var list = preparedStatementsForFindById
                .stream()
                .map(preparedStatement -> AccountDao.getAccountById(
                        preparedStatement, accountId))
                .filter(acc -> acc != null && acc.getId() != 0)
                .toList();

        if (list.size() != 0) return list.get(0);
        else throw new IllegalArgumentException("The account id: "
                + accountId + " does not exist");
    }

    public void updateAccountBalance(long accountId, long amount) {
        preparedStatementsForUpdate
                .forEach(preparedStatement ->
                        AccountDao.updateAccountBalance(
                                preparedStatement, accountId, amount)
                );
    }
}

