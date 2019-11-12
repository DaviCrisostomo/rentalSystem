package com.ultravision.rentalsys.model.Customer;
import com.ultravision.rentalsys.model.Rent.Rent;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private AccessLevel accLevel;

    private int points;

    @Column(unique = true)
    @Size(max=16, min = 16)
    private Long crdtCrdNumber;


    private LocalDate lastUpdate;

    private int rentedItems;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable =false)
    private Customer customer;

    @OneToMany(mappedBy = "card",fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    public List<Rent> rents = new ArrayList<>();

    public MembershipCard(){}

    public MembershipCard(Long crdtCrdNumber){

        this.crdtCrdNumber = crdtCrdNumber;
        this.points = 0;
        this.accLevel = AccessLevel.RC;
        this.lastUpdate = LocalDate.now();
    }

    public void setCustomer(Customer c){ this.customer = c;}

    public AccessLevel getAccLevel() {
        return accLevel;
    }

    public void setAccLevel(AccessLevel accLevel) {this.accLevel = accLevel;}

    public void setLastUpdate(){this.lastUpdate = LocalDate.now();}

    public void setLastUpdate(LocalDate date){ this.lastUpdate = date; }

    public LocalDate getlastUpdate() {
        return lastUpdate;
    }

    public void setCrdtCrdNumber(Long crdtCrdNumber) {
        this.crdtCrdNumber = crdtCrdNumber;
    }

    public void addPoints(){
        this.points += 10;
    }

    public boolean usePoints(){

        if (points>=100) {
            this.points -= 100;
            return true;
        }
        return false;
    }

    public int getPoints() { return  points; }

    public void addRent(){

        this.rentedItems++;
    }

    public void returnRent(){
        this.rentedItems--;
    }

    public int getRentedItems(){
        return rentedItems;
    }

    public void rentInOut(Rent rent){

        if(this.rents.remove(rent))
            rent.setCard(null);
        else
            rents.add(rent);
    }

    public Long getCustomerId(){

       return customer.getCustomerId();

    }

    public int getRentsSize(){return rents.size();}

    public void payment(Double value){

        class ProcessPayment{

            public double valueToPay;
            public Long crdtCrd;

            ProcessPayment(double valueToPay,Long crdtCrd){

                this.valueToPay = valueToPay;
                this.crdtCrd = crdtCrd;

            }

            public boolean payValue(){
                //connect to the bank database, passing card number and value
                //I gonna write this part only If I have time
                //Maybe change the boolean return to exception handling.
                return true;
            }

        }

        ProcessPayment paying = new ProcessPayment(value,crdtCrdNumber);
        if(paying.payValue())
            System.out.println("***Transaction Accepted:€"+value+"***");
        else
            System.out.println("Something Went Wrong!!!");

    }

    public List<Rent> getRent(){
        return rents;
    }

}
