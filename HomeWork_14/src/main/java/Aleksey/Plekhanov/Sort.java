package Aleksey.Plekhanov;

import java.util.List;

public class Sort implements Runnable {

    private List<Integer> list;

    Sort(List<Integer> list) {
        this.list = list;
    }

    public void run() {
        sort(list);
    }

    private static void sort(List<Integer> list) {
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < list.size() - 1; i++) {
                int cur = list.get(i);
                int next = list.get(i + 1);
                if (cur > next) {
                    list.set(i, next);
                    list.set(i + 1, cur);
                    changed = true;
                }
            }
        } while (changed);
    }
}
