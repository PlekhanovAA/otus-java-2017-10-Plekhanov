package Aleksey.Plekhanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static Aleksey.Plekhanov.MyUtils.threadsFiling;
import static Aleksey.Plekhanov.MyUtils.threadsSorting;

public class Main {

    private static final Logger log = LogManager.getLogger();
    private static final int SIZE_PART = 5;
    private static final int COUNT_THREADS = 4;

    public static void main (String [] args) {
        List<ArrayList<Integer>> parts = new ArrayList<>();
        ArrayList<Integer> unsortedList = threadsFiling(COUNT_THREADS, SIZE_PART, parts);
        log.info("unsorted list: " + unsortedList);
        ArrayList<Integer> sortedList = threadsSorting(COUNT_THREADS, parts);
        log.info(" sorted list:  " + sortedList);
    }
}
