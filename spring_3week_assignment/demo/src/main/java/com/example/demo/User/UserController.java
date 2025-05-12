package com.example.demo.User;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User postMethod(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getMethod(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping()
    public User putMethod(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteMethod(@PathVariable Long id) {
        userService.deleteUser(id);
        return "유저 삭제 완료";
    }
}
