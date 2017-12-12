package Aleksey.Plekhanov;

import java.util.Arrays;
import java.util.List;

class TestClass {
    private Integer a;
    private String b;
    private int[] c;
    private Boolean d;
    private List list;
    private SimpleTestClass[] simpleTestClass;

    TestClass(Integer a, String b, int[] c, boolean d, List list, SimpleTestClass[] simpleTestClass) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.list = list;
        this.simpleTestClass = simpleTestClass;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Value Integer field 'a': " + a + "\n" +
                "Value String field 'b': " + b + "\n" +
                "Value array field 'c': " + Arrays.toString(c) + "\n" +
                "Value boolean field 'd': " + d + "\n" +
                "Value collection field 'list': " + list + "\n" +
                "Value array objects 'simpleTestClass': ");
        for (SimpleTestClass stc:simpleTestClass) {
            result.append(stc.toString());
        }
        return result.toString();
    }
}
