package Aleksey.Plekhanov;

public class Atm {

    private Cell cellHundred;
    private Cell cellFiveHundred;
    private Cell cellThousand;
    private Cell cellFiveThousand;

     Atm(int count) {
        this.cellHundred = new Cell(count);
        this.cellFiveHundred = new Cell(count);
        this.cellThousand = new Cell(count);
        this.cellFiveThousand = new Cell(count);
    }

    private int getCellHundredCount() {
        return cellHundred.getCount();
    }

    private int getCellFiveHundredCount() {
        return cellFiveHundred.getCount();
    }

    private int getCellThousandCount() {
        return cellThousand.getCount();
    }

    private int getCellFiveThousandCount() {
        return cellFiveThousand.getCount();
    }

    void getState (){
        System.out.println("------- INFO -------");
        System.out.println("Count of banknotes");
        System.out.println(" 100 cost: " + getCellHundredCount() + " item");
        System.out.println(" 500 cost: " + getCellFiveHundredCount() + " item");
        System.out.println("1000 cost: " + getCellThousandCount() + " item");
        System.out.println("5000 cost: " + getCellFiveThousandCount() + " item");
    }
}
