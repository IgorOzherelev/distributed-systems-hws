package sber.ru.dss;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
class ConnectionInfo {
    private String url;
    private String username;
    private String password;

    private Connection connection;

    public ConnectionInfo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection createConnection() throws SQLException {
        connection = DriverManager.getConnection(this.getUrl(),
                this.getUsername(), this.getPassword());
        return connection;
    }
}
