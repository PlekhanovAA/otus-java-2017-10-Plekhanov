package Aleksey.Plekhanov;

import static Aleksey.Plekhanov.dbService.DbDispatcher.startDb;
import static Aleksey.Plekhanov.servlet.ServerDispatcher.startServer;

public class Main {

    public static void main(String[] args) throws Exception {
        startDb();
        startServer();
    }
}
