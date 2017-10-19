package Plekhanov.Aleksey;
import net.time4j.Moment;
import net.time4j.SystemClock;

public class Main {

    public static void main(String[] args) {
        Moment now = SystemClock.currentMoment();
        System.out.println(now);
    }
}
