public class Cell {

    private int count;

    Cell(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void addBanknotes (int count) {
        this.count = this.count + count;
    }
}
