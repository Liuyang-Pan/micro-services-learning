package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/18 11:57
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable String id, @RequestHeader(value = "Customize", required = false) String custom) {
        log.info("Customize:{}", custom);
        return userService.getById(id);
    }
}
