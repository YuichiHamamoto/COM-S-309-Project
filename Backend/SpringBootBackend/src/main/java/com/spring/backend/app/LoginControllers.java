package com.spring.backend.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "Login Info Controller")
@RestController
@RequestMapping("/api/login")
public class LoginControllers {
    @Autowired
    LoginInfoRepository loginRepo;

    @ApiOperation(value = "Get login information for specific user." , response = UserFiles.class)
    @GetMapping("/{username}")
    public Optional<LoginInfo> getUserFiles(@PathVariable String username) {
        return loginRepo.findById(username);
    }

    @ApiOperation(value = "Add new user to system" , response = UserFiles.class)
    @PostMapping("/addUser")
    public LoginInfo addUserPass(@RequestBody LoginInfo info) {
        loginRepo.save(info);
        return info;
    }
}
