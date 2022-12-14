package com.example.stada_fult_badgers.service;

import com.example.stada_fult_badgers.dto.WhoAmIDTO;
import com.example.stada_fult_badgers.enteties.AppUser;
import com.example.stada_fult_badgers.repo.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JwtUtil {

    private final AppUserRepo appUserRepo;

    private final int EXPIRATION_MILLIS = 1000 * 60 * 60 * 24;
    public static KeyPair keyPair = getRSAKeys();

    public JwtUtil(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }


    public String generateToken(AppUser appUser){

        return Jwts.builder()
                .setSubject(appUser.getAppUserName())
                .setId(String.valueOf(appUser.getId()))
                .claim("Role", appUser.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(keyPair.getPrivate())
                .compact();

    }


    public WhoAmIDTO parseToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();

        AppUser appUser =  appUserRepo.findById(Integer.parseInt(claims.getId())).orElseThrow();

        return new WhoAmIDTO(claims.getSubject(), claims.getId(), appUser.isCleaner(),  token);
    }


    public  Claims parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private static KeyPair getRSAKeys() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}
