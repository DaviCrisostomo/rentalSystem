package com.ultravision.rentalsys.repository;

import com.ultravision.rentalsys.model.Customer.Customer;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MembershipCardRepository extends CrudRepository<MembershipCard, Long> {

    MembershipCard findByCustomerId(Long customerId);
    MembershipCard save(MembershipCard card);

}
