package com.example.stada_fult_badgers.repo;

import com.example.stada_fult_badgers.enteties.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUsersByAppUserNameIgnoreCase(String appUserName);
}
