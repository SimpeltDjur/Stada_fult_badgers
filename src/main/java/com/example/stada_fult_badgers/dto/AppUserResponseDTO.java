package com.example.stada_fult_badgers.dto;

import java.util.List;

public record AppUserResponseDTO(int id, String appUserName, String adress, List<BookingResponseDTO> bookingList) {
}
