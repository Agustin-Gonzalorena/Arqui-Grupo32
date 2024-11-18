package com.microservice.gateway.security;

import com.microservice.gateway.presentation.dto.UsuarioDTO;
import com.microservice.gateway.service.http.UsuarioClient;
import com.microservice.gateway.presentation.dto.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UsuarioClient usuarioClient;

    public DomainUserDetailsService( UsuarioClient usuarioClient ) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email ) {
        log.debug("Authenticating {}", email);

        UsuarioDTO usuarioDTO = usuarioClient.getUsuarioByEmail( email.toLowerCase() ).getBody().getData();
        if(usuarioDTO == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        return createSpringSecurityUser(usuarioDTO);
    }

    private UserDetails createSpringSecurityUser( UsuarioDTO user ) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map( Authority::getName )
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toList() );
        return new CustomUserDetails( user.getId(),user.getEmail(), user.getPassword(), grantedAuthorities );
    }

}
