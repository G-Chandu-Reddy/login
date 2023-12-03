package com.champ.techie.passwordmanager.repository;

import com.champ.techie.passwordmanager.model.PasswordManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PasswordManagerRepository extends CrudRepository<PasswordManager, String> {
    List<PasswordManager> findByWebsite(String name);
}
