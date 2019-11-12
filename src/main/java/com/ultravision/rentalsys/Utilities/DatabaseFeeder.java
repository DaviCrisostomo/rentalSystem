package com.ultravision.rentalsys.Utilities;

import com.ultravision.rentalsys.model.Customer.Customer;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.model.Titles.Audio;
import com.ultravision.rentalsys.model.Titles.BoxSet;
import com.ultravision.rentalsys.model.Titles.LiveConcert;
import com.ultravision.rentalsys.model.Titles.Movie;
import com.ultravision.rentalsys.repository.CustomerRepository;
import com.ultravision.rentalsys.repository.TitleRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseFeeder {


    public static ArrayList<Customer> feedingCustomers() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("./customer.rss"));
        String line;

        ArrayList<Customer> customers = new ArrayList<>();
        while(in.ready()){
            line=in.readLine();
            String[] breakingLine = line.split("-");
            Customer customer = new Customer(breakingLine[0],breakingLine[1]);
            MembershipCard card =  new MembershipCard(Long.parseLong(breakingLine[2]));
            customer.setCard(card);
            card.setCustomer(customer);
            customers.add(customer);
        }
return customers;
    }

    public static ArrayList<Audio> feedingAudio() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("./audio.rss"));
        String line;

        ArrayList<Audio> audioList = new ArrayList<>();
        while(in.ready()){

            line=in.readLine();
            String[] breakingLine = line.split("-");
            Audio audio = new Audio(breakingLine[0],breakingLine[1],Integer.parseInt(breakingLine[2]),breakingLine[3],
                    breakingLine[4],breakingLine[5],breakingLine[6]);

           audioList.add(audio);

        }
        return audioList;
}

    public static ArrayList<BoxSet> feedingBoxSet() throws IOException {

        ArrayList<BoxSet> boxList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("./boxSet.rss"));
        String line;

        while (in.ready()) {

            line = in.readLine();
            String[] breakingLine = line.split("-");
            BoxSet box = new BoxSet(breakingLine[0], Integer.parseInt(breakingLine[1]), breakingLine[2], breakingLine[3],
                    breakingLine[4], breakingLine[5]);


            boxList.add(box);

        }
return boxList;
    }

    public static ArrayList<LiveConcert> feedingLiveConcert() throws IOException {

        ArrayList<LiveConcert> liveList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("./liveConcert.rss"));
        String line;

        while(in.ready()){

            line=in.readLine();
            String[] breakingLine = line.split("-");
            LiveConcert live = new LiveConcert(breakingLine[0],breakingLine[1],Integer.parseInt(breakingLine[2]),breakingLine[3],
                    breakingLine[4],breakingLine[5],breakingLine[6]);

            liveList.add(live);

        }
   return liveList;
    }

    public static ArrayList<Movie> feedingMovie() throws IOException {

        ArrayList<Movie> movieList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("./movie.rss"));
        String line;

        while(in.ready()){

            line=in.readLine();
            String[] breakingLine = line.split("-");
            Movie movie = new Movie(breakingLine[0],Integer.parseInt(breakingLine[1]),breakingLine[2],breakingLine[3],
                    breakingLine[4],breakingLine[5],breakingLine[6], breakingLine[7]);

            movieList.add(movie);

        }
return movieList;
    }

}