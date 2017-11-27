package Aleksey.Plekhanov;

import java.util.LinkedList;
import java.util.List;

class Banknotes {

    private static List<Integer> allBanknotes = new LinkedList<>();

    static List<Integer> getAllBanknotes() {
        allBanknotes.add(5000);
        allBanknotes.add(1000);
        allBanknotes.add(500);
        allBanknotes.add(100);
        return allBanknotes;
    }
}