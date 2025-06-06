package com.vestuario.alugel.DTO;

import java.util.List;

import com.vestuario.alugel.Enums.Perfil;
import com.vestuario.alugel.Models.Utilizador;

import jakarta.validation.constraints.*;

public record UtilizadorDTO(

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    String nome,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 50, message = "Senha deve ter entre 6 e 50 caracteres")
    String senha,

    @NotEmpty(message = "Deve ter pelo menos um perfil")
    List<Perfil> perfis,

    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Telefone deve conter apenas números e pode iniciar com +, entre 8 e 15 dígitos")
    String telefone

) {
    public Utilizador paraUtilizador(){
        Utilizador utilizador = new Utilizador();
        utilizador.setNome(nome);
        utilizador.setEmail(email);
        utilizador.setPerfis(perfis);
        utilizador.setSenha(senha);
        utilizador.setTelefone(telefone);
        return utilizador;
    }
}
