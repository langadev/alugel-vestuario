package com.vestuario.alugel.Services;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vestuario.alugel.Models.Utilizador;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecuritySerive {
    private final UtilizadorService usuarioService;
    public Utilizador obterPorUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String login = userDetails.getUsername();
        return usuarioService.findByEmail(login);
    }
}
