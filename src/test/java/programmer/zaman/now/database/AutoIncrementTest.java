package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1,"hanif@test.com");
            preparedStatement.setString(2,"hi");

        preparedStatement.executeUpdate();

        preparedStatement.getGeneratedKeys();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            System.out.println("ID Comment : " + resultSet.getInt(1));
        }

        preparedStatement.close();
        connection.close();
    }
}
