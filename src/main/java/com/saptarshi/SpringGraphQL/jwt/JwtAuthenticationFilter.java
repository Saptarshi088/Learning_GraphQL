package com.saptarshi.SpringGraphQL.jwt;

import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final StudentRepository studentRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/graphql");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = header.substring(7);
        if(!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        var student = studentRepository.findByEmail(jwtService.extractEmail(token));
        if(student == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var securityContext = SecurityContextHolder.getContext();
        var authentication = new UsernamePasswordAuthenticationToken(
                student, null, securityContext.getAuthentication().getAuthorities());
        securityContext.setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
