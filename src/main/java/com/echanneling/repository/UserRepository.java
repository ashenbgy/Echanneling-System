package com.echanneling.repository;


import com.echanneling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String userName);
    User findByUserId(int id);

    @Query("FROM User us WHERE us.username=?1 AND us.password=?2")
    User findByUsernameAndPassword(String username, String password);
}
