package Aleksey.Plekhanov.base.dataSets;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @ManyToOne(optional=true)
    @JoinColumn(name="USER_ID", nullable=true)
    private UserDataSet user;

    @Column(name = "number")
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number, UserDataSet user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }
}
