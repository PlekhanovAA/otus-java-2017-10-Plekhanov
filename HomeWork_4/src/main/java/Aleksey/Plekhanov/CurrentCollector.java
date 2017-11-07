package Aleksey.Plekhanov;

public class CurrentCollector {

    private String type;
    private int countCleanup;
    private  long totalTime;

    CurrentCollector (String type) {
        this.type = type;
        newCollector();
    }

    void newCollector() {
        countCleanup = 0;
        totalTime = 0;
    }

    void MarkCollector (long time) {
        countCleanup++;
        totalTime += time;
    }

    @Override
    public String toString() {
        return "Type: " + type + "; count cleanup: " + countCleanup + "; total time: " + totalTime + " ms (" + totalTime / 1000 + " s " + totalTime % 1000 + " ms)";
    }
}
