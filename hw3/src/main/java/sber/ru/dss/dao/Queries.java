package sber.ru.dss.dao;

import lombok.Getter;

public enum Queries {
    FIND_BY_ID("SELECT * FROM accounts WHERE accounts.id = ?"),
    UPDATE_BALANCE("UPDATE accounts SET balance = ? WHERE id = ?");

    @Getter
    private final String query;

    Queries(String query) {
        this.query = query;
    }
}