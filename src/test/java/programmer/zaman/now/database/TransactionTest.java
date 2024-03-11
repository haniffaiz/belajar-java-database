package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
    @Test
    void testCommit() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";




        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.clearParameters();
            preparedStatement.setString(1,"hanif@test.com");
            preparedStatement.setString(2,"hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }


        connection.commit();
        connection.close();

    }

    @Test
    void testRollback() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.clearParameters();
            preparedStatement.setString(1,"hanif@test.com");
            preparedStatement.setString(2,"hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }

        connection.rollback();
        connection.close();

    }
}
