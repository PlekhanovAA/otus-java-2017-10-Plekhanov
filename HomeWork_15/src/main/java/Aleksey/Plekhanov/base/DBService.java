package Aleksey.Plekhanov.base;

import Aleksey.Plekhanov.base.dataSets.UserDataSet;
import Aleksey.Plekhanov.messageSystem.Addressee;

import java.util.List;

public interface DBService extends Addressee {

    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
