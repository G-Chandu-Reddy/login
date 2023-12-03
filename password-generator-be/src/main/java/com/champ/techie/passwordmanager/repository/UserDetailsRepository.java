package com.champ.techie.passwordmanager.repository;

import com.champ.techie.passwordmanager.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {
}
