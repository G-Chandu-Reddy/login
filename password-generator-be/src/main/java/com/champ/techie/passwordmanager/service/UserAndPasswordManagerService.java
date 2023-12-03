package com.champ.techie.passwordmanager.service;

import com.champ.techie.passwordmanager.model.PasswordManager;
import com.champ.techie.passwordmanager.model.UserDetails;
import com.champ.techie.passwordmanager.repository.PasswordManagerRepository;
import com.champ.techie.passwordmanager.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAndPasswordManagerService {

    private final PasswordManagerRepository passwordManagerRepository;

    private final UserDetailsRepository userDetailsRepository;


    public String generatePassword() {
        int length = 8;
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{};:|\",<.>/?";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * charset.length());
            password.append(charset.charAt(randomIndex));
        }
        return password.toString();
    }

    public void createData(@Validated PasswordManager passwordManager) {
        passwordManagerRepository.save(passwordManager);
    }

    public List<PasswordManager> getDataByWebsite(String name) {
        return passwordManagerRepository.findByWebsite(name);
    }

    public PasswordManager getDataByUserName(String name) {
        Optional<PasswordManager> optionalData = passwordManagerRepository.findById(name);
        return optionalData.orElse(null);
    }

    public void registerUser(UserDetails userDetails) {
        UserDetails userData = UserDetails.builder()
                        .name(userDetails.getName())
                                .password(Objects.requireNonNull(encodePassword(userDetails.getPassword()))).build();
        userDetailsRepository.save(userData);
    }

    public boolean login(UserDetails userDetails) {
        Optional<UserDetails> result = userDetailsRepository.findById(userDetails.getName());
        if(result.isPresent()){
            if(userDetails.getPassword().equals(decodePassword(result.get().getPassword()))){
                return true;
            }
        }
        return false;
    }

    public static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decodePassword(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }

}
