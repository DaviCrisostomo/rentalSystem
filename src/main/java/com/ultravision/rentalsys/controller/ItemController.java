package com.ultravision.rentalsys.controller;

import com.ultravision.rentalsys.model.Items.Item;
import com.ultravision.rentalsys.model.Titles.Title;
import com.ultravision.rentalsys.repository.ItemRepository;
import com.ultravision.rentalsys.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@ComponentScan
@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    TitleController titleController;




    public void createItem(Title t){

        Item item = new Item();

        item.setTitle(t);

        t.addItem(item);

        itemRepository.save(item);

    }

    public void checkingData(String data){

        try {

            String[] details = data.split(",");
            Long id = Long.parseLong(details[0].trim());
            int times = (details.length==1||details[1].trim().equals(""))? 1: Integer.parseInt(details[1].trim());
            Title t = titleController.getById(id);

            if(t!=null){
                for(int i = times;i>0;i-- ) {
                    createItem(t);
                    System.out.println("New item for "+ t.getTitleName()+" created successfully");
                }
            }
            else
                System.out.println("Title not found");

        }catch(NumberFormatException a){
            System.out.println("Wrong value for value titleId");
        }
        catch(ArrayIndexOutOfBoundsException b){
            System.out.println("Operation failed!! Missing Data!");
        }


    }

    public void updateItem(Item item){

        itemRepository.save(item);

    }


    public Item getById(Long itemId){

        return itemRepository.findByItemId(itemId);

    }

    public void showItems(Title title){

        int countItem = itemRepository.countByTitle(title);
        int availables = itemRepository.countByTitleAndIsAvailable(title, true);

        System.out.println("Total: "+countItem+" Available: "+availables);
    }


}
