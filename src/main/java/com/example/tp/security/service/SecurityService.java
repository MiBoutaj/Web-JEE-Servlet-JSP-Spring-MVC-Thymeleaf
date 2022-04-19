package com.example.tp.security.service;

import com.example.tp.security.entities.AppRole;
import com.example.tp.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String userName,String password , String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String userName,String roleName);
    void removeRoleFromUser(String userName,String roleName);
    AppUser loadUserByUserName(String username);

}
