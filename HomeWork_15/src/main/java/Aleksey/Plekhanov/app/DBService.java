package Aleksey.Plekhanov.app;

import Aleksey.Plekhanov.base.dataSets.UserDataSet;
import Aleksey.Plekhanov.cache.CacheEngine;
import Aleksey.Plekhanov.messageSystem.Addressee;
import Aleksey.Plekhanov.messageSystem.MessageSystem;

import java.util.List;

public interface DBService extends Addressee {
    void init();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();

    MessageSystem getMS();

    CacheEngine getCache();
}
