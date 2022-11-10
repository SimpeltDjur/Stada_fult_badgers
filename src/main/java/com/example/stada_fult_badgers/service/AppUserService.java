package com.example.stada_fult_badgers.service;

import com.example.stada_fult_badgers.dto.CreateUserDTO;
import com.example.stada_fult_badgers.enteties.AppUser;
import com.example.stada_fult_badgers.enteties.Role;
import com.example.stada_fult_badgers.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public void createNewUser(CreateUserDTO createUserDTO) {
        AppUser newUser = new AppUser(createUserDTO.appUserName(),
                createUserDTO.address(),
                passwordEncoder.encode(createUserDTO.password()),
                Set.of(Role.CUSTOMER));
        System.out.println("Hello AppUserService!");
        appUserRepo.save(newUser);
    }
}
