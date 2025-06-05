package com.vestuario.alugel.Models;

import java.util.List;

import org.hibernate.annotations.Type;

import com.vestuario.alugel.Models.roles.UtilizadorROLE;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Type(ListArrayType.class)
    @Column(name = "perfis",columnDefinition = "varchar[]")
    private List<UtilizadorROLE> perfis;
    @Column
    private String telefone;


}
