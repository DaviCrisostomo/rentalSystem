package com.ultravision.rentalsys.repository;

import com.ultravision.rentalsys.model.Items.Item;
import com.ultravision.rentalsys.model.Titles.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> save(Title title);
    Item findByItemId(Long itemId);
    List<Item> findByTitle(Title title);
    int countByTitle(Title title);
    int countByTitleAndIsAvailable(Title title, boolean isAvailable);


}
