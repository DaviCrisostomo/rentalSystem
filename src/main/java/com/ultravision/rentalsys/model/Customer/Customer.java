package com.ultravision.rentalsys.model.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
//needs to implement serializable if we want to make it travels across networks
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column (updatable = false, nullable = false, unique=true)
    private Long customerId;

    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank(message = "Address may not be blank")
    private String address;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private MembershipCard card;


    public Customer(){}

    public Customer(String name, String address){
        this.name = name;
        this.address = address;
    }

    public void setCard(MembershipCard card){

        this.card = card;
    }

    public MembershipCard getCard() {
        return card;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {

        if(name.matches("^[a-zA-Z\\s]*$")) {
            this.name = name;
            return true;
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){

        return ("customerID: "+customerId+"| Name: "+name+ ("| Address:"+address+"\n")+
                "Reg.Level: "+card.getAccLevel()+" | "+"Points: "+card.getPoints()+" | "
                +"Rents: "+card.getRentsSize());

    }

}




