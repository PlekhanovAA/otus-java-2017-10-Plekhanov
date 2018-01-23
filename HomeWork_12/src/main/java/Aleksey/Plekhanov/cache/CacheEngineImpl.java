package Aleksey.Plekhanov.cache;

import Aleksey.Plekhanov.base.dataSets.UserDataSet;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<K, SoftReference<MyCacheElement<K, V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    public void put(MyCacheElement<K, V> elementStrongRef) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        K key = elementStrongRef.getKey();

        SoftReference<MyCacheElement<K,V>> element = new SoftReference<>(elementStrongRef);

        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        elements.put(key, element);

        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    public MyCacheElement<K, V> get(K key) {
        SoftReference<MyCacheElement<K, V>> elementSoftRef = elements.get(key);
        if (elementSoftRef != null) {
            hit++;
            elementSoftRef.get().setAccessed();
        } else {
            miss++;
        }
        return null;
    }

    public MyCacheElement<K, V> getByName (String name) {
        for (SoftReference<MyCacheElement<K, V>> elementSoftRef : elements.values()) {
            MyCacheElement<K, V> myCacheElement = elementSoftRef.get();
            UserDataSet userDataSet = (UserDataSet) myCacheElement.getValue();
            String thisName = userDataSet.getName();
            if (thisName.equals(name)) {
                hit++;
            }else {
                miss++;
            }
        }
        return null;
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    public int getSize() {
        return elements.size();
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<MyCacheElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<MyCacheElement<K, V>> elementSoftRef = elements.get(key);
                if (elementSoftRef==null) {
                    this.cancel();
                    return;
                }
                MyCacheElement<K, V> element = elementSoftRef.get();
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }


    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
