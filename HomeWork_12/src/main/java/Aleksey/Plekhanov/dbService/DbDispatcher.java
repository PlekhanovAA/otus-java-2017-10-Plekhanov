package Aleksey.Plekhanov.dbService;

import Aleksey.Plekhanov.base.DBService;
import Aleksey.Plekhanov.base.dataSets.AddressDataSet;
import Aleksey.Plekhanov.base.dataSets.PhoneDataSet;
import Aleksey.Plekhanov.base.dataSets.UserDataSet;

import java.util.List;

public class DbDispatcher {
    static DBServiceImpl dbService;

    public static void startDb() throws InterruptedException{
        dbService = new DBServiceImpl();
        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);
        System.out.println("Cache size: " + dbService.getSizeCache());

        UserDataSet aleksey = new UserDataSet("Aleksey", new AddressDataSet("AStreet"));
        aleksey.getPhones().add(new PhoneDataSet("1-111111", aleksey));
        aleksey.getPhones().add(new PhoneDataSet("1-222222", aleksey));
        dbService.save(aleksey);
        System.out.println("Cache size: " + dbService.getSizeCache());
        UserDataSet notaleksey = new UserDataSet("Notaleksey", new AddressDataSet("NAStreet"));
        notaleksey.getPhones().add(new PhoneDataSet("2-111111", notaleksey));
        notaleksey.getPhones().add(new PhoneDataSet("2-222222", notaleksey));
        dbService.save(notaleksey);
        System.out.println("Cache size: " + dbService.getSizeCache());
        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("Notaleksey");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }
        Thread.sleep(1000);
        //System.out.println("Cache size: " + dbService.getSizeCache());
        //dbService.shutdown();
    }

    public int getSizeCache() {
        return dbService.getSizeCache();
    }


    public static DBServiceImpl getDbService() {
        return dbService;
    }
}
