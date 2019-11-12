package com.ultravision.rentalsys.model.Titles;


import com.ultravision.rentalsys.model.Media.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
public class Audio extends Music{

    @Enumerated(EnumType.STRING)
    private final MedType mediaType = MedType.CD;

    @Column
    @Formula(value="(SELECT count(*) from item i, audio a where i.title_id = a.title_id)")
    private int numberOfItems;


    public Audio(){

    }

    public Audio(String title, String artist, int releaseYear, String genre, String recordCompany, String content, String info){

        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.recordCompany = recordCompany;
        this.content = content;
        this.info = info;

    }

    public MedType getMediaType() {
        return mediaType;
    }


}
