package com.ultravision.rentalsys.controller;

import com.ultravision.rentalsys.Utilities.Input;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.model.Items.Item;
import com.ultravision.rentalsys.model.Rent.Rent;
import com.ultravision.rentalsys.model.Titles.Title;
import com.ultravision.rentalsys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;



@ComponentScan
@Controller
public class RentController {
    /*
    * As renting items requires the item to be rented and the customer that rents
    * RentController class has to deal with MembershipCard and Item classes
    * For those other two classes a very specific role was determined
    * */

    @Autowired
    RentRepository rentRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MembershipCardRepository cardRepository;
/*
As we can not use the new operator for controllers
we have to clean up everything after each transaction
 */

    private final double regularRentValue = 2.0;
   /*As a good capitalist that I'm, I'm allowing the user to rent a title
     from another subscription plan by paying a charge
    */
    private final double extraCharge = 3.0;
 //penalties for late devolution because we love punish
    private final double dailyPenalty = 1.0;
//the value to be paid at the end of the transaction
    private double totalToPay;

    MembershipCard card;

    private ArrayList<Rent> itemsToRent = new ArrayList<>();

    public RentController(){


    }

    public void rentalProcess(Item item){

         //doing by this way helps to make the process easier by the user point of view.
         //pass the codebar reader and the system knows what is intended to be done
           if(item.isAvailable())//if the item is marked as available
               renting(item);   //the intention is rent the item
           else if(!alreadyIncluded(item))//True means the item was included but the customer
               returning(item);  //doesn't want to rent the item anymore. False is to return the item to the shop
    }

    public void settingValue(Rent rent, Item item){

        Title title = item.getTitle(); //getting a Title object by the item
        //checking if the type of Title is included in the the membership plan.
        if(card.getAccLevel().canRent(title)) {//if it is, the customer has to pay the standard value
            rent.setRentalValuePaid(regularRentValue);
            totalToPay+=regularRentValue;
        }
        else {//if is not, the customer pays extra
            rent.setRentalValuePaid(extraCharge);
            totalToPay+=extraCharge;
        }
    }

    public void checkingForPenalties(Rent rentEnding){
/*
Is the item being returned late? If yes, let make profit from it
 */
        double extraDays = rentEnding.getDevolutionDate().compareTo(rentEnding.getRentalDate());

        if(extraDays>3) {
            totalToPay += dailyPenalty*(extraDays-3);//3 is acceptable, after that, punishment
            rentEnding.setRentalValuePaid(dailyPenalty);
        }
    }

    public void renting(Item item){

        Rent rent = new Rent(card,item);
        //here we are checking how many items the customer
        //is renting and also if there are previous rents
        //registered with the customer
        if(card.getRentedItems()+itemsToRent.size()>=4)
            System.out.println("***MAX OF 4 ITEMS***");

        else //if everything is ok, the customer allowed to rent an item
            includingRenting(rent,item);

    }

    private void includingRenting(Rent rent, Item item){

        settingValue(rent, item);//setting the renting price
        item.setAvailable();//set item availability to false
        itemRepository.save(item);//updating the object item
        itemsToRent.add(rent);//insert the rent object in the list of rents
        System.out.println(toString());//printing the customer's selection
    }

    public void pointsInOut(String input){
     /*
     leading the user to the right place only if the user
     was gentle
      */
        String command = String.valueOf(input.subSequence(0, 2));
        String data = String.valueOf(input.subSequence(2, input.length())).trim();

        if(command.equals("bp")&&Input.forInt(data))
            usingPoints(Integer.parseInt(data));

        else if(command.equals("tb")&&Input.forInt(data))
            returningPoints(Integer.parseInt(data));

        else
            System.out.println("Command followed by wrong value");

    }

    private void usingPoints(int i){
        /*checking if the user is not trying to mess the program
          inserting the command when the list is empty
          checking if the customer has 100 or more points
          changing the rent's attribute free to true
          deducing the value of the product from totalTopay
          returning the points to the card
         */
        if(i<itemsToRent.size()&&card.usePoints()) {
            totalToPay-=itemsToRent.get(i - 1).getRentalValuePaid();//it has to come before
            itemsToRent.get(i - 1).setFree();
        }
          else
            System.out.println("***No points enough or the value inserted is wrong***");
    }

    private void returningPoints(int i){
        /*checking if the user is not trying to mess the program
          inserting the command when the list is empty or
          asking points back from an item that is not set as free
          changing the rent's attribute free to false
          adding back the value of the product
          returning the points to the card
         */
        if(i<itemsToRent.size()&&itemsToRent.get(i-1).getFree()) {
            itemsToRent.get(i-1).setFree();
            totalToPay+=itemsToRent.get(i - 1).getRentalValuePaid();
            card.addPoints();
        }
        else
            System.out.println("\nThe item selected is not set as free or the value inserted is wrong\n");
    }

    public void confirmingRent(){
//getting the money before update the database
        if(totalToPay>0.0)
            card.payment(totalToPay);

        closingRentalProcess();
    }

    private void closingRentalProcess(){

        // if(!itemsToRent.isEmpty()) {
        // a java.util.ConcurrentModificationException is returned when I try to walk through
        //the arraylist and I cant study it now
        Object[] rents = itemsToRent.toArray();
        // }
        for(int i = 0;i<rents.length;i++) {
            Rent rent = (Rent)rents[i];
            if(!rent.getFree()){//never  give bonus points for free rents
                card.addPoints();
            }
            card.addRent();
            savingElements(card,rent.getItem(), rent);//updating all objects necessary to the rental process
            itemsToRent.clear();
            totalToPay=0;
        }

    }

    public void returning(Item itemReturning){

      Long rentId = itemReturning.rents.get(itemReturning.rents.size()-1).getRentId();
      Rent closingRent = rentRepository.findByRentId(rentId);
      //an item can be returned by another customer. so we have to find the card that rented the item
      MembershipCard cardRenting = closingRent.getCard();
        closingRent.setDevolutionDate();
        checkingForPenalties(closingRent);
        addOrRemove(itemReturning, closingRent);
        cardRenting.returnRent();
        savingElements(cardRenting,itemReturning, closingRent);
        System.out.println("Item Retuned\n");

    }

    public void savingElements(MembershipCard card, Item item, Rent rent){
//updating objects
        itemRepository.save(item);
        cardRepository.save(card);
        rentRepository.save(rent);

    }

    public void addOrRemove(Item item, Rent rent){
        item.rentInOut(rent);//it was necessary before but as my experience with spring boot so far
        card.rentInOut(rent);//has been very dramatic I don't want to remove it and get some exception
        //yes, I'm a chicken
    }

    public void setCard(MembershipCard card){
        this.card=card;

    }

    public MembershipCard getCard(){
//just in case
        return card;
    }

    private boolean alreadyIncluded(Item item){
  //i have to compare by id
        for(Rent rent: itemsToRent)
            if(rent.getItem().getItemId()==item.getItemId()) {
                removingFromSelection(rent,item);
                return true;
            }
        return false;

    }

    private void removingFromSelection(Rent rent, Item item){
        /*
        Removing item from the selection and reconfiguring everything
         */
        totalToPay -= rent.getRentalValuePaid();
        item.setAvailable();
        itemRepository.save(item);
        itemsToRent.remove(rent);
        System.out.println("\n***Item removed***\n");
    }

    public void cancelRent(){
        //if the customer decides to cancel the rent, we have to cancel all changes
        //made on the objects and database
        for(Rent rent: itemsToRent){

            if(rent.getFree())//if points were used
            rent.getItem().setAvailable();//object item is available again
            itemRepository.save(rent.getItem());//and saved as available
        }

        itemsToRent.clear();//cleaning the list.
        totalToPay=0;

    }

    public int getItemsToRentSize(){
        return itemsToRent.size();
    }

    @Override
    public String toString(){

        StringBuilder rentList = new StringBuilder();
        int count = 0;

        if(itemsToRent.isEmpty())
        return "***Add Items to The List.***";
        else {
           for(Rent rent: itemsToRent) {
                count+=1;

               rentList.append(count+". "+rent.getItem().getTitle().getTitleName()+" €"
                        +rent.getRentalValuePaid()+"\n");
            }
        }
        return rentList.append("Total: €"+totalToPay+"\n").toString();
    }

}
