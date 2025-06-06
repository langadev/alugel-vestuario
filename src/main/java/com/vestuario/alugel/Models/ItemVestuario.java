package com.vestuario.alugel.Models;

import com.vestuario.alugel.Enums.EstadoItem;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "item_vestuario")
public class ItemVestuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String tamanho;

    @Column(columnDefinition = "TEXT")
    private String descricao;

   @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoItem estado;

    @Column(name = "preco_diario", nullable = false)
    private Double precoDiario;


}
