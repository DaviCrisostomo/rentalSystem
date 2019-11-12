package com.ultravision.rentalsys.model.Items;

import com.ultravision.rentalsys.model.Rent.Rent;
import com.ultravision.rentalsys.model.Titles.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique=true)
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="title_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Title title;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean isAvailable = true;

    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    public List<Rent> rents = new ArrayList<>();


    public Item(){

    }


    public Long getItemId() {
        return itemId;
    }

    public void setTitle(Title t){
        this.title=t;
    }

    public Title getTitle() {

        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable() {

        this.isAvailable = !isAvailable;
    }

    public void rentInOut(Rent rent){

        if(this.rents.remove(rent)) {
            setAvailable();
            rent.setItem(null);
        }
        else{
            this.rents.add(rent);
            setAvailable();
        }
           //i need to keep the records

    }
}
