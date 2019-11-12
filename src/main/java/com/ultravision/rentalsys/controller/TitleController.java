package com.ultravision.rentalsys.controller;

import com.ultravision.rentalsys.Utilities.DatabaseFeeder;
import com.ultravision.rentalsys.Utilities.Input;
import com.ultravision.rentalsys.model.Titles.Title;
import com.ultravision.rentalsys.repository.ItemRepository;
import com.ultravision.rentalsys.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.ultravision.rentalsys.Utilities.DatabaseFeeder.feedingAudio;
import static com.ultravision.rentalsys.Utilities.DatabaseFeeder.feedingBoxSet;

@ComponentScan
@Controller
public class TitleController {

    @Autowired
    TitleRepository titleRepository;
    @Autowired
    ItemRepository itemRepository;

    public Title getById(Long id){

        return titleRepository.findByTitleId(id);
    }

    public Set<Title> getAll(){

        return titleRepository.findAll();
    }

    public void searchTitle(String data){

        if(data.matches("[a-zA-Z]+")||data.equals(""))
            searchByTitle(data);
        else if(Input.forLong(data))
            searchById(Long.parseLong(data));

    }

    public void searchByTitle(String title){

        Iterable<Title> titlesList = titleRepository.findByTitleContaining(title);
        int count =0;

        for (Title t : titlesList) {
            count++;
            System.out.println(t.toString());
            System.out.print("Total items:"+itemRepository.countByTitle(t));
            System.out.println("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
        }
        System.out.println("Number of Titles Found:"+" "+count);

    }

    public void searchById(Long id){

        Title t = titleRepository.findByTitleId(id);

            System.out.println(t.toString());
            System.out.print("Total items:"+itemRepository.countByTitle(t));
            System.out.println("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");

    }

    public void addNewTitle(Title title) {

        Title newtitle = titleRepository.save(title);
        if(title==null)
            System.out.println("Operation Failed!");
        else
            newtitle.toString();

    }

    public void showAll(){

        Iterable<Title> titlesList = titleRepository.findAll();
        int count =0;

            for (Title t : titlesList) {
                count++;
                System.out.println(t.toString());
                System.out.print("Total items:"+itemRepository.countByTitle(t));
                System.out.println("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
            }
            System.out.println("Number of Titles Found:"+" "+count);
        }

    public void showAudio(){

        Iterable<Title> audioList = titleRepository.findAudio();
        int count =0;

        for (Title t : audioList) {
            count++;
            System.out.println(t.toString());
            System.out.println("Total items:"+itemRepository.countByTitle(t));
            System.out.print("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
        }
        System.out.println("Number of Titles Found:"+" "+count);
    }

    public void showLiveConcert(){

        Iterable<Title> liveConcertList = titleRepository.findLiveConcert();
        int count =0;

        for (Title t : liveConcertList) {
            count++;
            System.out.println(t.toString());
            System.out.println("Total items:"+itemRepository.countByTitle(t));
            System.out.print("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
        }
        System.out.println("Number of Titles Found:"+" "+count);
    }

    public void showMovie(){

        Iterable<Title> movieList = titleRepository.findMovie();
        int count =0;

        for (Title t : movieList) {
            count++;
            System.out.println(t.toString());
            System.out.println("Total items:"+itemRepository.countByTitle(t));
            System.out.print("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
        }
        System.out.println("Number of Titles Found:"+" "+count);
    }

    public void showBoxSet(){

        Iterable<Title> boxSetList = titleRepository.findBoxSet();
        int count =0;

        for (Title t : boxSetList) {
            count++;
            System.out.println(t.toString());
            System.out.println("Total items:"+itemRepository.countByTitle(t));
            System.out.print("\tAvailable: "+itemRepository.countByTitleAndIsAvailable(t, true)+"\n");
        }
        System.out.println("Number of Titles Found:"+" "+count);
    }

    public void feedAllFromFile(){
        DatabaseFeeder feeder = new DatabaseFeeder();
        ArrayList<Title> titles = new ArrayList<>();
        try{
            titles.addAll(feeder.feedingAudio());
            titles.addAll(feeder.feedingBoxSet());
            titles.addAll(feeder.feedingLiveConcert());
            titles.addAll(feeder.feedingMovie());
            }catch(IOException e){
            System.out.println(e.toString());
            }
        for(Title t: titles)
            addNewTitle(t);
    }
}
