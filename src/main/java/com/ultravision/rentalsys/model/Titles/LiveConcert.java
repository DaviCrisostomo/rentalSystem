package com.ultravision.rentalsys.model.Titles;

import com.ultravision.rentalsys.model.Media.MedType;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class LiveConcert extends Music {

    private final MedType mediaType = MedType.BLUE_RAY;

    @Column
    @Formula(value="(SELECT count(*) from item i, live_concert l where i.title_id = l.title_id)")
    private int numberOfItems;

    public LiveConcert(){}

    public LiveConcert(String title, String artist, int releaseYear, String genre, String recordCompany, String content, String info){

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
