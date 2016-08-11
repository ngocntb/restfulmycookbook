package com.jersey.persistance;

import com.jersey.representations.Direction;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Audi on 10/29/2015.
 */
public interface DirectionDao extends JpaRepository<Direction, Long> {

}
