package Aleksey.Plekhanov.base.dataSets;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {


    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneDataSet> phones = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public UserDataSet() {
    }

    public UserDataSet(String name, AddressDataSet address) {
        this.setId(-1);
        this.name = name;
        this.phones = phones;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    private void setAddress(AddressDataSet address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id'" + getId() + '\'' +
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                ", phone=" + phones +
                '}';
    }
}
