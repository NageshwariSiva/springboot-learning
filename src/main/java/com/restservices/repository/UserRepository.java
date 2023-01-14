package com.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservices.entities.User;

//Data Flow:
//Entity -> Repository -> Service -> Controller
@Repository
public interface UserRepository extends JpaRepository<User, Long> {  // this interface will have entity and primarykey as a parameter
	
	User findByUsername(String username);
}
