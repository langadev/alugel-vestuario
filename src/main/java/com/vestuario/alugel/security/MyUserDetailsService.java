package com.vestuario.alugel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vestuario.alugel.Models.Utilizador;
import com.vestuario.alugel.Models.UtilizadorPrincipal;
import com.vestuario.alugel.Repositories.UtilizadorRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizador utilizador = utilizadorRepository.findByEmail(username);
        if(utilizador==null){
           
            throw new UsernameNotFoundException("User not found !");
        }
            // return User
            // .withUsername(utilizador.getEmail())
            // .password(utilizador.getSenha())
            // .authorities("ROLE_USER","CLIENTE","ADMIN")
            // .build();
            return new UtilizadorPrincipal(utilizador);
        }
    }
    

