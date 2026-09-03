package com.nexushr.auth.config;

import com.nexushr.auth.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.Customizer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .cors(Customizer.withDefaults())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/api/auth/register",
                    "/api/auth/login"
                ).permitAll()

                .requestMatchers("/api/employee/**")
                .hasRole("EMPLOYEE")

                .requestMatchers("/api/profile/**")
                .hasRole("EMPLOYEE")

                .requestMatchers("/api/manager/**")
                .hasRole("MANAGER")

                .requestMatchers("/api/hr/**")
                .hasRole("HR")

                .requestMatchers("/api/admin/**")
                .hasRole("ADMIN")

                .requestMatchers("/api/employees/**")
                .hasAnyRole("HR", "MANAGER", "ADMIN")

                .requestMatchers("/api/departments/**")
                .hasAnyRole("HR", "MANAGER", "ADMIN")

                .requestMatchers("/api/designations/**")
                .hasAnyRole("HR", "MANAGER", "ADMIN")

                .requestMatchers(
                    "/api/attendance/check-in",
                    "/api/attendance/check-out/**",
                    "/api/attendance/my-attendance"
                )
                .hasRole("EMPLOYEE")

                .requestMatchers("/api/attendance/all")
                .hasAnyRole("HR", "MANAGER", "ADMIN")

                .requestMatchers(
                    "/api/leaves/apply",
                    "/api/leaves/my-leaves"
                )
                .hasRole("EMPLOYEE")

                .requestMatchers("/api/leaves/**")
                .hasAnyRole("EMPLOYEE", "HR", "MANAGER", "ADMIN")

                // PAYROLL MODULE

                .requestMatchers("/api/payroll/my-payroll")
                .hasRole("EMPLOYEE")

                .requestMatchers(
                    "/api/payroll/create",
                    "/api/payroll/all",
                    "/api/payroll/**"
                )
                .hasAnyRole("FINANCE", "HR", "MANAGER", "ADMIN")

                .requestMatchers("/api/reports/**")
                .hasAnyRole("HR", "MANAGER", "ADMIN")
                  
                    .requestMatchers("/api/jobs/**", "/api/candidates/**", "/api/interviews/**", "/api/recruitment/reports/**")
                    .authenticated()
                    
                    .requestMatchers("/api/onboarding/**")
                    .hasAnyRole("HR", "ADMIN")

                   .requestMatchers("/api/performance/my")
                    .hasRole("EMPLOYEE")

                   .requestMatchers("/api/performance/**")
                    .hasAnyRole("MANAGER", "HR", "ADMIN")

                    .requestMatchers("/api/documents/**")
                    .hasAnyRole("ADMIN", "HR", "MANAGER", "EMPLOYEE")

                     .requestMatchers("/api/notifications/**")
                     .hasAnyRole("ADMIN", "HR", "MANAGER", "EMPLOYEE")

                     .requestMatchers("/api/dashboard/**")
                     .hasAnyRole("ADMIN", "HR", "MANAGER", "EMPLOYEE")

                     .requestMatchers("/api/ai/**")
                     .hasAnyRole("EMPLOYEE", "ADMIN", "HR", "MANAGER", "RECRUITER", "FINANCE", "INTERVIEWER")

                    .requestMatchers("/api/audit-logs/**", "/api/permissions/**")
                    .hasRole("ADMIN")

                    .requestMatchers("/api/training/mine")
                    .hasRole("EMPLOYEE")

                    .requestMatchers("/api/training/**")
                    .hasAnyRole("HR", "MANAGER", "ADMIN")

                    .requestMatchers("/api/assets/mine")
                    .hasRole("EMPLOYEE")

                    .requestMatchers("/api/assets/**")
                    .hasAnyRole("HR", "ADMIN")

                    .requestMatchers("/api/ess/**")
                    .hasRole("EMPLOYEE")

                    .requestMatchers("/api/workflows/mine")
                    .authenticated()

                    .requestMatchers("/api/workflows/**")
                    .hasAnyRole("MANAGER", "HR", "ADMIN")

                    .requestMatchers("/api/auth/register").permitAll()
                    .requestMatchers("/api/auth/login").permitAll()

                    .requestMatchers(
                                   "/api/auth/refresh",
                                                                        "/api/auth/password/forgot",
                                                                        "/api/auth/password/reset",
                                                                        "/api/auth/password/verify",
                                                                        "/api/auth/verification/**"
                               ).permitAll()
                 .anyRequest()
                 .authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}