package io.github.marcelothebuilder.datasourcepertenant.auth;

import io.github.marcelothebuilder.datasourcepertenant.model.User;
import io.github.marcelothebuilder.datasourcepertenant.repository.config.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user))
            throw new UsernameNotFoundException("User not found!");
        return new UserDetailsImpl(user);
    }
}
