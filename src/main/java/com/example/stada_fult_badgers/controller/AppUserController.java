package com.example.stada_fult_badgers.controller;

import com.example.stada_fult_badgers.dto.AppUserResponseDTO;
import com.example.stada_fult_badgers.dto.CreateUserDTO;
import com.example.stada_fult_badgers.service.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000"}, methods =
        {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/new")
    public void createNewUser(@RequestBody CreateUserDTO createUserDTO) {
        System.out.println("Hello controller 1.");
        appUserService.createNewUser(createUserDTO);
        System.out.println("Hello controller 2.");
    }

}
