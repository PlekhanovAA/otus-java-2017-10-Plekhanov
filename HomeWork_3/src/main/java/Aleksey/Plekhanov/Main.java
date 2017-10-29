package Aleksey.Plekhanov;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String... arg) {
        List<Integer> massifList = new MassifList();

        for (int i = 0; i < 25; i++) {
            massifList.add(i);
        }
        for (int i = 0; i < 25; i++) {
            System.out.println("element "+ i +" = " + massifList.get(i));
        }
        System.out.println(massifList.size());
    }
}
