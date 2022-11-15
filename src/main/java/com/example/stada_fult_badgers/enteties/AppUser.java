package com.example.stada_fult_badgers.enteties;


import com.example.stada_fult_badgers.dto.AppUserResponseDTO;
import com.example.stada_fult_badgers.dto.BookingResponseDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String appUserName;

    @Column()
    private String adress;

    @Column
    private String password;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Booking> bookings = new java.util.ArrayList<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column
    Set<Role> roles;



    public AppUser() {
    }

    public AppUser(String appUserName, String adress, String password, Set<Role> roles) {
        this.appUserName = appUserName;
        this.adress = adress;
        this.password = password;
        this.roles = roles;

    }



    public boolean isCleaner(){
        return roles.contains(Role.CLEANER);
    }

    public AppUserResponseDTO toAppUserResponseDTO() {
        List<BookingResponseDTO> bookingResponseDTOList =
                bookings.stream().map(c -> c.toBookingResponseDTO()).toList();
        return new AppUserResponseDTO(id, appUserName, adress, bookingResponseDTOList);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppUserName() {
        return appUserName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return appUserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
