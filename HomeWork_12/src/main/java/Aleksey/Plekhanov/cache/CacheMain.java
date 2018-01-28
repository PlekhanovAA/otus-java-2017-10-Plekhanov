package Aleksey.Plekhanov.cache;

public class CacheMain {

    public static void CacheMain() {
        new CacheMain().softRefCacheExample();
    }

    private void softRefCacheExample() {
        int size = 1000;
        CacheEngine<Integer, Object> cache = new CacheEngineImpl<>(size, 10000, 1000, false);

        for (int i = 0; i < size; i++) {
            cache.put( new MyCacheElement<>(i, new byte[ 1024 * 1024 ]) );
        }

        System.out.println("Cache size = " + cache.getSize());

        for (int i = 0; i < size; i++) {
            cache.get(i);
        }

        //System.out.println("Cache hits: " + cache.getHitCount());
        //System.out.println("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }
}
