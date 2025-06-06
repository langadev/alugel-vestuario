package com.vestuario.alugel.Models;

import com.vestuario.alugel.Enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "utilizador")
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilizador;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "utilizador_perfis", joinColumns = @JoinColumn(name = "utilizador_id"))
    @Column(name = "perfil")
    private List<Perfil> perfis;

    @Column
    private String telefone;
}
