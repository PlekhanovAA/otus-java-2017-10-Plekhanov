package Aleksey.Plekhanov;

public class UserDataSet extends DataSet{

    private String name;
    private Integer age;

    UserDataSet() {}

    UserDataSet(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "        name: "+name+", age: "+age;
    }
}
