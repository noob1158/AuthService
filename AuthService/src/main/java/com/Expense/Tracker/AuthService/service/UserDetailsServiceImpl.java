package com.Expense.Tracker.AuthService.service;


import com.Expense.Tracker.AuthService.entities.UserInfo;
import com.Expense.Tracker.AuthService.model.UserInfoDto;
import com.Expense.Tracker.AuthService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user= userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("could not found user");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExists(UserInfoDto userInfoDto){
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto){
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExists(userInfoDto))){
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userInfoDto.getUserId(),userInfoDto.getUsername(),userInfoDto.getPassword(),new HashSet<>()));
        return true;
    }
}