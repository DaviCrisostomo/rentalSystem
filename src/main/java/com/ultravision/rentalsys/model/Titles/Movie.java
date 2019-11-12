package com.ultravision.rentalsys.model.Titles;

import com.ultravision.rentalsys.model.Media.MedType;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
public class Movie extends Title {

    @Column(columnDefinition="VARCHAR(255)")
    private String starring;
    @Column(columnDefinition="VARCHAR(25)")
    private String studio;
    @Column(columnDefinition="VARCHAR(65)")
    private String director;
    @Column(columnDefinition="VARCHAR(65)")
    private String writer;

    @Enumerated(EnumType.STRING)
    private final MedType mediaType = MedType.DVD;


    public Movie(){}

    public Movie(String title, int releaseYear, String genre, String info, String starring, String studio, String director, String writer ){
        this.title=title;
        this.releaseYear=releaseYear;
        this.genre=genre;
        this.info=info;
        this.starring=starring;
        this.studio=studio;
        this.director=director;
        this.writer=writer;

    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public MedType getMediaType() {
        return mediaType;
    }

    @Override
    public String toString() {
        return "Title: "+title + " ("+releaseYear+") - ID:"+titleId+"\nGenre: "+genre+"\nStarrinng: "+starring+"\nDirector: "+director+"\nWriter: "+writer+"\nStudio: "+studio+
                "\n"+"Info: "+info+"\n";
    }
}
