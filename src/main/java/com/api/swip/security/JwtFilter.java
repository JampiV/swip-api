package com.api.swip.security;

import com.api.swip.dao.IUserRepo;
import com.api.swip.entity.User;
import com.api.swip.service.ITokenService;
import com.api.swip.service.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter
{
    private final JwUtil jwtTokenUtil;
    private final MyUserDetailService jwtUserDetailsService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(tokenHeader != null) {
            if(tokenHeader.startsWith("Bearer ") || tokenHeader.startsWith("bearer ")) {
                jwtToken = tokenHeader.substring(7);

                try{
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                }catch (Exception ex){
                    request.setAttribute("exception", ex.getMessage());
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            User user = userRepo.findByCodigo(username).orElseThrow();

            if(jwtTokenUtil.validateToken(jwtToken, userDetails) && tokenService.isTokenActive(jwtToken, user)) {
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken
                        (userDetails, null, userDetails.getAuthorities());

                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}