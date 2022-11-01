package com.example.stada_fult_badgers.service;

import com.example.stada_fult_badgers.dto.BookingResponseDTO;
import com.example.stada_fult_badgers.enteties.Booking;
import com.example.stada_fult_badgers.repo.BookingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;

    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public List<BookingResponseDTO> getBookings(int id) {
        return bookingRepo.findAll().stream()
                .filter(b -> b.getAppUser().getId() == id)
                .map(Booking::toBookingResponseDTO)
                .toList();
    }
}
