package com.echanneling.service;



import com.echanneling.dto.UserDTO;
import com.echanneling.entity.User;
import com.echanneling.util.CommonResponse;

import java.util.List;

public interface UserService {
    User save(UserDTO user);
    List<User> findAll();
    void delete(int id);
    User findOne(String username);
    User findById(int id);

    CommonResponse validateUserByNameAndPassword(UserDTO userDTO);
}
