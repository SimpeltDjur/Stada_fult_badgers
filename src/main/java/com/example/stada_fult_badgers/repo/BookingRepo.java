package com.example.stada_fult_badgers.repo;

import com.example.stada_fult_badgers.enteties.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
