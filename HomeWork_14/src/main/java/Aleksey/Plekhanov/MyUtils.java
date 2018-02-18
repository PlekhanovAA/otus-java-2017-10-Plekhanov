package Aleksey.Plekhanov;

import java.util.ArrayList;
import java.util.List;

class MyUtils {

    static ArrayList<Integer> threadsFiling (int count, int size, List<ArrayList<Integer>> parts) {
        ArrayList<Integer> result = new ArrayList<>();
        List<Thread> threadPoolFill = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ArrayList<Integer> part = new ArrayList<>();
            Fill fill = new Fill(size, part);
            Thread thread = new Thread(fill);
            threadPoolFill.add(thread);
            parts.add(part);
        }
        startThreads(threadPoolFill);
        joinThreads(threadPoolFill);
        for (ArrayList<Integer> part : parts) {
            result.addAll(part);
        }
        return result;
    }

    static ArrayList<Integer> threadsSorting (int count, List<ArrayList<Integer>> parts) {
        List<Thread> threadPoolSort = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Sort sort = new Sort(parts.get(i));
            Thread thread = new Thread(sort);
            threadPoolSort.add(thread);
        }
        startThreads(threadPoolSort);
        joinThreads(threadPoolSort);
        ArrayList<Integer> result = parts.get(0);
        for (int i = 1; i < count; i++) {
            result = mergeTwoLists(result, parts.get(i));
        }
        return result;
    }

    private static ArrayList<Integer> mergeTwoLists(ArrayList<Integer> one, ArrayList<Integer> two) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < one.size() && j < two.size()) {
            if (one.get(i) < two.get(j)) {
                result.add(one.get(i));
                i++;
            } else {
                result.add(two.get(j));
                j++;
            }
        }
        while (i < one.size()) {
            result.add(one.get(i));
            i++;
        }
        while (j < two.size()) {
            result.add(two.get(j));
            j++;
        }
        return result;
    }

    private static void startThreads (List<Thread> threadPool) {
        for (Thread thread : threadPool) {
            thread.start();
        }
    }

    private static void joinThreads (List<Thread> threadPool) {
        for (Thread thread : threadPool) {
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
