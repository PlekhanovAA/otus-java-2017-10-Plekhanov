package Aleksey.Plekhanov;

import java.sql.*;

class Main {

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            Statement statement = connection.createStatement();
            ConnectionHelper.createTableTEST (connection, statement);

            UserDataSet userDataSetTo = new UserDataSet("Aleksey", 33);
            System.out.println("to DB   ______________________ \n" + userDataSetTo.toString() + "\n");
            Executor executor = new Executor(statement);
            executor.save(userDataSetTo);

            UserDataSet userDataSetFrom = executor.load(1, UserDataSet.class);
            System.out.println("from DB ______________________ \n" + userDataSetFrom.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

