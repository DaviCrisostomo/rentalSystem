package com.ultravision.rentalsys.controller;

import com.ultravision.rentalsys.Utilities.DatabaseFeeder;
import com.ultravision.rentalsys.Utilities.Input;
import com.ultravision.rentalsys.model.Customer.Customer;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;



@ComponentScan
@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;


    public void searchById(Long customerId){

        Customer customer = customerRepository.findByCustomerId(customerId);

        if(customer==null)
            System.out.println("Operation Failed!");
        else
            System.out.println(customer.toString());

    }

    public void searchCustomer(String data){

        if(data.matches("[a-zA-Z]+")||data.equals(""))
            searchByName(data);
        else if(Input.forLong(data))
            searchById(Long.parseLong(data));

    }

    public Customer getById(Long customerId){

         return customerRepository.findByCustomerId(customerId);

    }

    public void searchByName(String name){

        name=name.toLowerCase();

        List<Customer> allCustomers = customerRepository.findByNameContaining(name);

        if(allCustomers.isEmpty())
            System.out.println("Nothing Found!");
        else
        for (Customer customer:allCustomers)
            System.out.printf(customer.toString()+"\n");
    }

    public void checkingData(String data){

        String[] details = data.split(",");
        Customer customer = new Customer();
        if(details.length!=3)
            System.out.println("Operation failed!! Missing Data!");
        else if(!customer.setName(details[0].trim()))
            System.out.println("Incorrect value for field name");
        else if(details[2].trim().length()!=16||details[2].matches("[A-Z][a-z]*"))
            System.out.println("Incorrect value for field CreditCard");
        else {
            customer.setAddress(details[1].trim());
            MembershipCard card = new MembershipCard(Long.parseLong(details[2].trim()));
            addCustomer(customer, card);
        }


    }

    public void addCustomer(Customer customer, MembershipCard card){

        customer.setCard(card);

        card.setCustomer(customer);

        if(customerRepository.save(customer)!=null)
            System.out.println("Operation Successfully Completed");
        else
            System.out.println("Something went wrong");

    }

    public void showAllCustomers(){

        List<Customer> allCustomers =  customerRepository.findAll();

        if(allCustomers.isEmpty())
            System.out.printf("YOU'RE BROKEN!!!");
        else
       for (Customer customer:allCustomers)
            System.out.printf(customer.toString()+"\n");

    }

    public void updateCustomer(Customer inDb) {

        customerRepository.save(inDb);
    }

    public void feedFromFile(){

        try{

            for(Customer c : new DatabaseFeeder().feedingCustomers())
                customerRepository.save(c);

        }catch(IOException e){
            System.out.println(e.toString());
        }

    }

}
