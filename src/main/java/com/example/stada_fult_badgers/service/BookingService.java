package com.example.stada_fult_badgers.service;

import com.example.stada_fult_badgers.dto.BookingResponseDTO;
import com.example.stada_fult_badgers.dto.CreateBookingDTO;
import com.example.stada_fult_badgers.enteties.AppUser;
import com.example.stada_fult_badgers.enteties.Booking;
import com.example.stada_fult_badgers.repo.AppUserRepo;
import com.example.stada_fult_badgers.repo.BookingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;
    private final AppUserRepo appUserRepo;

    public BookingService(BookingRepo bookingRepo, AppUserRepo appUserRepo) {
        this.bookingRepo = bookingRepo;
        this.appUserRepo = appUserRepo;
    }

    public List<BookingResponseDTO> getBookings(int id) {
        return bookingRepo.findAll().stream()
                .filter(b -> b.getAppUser().getId() == id)
                .map(Booking::toBookingResponseDTO)
                .toList();
    }

    public void approveCleaning(int id) {
        Booking booking = bookingRepo.findById(id).orElseThrow();
        booking.setStatus("Godkänd");
        bookingRepo.save(booking);
    }

    public void cancelCleaning(int id) {
        Booking booking = bookingRepo.findById(id).orElseThrow();
        booking.setStatus("Avbokad");
        bookingRepo.save(booking);
    }

    public void createBookingDTO(CreateBookingDTO createBookingDTO) {
        String message = createBookingDTO.message();
        int appUserId = createBookingDTO.appuserId();
        String date = createBookingDTO.date();
        String time = createBookingDTO.time();

        AppUser appUser = appUserRepo.findById(appUserId).orElseThrow();
        Booking booking = new Booking(appUser, date, time);
        bookingRepo.save(booking);
    }
}
