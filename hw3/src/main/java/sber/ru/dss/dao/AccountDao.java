package sber.ru.dss.dao;

import sber.ru.dss.entities.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    public static Account getAccountById(PreparedStatement preparedStatement, long accountId) {
        Account account = new Account();
        ResultSet rs;
        try {
            preparedStatement.setLong(1, accountId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getLong("balance"));
            }

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateAccountBalance(PreparedStatement preparedStatement,
                                            long accountId, long amount
    ) {
        try {
            preparedStatement.setLong(1, amount);
            preparedStatement.setLong(2, accountId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}