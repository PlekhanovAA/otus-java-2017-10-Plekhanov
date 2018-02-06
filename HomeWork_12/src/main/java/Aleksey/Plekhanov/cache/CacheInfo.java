package Aleksey.Plekhanov.cache;

public interface CacheInfo {

    int getMiss();

    int getHit();

    int getCacheSize();
}