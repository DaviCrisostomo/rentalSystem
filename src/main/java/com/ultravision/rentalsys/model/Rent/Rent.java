package com.ultravision.rentalsys.model.Rent;

import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.model.Items.Item;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Rent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;

    @Column(name="rental_value")
    private double rentalValuePaid;

    @Column(name="devolution_date")
    private LocalDate devolutionDate;


    @Column(name="rental_date", updatable = false)
    public LocalDate rentalDate = LocalDate.now();

    @Column(name="penalty_value")
    private double penaltyValuePaid;

    @Transient
    private boolean free = false;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer_id")
    MembershipCard card;

    @ManyToOne(fetch = FetchType.EAGER)
   // @MapsId("item_id")
    @JoinColumn(name = "item_id")
    Item item;



    public Rent(){}

    public Rent(MembershipCard card, Item item){

        this.card = card;
        this.item = item;

    }

    public Long getRentId(){

        return rentId;
    }
    public void setItem(Item item){

        this.item = item;
        this.item.setAvailable();

    }


    public Item getItem(){

        return item;
    }


   public void setDevolutionDate() {

       this.devolutionDate = LocalDate.now();
    }

    public void setRentalValuePaid(double rentalValuePaid) {
        this.rentalValuePaid = rentalValuePaid;
    }

    public double getRentalValuePaid() {

        return (getFree())?0.0:rentalValuePaid;
    }

    public void setCard(MembershipCard card){

            this.card = card;
    }

    public MembershipCard getCard() {
        return card;
    }

    public LocalDate getRentalDate(){

       return rentalDate;

    }
    public LocalDate getDevolutionDate(){

        return devolutionDate;

    }

    public double getPenaltyValuePaid() {
        return penaltyValuePaid;
    }

    public void setPenaltyValuePaid(double value) {
        this.penaltyValuePaid=value;
    }

    public String getItemTitleName() {

       return item.getTitle().getTitleName();

    }

    public void setFree(){

        this.free = !free;
    }

    public boolean getFree(){

        return free;
    }

}
