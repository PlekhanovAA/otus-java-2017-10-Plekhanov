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

    public Cell getCellHundred() {
        return cellHundred;
    }

    public Cell getCellFiveHundred() {
        return cellFiveHundred;
    }

    public Cell getCellThousand() {
        return cellThousand;
    }

    public Cell getCellFiveThousand() {
        return cellFiveThousand;
    }
}
