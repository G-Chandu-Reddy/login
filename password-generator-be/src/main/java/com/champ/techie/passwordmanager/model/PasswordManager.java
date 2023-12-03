package com.champ.techie.passwordmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordManager {
    @Id
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String website;
}
