package Aleksey.Plekhanov;

import java.sql.*;

class ConnectionHelper {

    static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createTableTEST (Connection connection, Statement statement) {
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "TEST", null);
            if (!resultSet.next()) {
                statement.execute("create table TEST (\n" +
                        "ID bigint (20) identity not null, \n" +
                        "NAME varchar(255), \n" +
                        "AGE  int(3) \n" +
                        ");\n");
                System.out.println("table TEST created \n");
            }else {
                System.out.println("table TEST already exists");
                viewTableTEST(statement);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewTableTEST(Statement statement) {
        try {
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM TEST");
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                Integer age = resultSet.getInt("AGE");
                System.out.println(resultSet.getString("ID") + " " + name + " " + age);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
