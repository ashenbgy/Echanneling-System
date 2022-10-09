package com.echanneling.controller.rest;

import com.echanneling.dto.UserDTO;
import com.echanneling.service.UserService;
import com.echanneling.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/validateUser")
    public CommonResponse validateUserByNameAndPassword(@RequestBody UserDTO userDTO) {
        return userService.validateUserByNameAndPassword(userDTO);
    }
}
