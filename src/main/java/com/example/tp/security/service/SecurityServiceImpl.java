package com.example.tp.security.service;

import com.example.tp.security.entities.AppRole;
import com.example.tp.security.entities.AppUser;

import com.example.tp.security.repository.AppRoleRepository;
import com.example.tp.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser saveNewUser(String userName, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(userName);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {

        AppRole appRole1 = appRoleRepository.findByRoleName(roleName);
        if (appRole1 != null) throw new RuntimeException("ROLE " + roleName + "Already existt");
        appRole1 = new AppRole();
        appRole1.setRoleName(roleName);
        appRole1.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole1);
        return appRole1;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(userName);

    }

    @Override
    public void removeRoleFromUser(String userName, String roleName) {

    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return null;
    }
}
