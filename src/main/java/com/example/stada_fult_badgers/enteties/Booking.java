package com.example.stada_fult_badgers.enteties;

import com.example.stada_fult_badgers.dto.BookingResponseDTO;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column
    private Boolean done;

    @Column
    private Boolean accepted;

    @ManyToOne
    //@JoinColumn()
    private AppUser appUser;



    public Booking() {
    }

    public Booking(AppUser appUser, String date, String time) {
        this.appUser = appUser;
        this.date = date;
        this.time = time;
        this.status = "Obekräftad";
        this.done = false;
        this.accepted = false;
    }



    public BookingResponseDTO toBookingResponseDTO() {
        return new BookingResponseDTO(id, appUser.getAppUserName(), status, date, time, done, accepted);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser customer) {
        this.appUser = customer;
    }
}