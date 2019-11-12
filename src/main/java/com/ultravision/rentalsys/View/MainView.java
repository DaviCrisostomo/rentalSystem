package com.ultravision.rentalsys.View;


import com.ultravision.rentalsys.controller.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.util.Scanner;


public class MainView{


    public MainView(){


    }

    public static void showPanel(){

        System.out.println("*********************************");
        System.out.println("*********************************");
        System.out.println("*** HyperVision Rental System ***");
        System.out.println("*********************************");
        System.out.println("*********************************\n");
    }//

    public static void askingForCommand(){

        System.out.println("*********************************\n");
        System.out.println("Insert Command:");
    }

    public static void askingForItemId(){

        System.out.println("*********************************\n");
        System.out.println("Insert Item id:");
    }

    public static void showCommands(){

        System.out.println("Search Customers: sc  [customer name||customer id]");
        System.out.println("Search Titles:    st  [title name||title id]");
        System.out.println("Add Customer:     ac  [customer name],[customer address],[credit card number]");
        System.out.println("Add LiveConcert:  al  [title name],[genre],[info],[release year]");
        System.out.println("Add Movie:        am  [title name],[genre],[info],[release year],[starring],[studio],[director],[writer]");
        System.out.println("Add BoxSet:       ab  [title name],[genre],[info],[release year],[starring],[chanel]");
        System.out.println("Add Audio:        aa  [title name],[genre],[info],[release year]");
        System.out.println("Add Item:         ai  [title id],[number of items]");
        System.out.println("Update Card:      up  [customer id],[new Access Level(ML, VL, TL or PM)]");
        System.out.println("Rental Process:   rp  [customer id]");
    }

    public static void showRentCommands(){

        System.out.println("\n*********************************");
        System.out.println("To include an item in the basket or remove it from the basket, insert item id;");
        System.out.println("To conclude or cancel a rental process, insert 'commit';");
        System.out.println("To cancel a rental process, insert 'drop';");
        System.out.println("To use bonus points: bp [title id].");
        System.out.println("To take the bonus point back: tb [title id]\n");

    }



}
