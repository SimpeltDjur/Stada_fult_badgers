package com.example.stada_fult_badgers.controller;

import com.example.stada_fult_badgers.dto.BookingResponseDTO;
import com.example.stada_fult_badgers.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = {"http://localhost:3000"}, methods =
        {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/get/{id}")
    public List<BookingResponseDTO> getBookings(@PathVariable("id") int id) {
        return bookingService.getBookings(id);
    }

}
