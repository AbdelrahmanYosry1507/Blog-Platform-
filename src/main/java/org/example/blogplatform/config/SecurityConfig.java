package org.example.blogplatform.config;


import org.example.blogplatform.Services.AuthenticationService;
import org.example.blogplatform.domain.entities.User;
import org.example.blogplatform.repostries.UserRepository;
import org.example.blogplatform.security.BlogUserDetailsService;
import org.example.blogplatform.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

   @Bean
   public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
       return new JwtAuthenticationFilter(authenticationService);
   }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        BlogUserDetailsService blogUserDetailsService = new BlogUserDetailsService(userRepository);

        // Create a default test user only if not exists
        String email = "user@test.com";

        userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = User.builder()
                    .name("Test User")
                    .email(email)
                    .password(passwordEncoder().encode("password"))
                    .build();

            return userRepository.save(newUser);
        });

        return blogUserDetailsService;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categories").permitAll()
                        .requestMatchers(HttpMethod.GET,"/tags").permitAll()
                        .requestMatchers(HttpMethod.GET,"/posts").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tags").authenticated()
                        .anyRequest().authenticated()
                )
                .csrf(crsf->
                        crsf.disable()
                ).sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
