package Aleksey.Plekhanov.cache;

public interface CacheEngine<K, V> {

    void put(MyCacheElement<K, V> element);

    MyCacheElement<K, V> get(K key);

    MyCacheElement<K, V> getByName(String name);

    int getHitCount();

    int getMissCount();

    void dispose();

    int getSize();
}
