package Aleksey.Plekhanov;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        return "Value Integer field 'a': " + a + "\n" +
                "Value String field 'b': " + b + "\n" +
                "Value array field 'c': " + Arrays.toString(c) + "\n" +
                "Value boolean field 'd': " + d + "\n" +
                "Value collection field 'list': " + list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return Objects.equals(a, testClass.a) &&
                Objects.equals(b, testClass.b) &&
                Arrays.equals(c, testClass.c) &&
                Objects.equals(d, testClass.d) &&
                Objects.equals(list, testClass.list) &&
                Arrays.equals(simpleTestClass, testClass.simpleTestClass);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(a, b, d, list);
        result = 31 * result + Arrays.hashCode(c);
        result = 31 * result + Arrays.hashCode(simpleTestClass);
        return result;
    }
}
