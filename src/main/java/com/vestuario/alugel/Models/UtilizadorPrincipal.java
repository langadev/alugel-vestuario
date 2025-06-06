package com.vestuario.alugel.Models;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Data
public class UtilizadorPrincipal implements UserDetails {
    private Utilizador utilizador;

    public UtilizadorPrincipal(Utilizador utilizador){
        this.utilizador = utilizador;
    }

   @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    var authorities = utilizador.getPerfis()
        .stream()
        .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.name()))
        .toList();

    System.out.println("Authorities: " + authorities); // 👈 Depuração

    return authorities;
}

    @Override
    public String getPassword() {
        return utilizador.getSenha();
    }

    @Override
    public String getUsername() {
      return utilizador.getEmail();
    }
    
}
