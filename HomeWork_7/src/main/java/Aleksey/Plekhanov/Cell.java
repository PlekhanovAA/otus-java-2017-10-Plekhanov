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

    private void removeBanknote () {
        this.count = this.count - 1;
    }

    String getCountToString() {
        return nominal + " cost: " + count + " item";
    }

    int issuance (int sum){
        int countBanknotes = 0;
        while (sum / this.getNominal() > 0 & this.getCount() > 0) {
            countBanknotes++;
            this.removeBanknote();
            sum = sum - this.getNominal();
        }
        return countBanknotes;
    }
}