package Aleksey.Plekhanov.app;

import Aleksey.Plekhanov.messageSystem.Addressee;

public interface FrontendService extends Addressee {
    void init();

    void getCacheStats();

    void setCacheStats(int hitCount, int missCount, int size);
}