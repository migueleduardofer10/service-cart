package com.example.servicecart.client;

import com.example.servicecart.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-user", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/users/admin")
    @ResponseBody
    public String forAdmin();

    @GetMapping("/users/user")
    @ResponseBody
    public String forUser();

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code);

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username);
}
