package Aleksey.Plekhanov.dbService;

import Aleksey.Plekhanov.app.MsgToDB;
import Aleksey.Plekhanov.messageSystem.Address;

public class MsgSysCacheSizeRequest extends MsgToDB {
    public MsgSysCacheSizeRequest(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(DBServiceImpl dbServiceImpl) {
        int hitCount = dbServiceImpl.getHit();
        int missCount = dbServiceImpl.getMiss();
        int size = dbServiceImpl.getCacheSize();
        dbServiceImpl.getMS().sendMessage(new MsgSysCacheSizeResponse(getTo(), getFrom(), hitCount, missCount, size));
    }
}