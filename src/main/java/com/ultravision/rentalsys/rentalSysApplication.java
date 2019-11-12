package com.ultravision.rentalsys;

import com.ultravision.rentalsys.Utilities.Input;
import com.ultravision.rentalsys.View.MainView;
import com.ultravision.rentalsys.controller.*;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.model.Items.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class rentalSysApplication {

    private static Logger LOG = LoggerFactory.getLogger(rentalSysApplication.class);

    @Autowired
    CustomerController customerController;
    @Autowired
    MembershipCardController cardController;
    @Autowired
    ItemController itemController;
    @Autowired
    RentController rentController;
    @Autowired
    TitleController titleController;
    Scanner reader = new Scanner(System.in);


    @Bean
    public CommandLineRunner readingFromUser() {
        return (args) -> {

            MainView.showCommands();

            do {
                MainView.askingForCommand();
                gettingCommand(reader.nextLine() + "   ");

            } while (1 == 1);

        };
    }

    public void gettingCommand(String input) {

        String command = String.valueOf(input.subSequence(0, 3));
        String data = String.valueOf(input.subSequence(3, input.length())).trim();

        if (command.equals("sc "))
            customerController.searchCustomer(data);

        else if (command.equals("ac "))
            customerController.checkingData(data);

        else if (command.equals("st "))
            titleController.searchTitle(data);

        else if (command.equals("up "))
            cardController.checkingData(data);

        else if (command.equals("ai "))
            itemController.checkingData(data);

        else if (command.equals("rp ") && Input.forLong(data))
            findingMemberCard(data);

        else if (command.equals("ls "))
            MainView.showCommands();
        else if (command.equals("ex "))
            System.exit(0);
        else
            System.out.println("Command not found or followed by wrong value!\nInsert ls to get instructions.");

    }

    public void findingMemberCard(String data) {

        MembershipCard card = cardController.getById(Long.parseLong(data));

        if (card != null)
            gettingRentCommands(card);
        else
            System.out.println("\n****Membership Card not found****\n");
    }

    public void gettingRentCommands(MembershipCard card) {
        MainView.showRentCommands();
        rentController.setCard(card);
        String input = "";
        do {

            MainView.askingForItemId();
            input = reader.nextLine();
            if (Input.forLong(input)) {
                Item item = itemController.getById(Long.parseLong(input));
                if (item != null)
                    rentController.rentalProcess(item);
                else
                    System.out.println("Item not found\n");
            } else if (input.subSequence(0, 2).equals("bp") || input.subSequence(0, 2).equals("tb"))
                rentController.pointsInOut(input + "   ");

            else if (input.equals("prnt"))
                System.out.println(rentController.toString() + "\n");

            else if (input.equals("commit"))
                break;

            else if (input.equals("drop"))
                break;

            else if (input.subSequence(0, 2).equals("ls"))
                MainView.showRentCommands();

            else
                System.out.println("Command not found. Insert ls to get instructions.");

        } while (1 == 1);

        if (input.equals("commit") && rentController.getItemsToRentSize() != 0)
            rentController.confirmingRent();


        else if (input.equals("drop"))
            rentController.cancelRent();

    }

    {//initialization block.
        MainView.showPanel();
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(rentalSysApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

}
