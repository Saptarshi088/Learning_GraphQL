package com.saptarshi.SpringGraphQL.userService;

import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        var student = studentRepository.findByEmail(email);

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles(String.valueOf(student.getRole()))
                .build();
    }
}
