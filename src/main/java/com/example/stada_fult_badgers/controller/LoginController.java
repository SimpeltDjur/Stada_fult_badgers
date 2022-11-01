package com.example.stada_fult_badgers.controller;

import com.example.stada_fult_badgers.dto.LoginRequestDTO;
import com.example.stada_fult_badgers.dto.WhoAmIDTO;
import com.example.stada_fult_badgers.service.LoginService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET})
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){

        return loginService.login(loginRequestDTO.appUserName(), loginRequestDTO.password());
    }

    @GetMapping("/whoami")
    public WhoAmIDTO whoAmI(@RequestParam String token) {return loginService.whoami(token);}
}





// JWT anteckningar


// @Secured("ROLE_ADMIN") //Specificerar vilken roll som ska komma åt den.



// @PreAuthorize("@authService.idMatchesUses(#id)")  // kan läggas på specifik metod/endpoint

//@Service
//public class AuthService {
//
//    public static boolean idMatchesUser(int id){
//        AppUser appUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//        return appUser.getId() == id;
//    }
//}