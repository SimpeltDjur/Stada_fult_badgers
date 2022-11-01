package com.example.stada_fult_badgers;

import com.example.stada_fult_badgers.enteties.AppUser;
import com.example.stada_fult_badgers.enteties.Booking;
import com.example.stada_fult_badgers.enteties.Role;
import com.example.stada_fult_badgers.repo.AppUserRepo;
import com.example.stada_fult_badgers.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class StadaFultBadgersApplication implements CommandLineRunner {

	@Autowired
	AppUserRepo appUserRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BookingRepo bookingRepo;

	public static void main(String[] args) {
		SpringApplication.run(StadaFultBadgersApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		try {
			AppUser appUser1 = new AppUser("Agda", "Stckholm", passwordEncoder.encode("pass"), Set.of(Role.CUSTOMER) );
			appUserRepo.saveAll(List.of(
					appUser1,
					new AppUser("Bengt", "Toreboda", passwordEncoder.encode("1234"), Set.of(Role.CLEANER))
					));
			bookingRepo.saveAll(List.of(
					new Booking(appUser1, "2022/11/15", "12:00")
			));


		} catch (Exception e) {
			System.out.println("Det bidde fel");
		}
	}
}
