package com.ultravision.rentalsys.repository;

import com.ultravision.rentalsys.model.Titles.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {


    Title findByTitleId(Long titleId);

    Set<Title> findByTitleContaining(String title);
    Title findByTitle(String title);

    Set<Title> findAll();
    @Query("FROM Audio")
    Set<Title> findAudio();
    @Query("FROM BoxSet")
    Set<Title> findBoxSet();
    @Query("FROM Movie")
    Set<Title> findMovie();
    @Query("FROM LiveConcert")
    Set<Title> findLiveConcert();

    Title save(Title title);


}
