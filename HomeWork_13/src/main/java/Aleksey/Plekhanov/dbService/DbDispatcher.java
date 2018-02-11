package Aleksey.Plekhanov.dbService;

import Aleksey.Plekhanov.base.dataSets.AddressDataSet;
import Aleksey.Plekhanov.base.dataSets.PhoneDataSet;
import Aleksey.Plekhanov.base.dataSets.UserDataSet;
import Aleksey.Plekhanov.cache.CacheInfo;

import java.util.List;

public class DbDispatcher {
    private static DBServiceImpl dbService;

    public DbDispatcher() {
    }

    public void startDb() {
        dbService = new DBServiceImpl();
        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);
        System.out.println("Cache size: " + dbService.getCacheSize());

        UserDataSet aleksey = new UserDataSet("Aleksey", new AddressDataSet("AStreet"));
        aleksey.getPhones().add(new PhoneDataSet("1-111111", aleksey));
        aleksey.getPhones().add(new PhoneDataSet("1-222222", aleksey));
        dbService.save(aleksey);
        System.out.println("Cache size: " + dbService.getCacheSize());
        UserDataSet notaleksey = new UserDataSet("Notaleksey", new AddressDataSet("NAStreet"));
        notaleksey.getPhones().add(new PhoneDataSet("2-111111", notaleksey));
        notaleksey.getPhones().add(new PhoneDataSet("2-222222", notaleksey));
        dbService.save(notaleksey);
        System.out.println("Cache size: " + dbService.getCacheSize());
        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("Notaleksey");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }
        dbService.shutdown();
    }

    public static CacheInfo getCacheService() {
        return dbService;
    }
}
