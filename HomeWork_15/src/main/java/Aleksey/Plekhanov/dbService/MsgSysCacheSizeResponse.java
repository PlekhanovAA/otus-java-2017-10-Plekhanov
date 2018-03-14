package Aleksey.Plekhanov.dbService;

import Aleksey.Plekhanov.app.FrontendService;
import Aleksey.Plekhanov.app.MsgToFrontend;
import Aleksey.Plekhanov.messageSystem.Address;

public class MsgSysCacheSizeResponse extends MsgToFrontend {
    private final int hitCount;
    private final int missCount;
    private final int size;

    public MsgSysCacheSizeResponse(Address from, Address to, int hitCount, int missCount, int size) {
        super(from, to);
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.size = size;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.setCacheStats(hitCount, missCount, size);
    }
}