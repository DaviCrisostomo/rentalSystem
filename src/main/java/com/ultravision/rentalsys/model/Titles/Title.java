package com.ultravision.rentalsys.model.Titles;

import com.ultravision.rentalsys.model.Items.Item;
import com.ultravision.rentalsys.model.Rent.Rent;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long titleId;

    @Column (columnDefinition="VARCHAR(35)")
    protected String title;
    @Column (columnDefinition="VARCHAR(50)")
    protected String genre;
    @Column (columnDefinition="VARCHAR(500)")
    protected String info;


    protected int releaseYear;
   // int totalRunTime;

    @OneToMany(mappedBy = "title",fetch = FetchType.EAGER)
    List<Item> items=new ArrayList<>();


    public String getTitleName() {

        return this.title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }

    public String getInfo() {

        return info;
    }

    public void setInfo(String description) {

        this.info = description;
    }


    public int getReleaseYear() {

        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {

        this.releaseYear = releaseYear;
    }

    public Long getIdNumber() {

        return titleId;
    }

    public void addItem(Item item){

        items.add(item);
    }

    @Override
    public abstract String toString();
}
