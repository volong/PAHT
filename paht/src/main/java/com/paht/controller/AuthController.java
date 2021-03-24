package com.paht.controller;

import com.paht.common.JwtUtils;
import com.paht.dto.JwtResponse;
import com.paht.dto.LoginRequest;
import com.paht.dto.MessageResponse;
import com.paht.dto.SignupRequest;
import com.paht.model.ERole;
import com.paht.model.Role;
import com.paht.model.User;
import com.paht.repository.RoleRepository;
import com.paht.repository.UserRepository;
import com.paht.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin/admin")
    public ResponseEntity<?> authenticateAdmin(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> role = new ArrayList<>();
        role.add("ROLE_ADMIN");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        if (roles.get(0) != role.get(0)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
        } else {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getFullname(),
                    userDetails.getAvatar(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getIs_enable(),
                    roles));
        }
    }

    @PostMapping("/signin/dep")
    public ResponseEntity<?> authenticateDep(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> role = new ArrayList<>();
        role.add("ROLE_DEP");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        if (roles.get(0) != role.get(0)) {
            return null;
        } else {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getFullname(),
                    userDetails.getAvatar(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getIs_enable(),
                    roles));
        }
    }

    @PostMapping("/signin/user")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> role = new ArrayList<>();
        role.add("ROLE_USER");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        if (roles.get(0) != role.get(0)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
        } else {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getFullname(),
                    userDetails.getAvatar(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getIs_enable(),
                    roles));
        }
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Tài khoản đã được sử dụng!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email này đã được sử dụng!"));
        }

        // Create new user's account
        User user = new User(
                signupRequest.getFullname(),
                signupRequest.getAvatar(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getIs_enable());

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Nothing to say :D"));
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Đăng kí thành công!"));
    }

    @PostMapping("/dep/signup")
    public ResponseEntity<?> registerDep(@Validated @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

//        if (userRepository.existsByEmail(signupRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }

        // Create new user's account
        User user = new User(
                signupRequest.getFullname(),
                signupRequest.getAvatar(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getIs_enable());

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(ERole.ROLE_DEP)
                .orElseThrow(() -> new RuntimeException("Nothing to say :D"));
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Đăng kí thành công!"));
    }
}
