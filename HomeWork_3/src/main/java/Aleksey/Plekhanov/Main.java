package Aleksey.Plekhanov;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main (String... arg) {
        List<Integer> massifList = new MassifList<>();
        for (int i = 0; i < 15; i++) {
            massifList.add(i);
        }
        massifList.add(-5);
        massifList.add(111);
        massifList.add(110);
        System.out.println("size: " + massifList.size());
        System.out.println("not sorted: ");
        for (int i = 0; i < massifList.size(); i++) {
            System.out.println(massifList.get(i));
        }
        Collections.sort(massifList);
        System.out.println("sorted: ");
        for (int i = 0; i < massifList.size(); i++) {
            System.out.println(massifList.get(i));
        }
    }
}

