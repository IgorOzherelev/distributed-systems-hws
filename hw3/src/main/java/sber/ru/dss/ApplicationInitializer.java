package sber.ru.dss;

import sber.ru.dss.services.AccountService;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static sber.ru.dss.dao.Queries.FIND_BY_ID;
import static sber.ru.dss.dao.Queries.UPDATE_BALANCE;

public class ApplicationInitializer {
    public static AccountService initAccountService(List<ConnectionInfo> connectionInfos) {
        List<PreparedStatement> preparedStatementsForFindById = new ArrayList<>();
        List<PreparedStatement> preparedStatementsForUpdate = new ArrayList<>();

        initPreparedStatements(connectionInfos,
                preparedStatementsForFindById, preparedStatementsForUpdate);

        AccountPersister accountPersister = new AccountPersister(preparedStatementsForUpdate,
                preparedStatementsForFindById);
        return new AccountService(accountPersister);
    }

    private static void initPreparedStatements(List<ConnectionInfo> connectionInfos,
                                               List<PreparedStatement> preparedStatementsForFindById,
                                               List<PreparedStatement> preparedStatementsForUpdate) {
        for (var info : connectionInfos) {
            preparedStatementsForFindById
                    .add(StatementSupplier
                            .getPreparedStatement(info, FIND_BY_ID.getQuery()));
            preparedStatementsForUpdate
                    .add(StatementSupplier
                            .getPreparedStatement(info, UPDATE_BALANCE.getQuery()));
        }
    }
}
