package Aleksey.Plekhanov;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.Date;

public class CollectorsManager {

    private CurrentCollector majorCollector;
    private CurrentCollector minorCollector;
    private String name;
    private Date startTime;

    CollectorsManager(String name) {
        majorCollector = new CurrentCollector("major collector");
        minorCollector = new CurrentCollector("minor collector");
        this.name = name;
        startTime = new Date();
    }

    void MarkCollectors(GarbageCollectionNotificationInfo info) {
        switch (info.getGcAction()) {
            case "end of minor GC": {minorCollector.MarkCollector(info.getGcInfo().getDuration()); break;}
            case "end of major GC": {majorCollector.MarkCollector(info.getGcInfo().getDuration()); break;}
        }
    }

    void clear() {
        majorCollector.newCollector();
        minorCollector.newCollector();
        startTime = new Date();
    }

    @Override
    public String toString() {
        return name + " (started " + startTime + ")\n" +
                minorCollector + "\n" +
                majorCollector;
    }
}
