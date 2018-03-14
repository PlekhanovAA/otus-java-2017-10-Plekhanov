package Aleksey.Plekhanov.app;

import Aleksey.Plekhanov.dbService.DBServiceImpl;
import Aleksey.Plekhanov.messageSystem.Address;
import Aleksey.Plekhanov.messageSystem.Addressee;
import Aleksey.Plekhanov.messageSystem.Message;

public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBServiceImpl) {
            exec((DBServiceImpl) addressee);
        }
    }

    public abstract void exec(DBServiceImpl dbService);
}