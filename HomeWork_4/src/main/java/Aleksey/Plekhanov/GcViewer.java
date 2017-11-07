package Aleksey.Plekhanov;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.GarbageCollectorMXBean;
import java.util.Date;
import java.util.List;

public class GcViewer {
    private CollectorsManager inMinuteCleanup;
    private CollectorsManager totalCleanup;
    private Date timeStart = new Date();

    GcViewer() {
        startGCMonitoring();

        inMinuteCleanup = new CollectorsManager("In minute: ");
        totalCleanup = new CollectorsManager("Total: ");
        int delay = 30_000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String log = inMinuteCleanup.toString();
                inMinuteCleanup.clear();

                System.out.println("_________________________________________________________________________\n" +
                        "Collectors marked by " + new Date() + "\n" +
                        log + "\n" +
                        totalCleanup + "\n" +
                        "Free memory: " + Runtime.getRuntime().freeMemory() + "\n" +
                        "Total time: " + ((new Date().getTime()-timeStart.getTime())/1000) + " sec. \n" +
                        "_________________________________________________________________________");

            }
        };
        new Timer(delay, taskPerformer).start();
    }

    private void startGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    inMinuteCleanup.MarkCollectors(info);
                    totalCleanup.MarkCollectors(info);
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
