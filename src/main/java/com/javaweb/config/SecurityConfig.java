package com.javaweb.config;

import com.javaweb.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // -------------------------------------------------------
    // BCrypt password encoder
    // -------------------------------------------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // -------------------------------------------------------
    // DaoAuthenticationProvider: kết nối UserDetailsService + encoder
    // -------------------------------------------------------
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // -------------------------------------------------------
    // AuthenticationManager
    // -------------------------------------------------------
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // -------------------------------------------------------
    // Security Filter Chain
    // -------------------------------------------------------
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth
                // Public pages
                .requestMatchers("/", "/trang-chu", "/gioi-thieu", "/gioi-thieu/search",
                                 "/san-pham", "/san-pham/search",
                                 "/tin-tuc", "/lien-he", "/login", "/dang-ky",
                                 "/css/**", "/js/**", "/images/**", "/webjars/**",
                                 "/error/**").permitAll()
                // REST API: chỉ MANAGER hoặc STAFF mới dùng được
                .requestMatchers("/api/**").hasAnyRole("MANAGER", "STAFF")
                // Admin pages: chỉ MANAGER hoặc STAFF
                .requestMatchers("/admin/**").hasAnyRole("MANAGER", "STAFF")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .successHandler(successHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/error/403")
            )
            // Tắt CSRF cho REST API endpoints, giữ cho web form
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**", "/gioi-thieu/search", "/san-pham/search",
                        "/admin/building/search", "/admin/customer/search")
            );

        return http.build();
    }

    // -------------------------------------------------------
    // Custom success handler: điều hướng theo role
    // -------------------------------------------------------
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            boolean isManager = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));
            boolean isStaff = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"));

            if (isManager || isStaff) {
                response.sendRedirect("/admin/building");
            } else {
                response.sendRedirect("/");
            }
        };
    }
}
