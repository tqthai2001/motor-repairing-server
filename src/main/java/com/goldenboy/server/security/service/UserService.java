package com.goldenboy.server.security.service;

import com.goldenboy.server.dto.UserDTO;
import com.goldenboy.server.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public UserDTO login(String username, String password) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles =
                userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return new UserDTO(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    public UserDTO getUserDetail() {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;
                List<String> roles = userDetails.getAuthorities()
                                                .stream()
                                                .map(item -> item.getAuthority())
                                                .collect(Collectors.toList());

                return new UserDTO(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
