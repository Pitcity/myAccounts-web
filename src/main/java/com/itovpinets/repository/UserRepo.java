package com.itovpinets.repository;

import com.itovpinets.entity.Deal;
import com.itovpinets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String>  {

}
