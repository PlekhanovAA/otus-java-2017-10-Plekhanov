package Aleksey.Plekhanov;

class Cell {

    private int count;
    private int nominal;

    Cell(int count, int nominal) {
        this.count = count;
        this.nominal = nominal;
    }

    int getCount() {
        return count;
    }

    int getNominal() {
        return nominal;
    }

    void addBanknotes (int count) {
        this.count = this.count + count;
    }

    void removeBanknote () {
        this.count = this.count - 1;
    }

    String getCountToString() {
        return nominal + " cost: " + count + " item";
    }
}