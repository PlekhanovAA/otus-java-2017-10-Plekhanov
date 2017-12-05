package Aleksey.Plekhanov;

import java.util.LinkedHashMap;
import java.util.Map;

class Atm {

    private int count;
    private Map<Integer, Cell> allCells = new LinkedHashMap<>();

    Atm(int count) {
        Banknotes.getAllBanknotes().forEach((k) -> allCells.put(k, new Cell(count, k)));
        this.count = count;
    }

    int getVolume() {
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
        System.out.println("4. Back to atm department menu.");
    }

    void getStateAtm (){
        System.out.println("Count of banknotes");
        allCells.forEach((k, v) -> System.out.println(v.getCountToString()));
        System.out.println("Balance: " + getVolume());
    }

    void printMenuReceiving() {
        System.out.println("------- RECEIVING -------");
        System.out.println("Choice nominal:");
        allCells.forEach((k, v) -> System.out.println(v.getNominal()));
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
                int countBanknotes = cell.issuance(sum);
                buffer.put(cell.getNominal(), countBanknotes);
                sum = sum - cell.getNominal() * countBanknotes;
            }
            if (sum == 0) {
                System.out.println("OK");
                for (Integer nominal : buffer.keySet()) {
                    if (buffer.get(nominal) != 0) {
                        System.out.println("Issued " + nominal + " nominal: " + buffer.get(nominal) + " banknotes");
                    }
                }
                System.out.println("Balance: " + getVolume());
            } else {
                System.out.println("Incorrect sum");
                buffer.forEach((k, v) -> allCells.get(k).addBanknotes(v));
            }
        }
    }

   public void revert () {
       for (Cell cell : allCells.values()) {
           int addCount = count - cell.getCount();
           if (addCount != 0) {
               cell.addBanknotes(addCount);
               System.out.println("Recycled " + addCount + " banknotes of " + cell.getNominal());
           }
       }
   }
}