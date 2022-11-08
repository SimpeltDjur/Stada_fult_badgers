package com.example.stada_fult_badgers.dto;

public record BookingResponseDTO(
        int id,
        String appUserName,
        String status,
        String date,
        String time,
        Boolean accepted) { }
