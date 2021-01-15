package com.project.travel.bot.Repository;


import com.project.travel.bot.model.Travel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Long> {

    List<Travel> findByCity(String city);
    Travel findById(long id);

}
