package com.ultravision.rentalsys.model.Titles;

//Live Concert and Music represented by the same class
//they don't differ that much from each other in behaviors and attributes

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Music extends Title {

    @Column(columnDefinition="VARCHAR(35)")
    protected String artist;
    @Column (columnDefinition="VARCHAR(25)")
    protected String recordCompany;
    @Column (columnDefinition="VARCHAR(600)")
    protected String content;



    public String getArtist() { return artist; }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordCompany() {
        return recordCompany;
    }

    public void setRecordCompany(String recordCompany) { this.recordCompany = recordCompany;}

    public void setContent (String content){this.content=content;}

    public String getContent (){return content;}

    @Override
    public String toString() {

        return "Title: "+title + " ("+releaseYear+") - ID:"+titleId+"\nArtist: "+artist+"\nRecorded By: "+recordCompany+
                "\n"+"Info: "+info+"\nTracks: "+content+"\n";
    }


}
