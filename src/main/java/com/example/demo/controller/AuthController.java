// package com.example.demo.controller;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.LoginRequest;
// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Used mainly for verification if needed, but here we just simulate.
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication", description = "Authentication APIs")
// public class AuthController {

//     private final UserService userService;
//     private final PasswordEncoder passwordEncoder;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//         this.passwordEncoder = new BCryptPasswordEncoder();
//     }

//     @PostMapping("/login")
//     @Operation(summary = "Login user")
//     public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
//         User user = userService.findByEmail(loginRequest.getEmail());

//         if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//             // Generate a dummy token since security layer is disabled
//             String token = "dummy-jwt-token-for-" + user.getEmail();
//             return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
//         } else {
//             throw new IllegalArgumentException("Invalid credentials");
//         }
//     }

//     @PostMapping("/register")
//     @Operation(summary = "Register user")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return ResponseEntity.ok(userService.registerUser(user));
//     }
// }


package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
}