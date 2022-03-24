package sber.ru.dss;

import sber.ru.dss.services.AccountService;

import java.sql.SQLException;
import java.util.List;

import static sber.ru.dss.ApplicationInitializer.initAccountService;

public class DssHw3Application {
    static List<ConnectionInfo> connectionInfos = List.of(
            new ConnectionInfo("jdbc:postgresql://localhost:5432/banking",
                    "postgres", "postgres"
            ),
            new ConnectionInfo(
                    "jdbc:postgresql://localhost:5432/banking_1",
                    "postgres", "postgres"
            )
    );

    public static void main(String[] args) {
        AccountService accountService = initAccountService(connectionInfos);

    //    oneThreadTest(accountService);
        twoThreadsTest(accountService);

        closeConnections();
    }

    private static void closeConnections() {
        for (var info : connectionInfos) {
            try {
                info.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void oneThreadTest(AccountService accountService) {
        accountService.transact(1, 3, 2L);
        accountService.transact(3, 1, 2L);
    }

    public static void twoThreadsTest(AccountService accountService) {
        Thread t1 = new Thread(() -> accountService.transact(1, 2, 2L));
        Thread t2 = new Thread(() -> accountService.transact(1, 3, 2L));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}