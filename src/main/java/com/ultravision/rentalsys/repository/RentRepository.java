package com.ultravision.rentalsys.repository;

import com.ultravision.rentalsys.model.Rent.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    List<Rent> findAll();
  // @Query("FROM Rent WHERE id.itemId = ?1 AND id.rentalDate<now() AND devolutionDate is NULL")

    Rent save(Rent rent);


    Rent findByRentId(Long rentId);
}
