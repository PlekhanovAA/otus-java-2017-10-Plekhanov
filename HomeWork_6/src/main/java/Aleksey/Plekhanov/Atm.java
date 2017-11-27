package Aleksey.Plekhanov;

import java.util.LinkedHashMap;
import java.util.Map;

class Atm {

    private static Map<Integer, Cell> allCells = new LinkedHashMap<>();

    Atm(int count) {
        for (Integer nominal : Banknotes.getAllBanknotes()) {
            allCells.put(nominal, new Cell(count, nominal));
        }
    }

    private int getVolume() {
        int volume = 0;
        for (Cell cell: allCells.values()) {
            volume = volume + cell.getCount() * cell.getNominal();
        }
        return volume;
    }

    void printMenu() {
        System.out.println("---------- MENU ---------");
        System.out.println("1. State ATM;");
        System.out.println("2. Receiving banknotes;");
        System.out.println("3. Returning banknotes;");
        System.out.println("4. Exit.");
    }

    void getState (){
        System.out.println("---------- INFO ---------");
        System.out.println("Count of banknotes");
        for (Cell cell: allCells.values()) {
            System.out.println(cell.getCountToString());
        }
    }

    void printMenuReceiving() {
        System.out.println("------- RECEIVING -------");
        System.out.println("Choice nominal:");
        for (Cell cell: allCells.values()) {
            System.out.println(cell.getNominal());
        }
    }

    void receiving(int nominal, int count) {
        if (allCells.get(nominal) != null) {
            allCells.get(nominal).addBanknotes(count);
            System.out.println("OK");
        } else {
            System.out.println("Incorrect nominal");
        }
    }

    void returning(int sum) {
        if (sum > getVolume()) {
            System.out.println("Not enough money");
        } else {
            Map<Integer, Integer> buffer = new LinkedHashMap<>();
            for (Cell cell : allCells.values()) {
                int countBanknotes = issuance(sum, cell);
                buffer.put(cell.getNominal(), countBanknotes);
                sum = sum - cell.getNominal() * countBanknotes;
            }
            if (sum == 0) {
                System.out.println("OK");
            } else {
                System.out.println("Incorrect sum");
                for (Integer nominal : buffer.keySet()) {
                    allCells.get(nominal).addBanknotes(buffer.get(nominal));
                }
            }
        }
    }

    private int issuance (int sum, Cell cell){
        int countBanknotes = 0;
        while (sum / cell.getNominal() > 0 & cell.getCount() > 0) {
            countBanknotes++;
            cell.removeBanknote();
            sum = sum - cell.getNominal();
        }
        return countBanknotes;
    }
}