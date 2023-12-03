package com.champ.techie.passwordmanager.controller;

import com.champ.techie.passwordmanager.model.PasswordManager;
import com.champ.techie.passwordmanager.model.UserDetails;
import com.champ.techie.passwordmanager.service.UserAndPasswordManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwordmanager")
@RequiredArgsConstructor
public class PasswordManagerController {

    private final UserAndPasswordManagerService userAndPasswordManagerService;


    @PostMapping("/createdata")
    @ResponseStatus(HttpStatus.CREATED)
    public void createData (@RequestBody PasswordManager passwordManager) {
        userAndPasswordManagerService.createData(passwordManager);
    }

    @PostMapping("/website/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PasswordManager> getDataByWebsite (@PathVariable String name){
        return userAndPasswordManagerService.getDataByWebsite(name);
    }

    @PostMapping("/username/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PasswordManager getDataByUserName (@PathVariable String name){
        return userAndPasswordManagerService.getDataByUserName(name);
    }


    @GetMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    public String generatePassword(){
        return userAndPasswordManagerService.generatePassword();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser (@RequestBody UserDetails userDetails) {
        userAndPasswordManagerService.registerUser(userDetails);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean loginUser(@RequestBody UserDetails userDetails){
        return userAndPasswordManagerService.login(userDetails);
    }

}
