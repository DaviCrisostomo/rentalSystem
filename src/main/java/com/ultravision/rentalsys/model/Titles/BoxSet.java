package com.ultravision.rentalsys.model.Titles;

import com.ultravision.rentalsys.model.Media.MedType;


import javax.persistence.*;

@Entity
public class BoxSet extends Title {

    @Column(columnDefinition="VARCHAR(255)")
    private String starring;
    @Column(columnDefinition="VARCHAR(25)")
    private String channel;


    @Enumerated(EnumType.STRING)
    private final MedType mediaType = MedType.DVD;

    public BoxSet(){}

    public BoxSet(String title, int releaseYear, String genre, String info, String starring, String channel){
        this.title=title;
        this.releaseYear=releaseYear;
        this.genre=genre;
        this.info=info;
        this.starring=starring;
        this.channel=channel;
    }



    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public MedType getMediaType() {
        return mediaType;
    }

    @Override
    public String toString() {
        return "Title: "+title + " ("+releaseYear+") - ID:"+titleId+"\nGenre: "+genre+"\nStarrinng: "+starring+"\nChannel: "+channel+
                "\nInfo: "+info+"\n";
    }

}
