package sber.ru.dss;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class StatementSupplier {
    public static Statement getStatement(ConnectionInfo info) {
        try {
            return info.createConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement getPreparedStatement(
            ConnectionInfo info,
            String query
    ) {
        try {
            return info.createConnection().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
