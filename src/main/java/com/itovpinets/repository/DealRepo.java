package com.itovpinets.repository;

import com.itovpinets.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Repository
public interface DealRepo extends JpaRepository<Deal, Long> {
}
