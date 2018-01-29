package Aleksey.Plekhanov.base;

import Aleksey.Plekhanov.base.dataSets.UserDataSet;

import java.util.List;

public interface DBService {

    String getLocalStatus();

    int getSizeCache();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
