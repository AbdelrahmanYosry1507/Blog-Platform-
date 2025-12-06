package org.example.blogplatform.security;


import lombok.RequiredArgsConstructor;
import org.example.blogplatform.domain.entities.User;
import org.example.blogplatform.repostries.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:= "+email));
        return new BlogUserDetails(user);
    }
}
