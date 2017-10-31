package Aleksey.Plekhanov;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main (String... arg) {
        List<Integer> massifList1 = new MassifList<>();
        for (int i = 0; i < 5; i++) {
            massifList1.add(i);
        }
        Collections.addAll(massifList1, -5, 111, 110);
        List<Integer> massifList2 = new MassifList<>();
        massifList2.add(888);
        massifList2.add(666);
        massifList2.add(777);
        Collections.copy(massifList1, massifList2);
        System.out.println("size: " + massifList1.size());
        System.out.println("not sorted: ");
        for (int i = 0; i < massifList1.size(); i++) {
            System.out.println(massifList1.get(i));
        }
        Collections.sort(massifList1);
        System.out.println("sorted: ");
        for (int i = 0; i < massifList1.size(); i++) {
            System.out.println(massifList1.get(i));
        }
    }
}

