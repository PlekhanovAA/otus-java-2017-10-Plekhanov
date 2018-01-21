package Aleksey.Plekhanov.cache;

public interface CacheEngine<K, V> {

    void put(MyCache<K, V> element);

    MyCache<K, V> get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    int getSize();
}
