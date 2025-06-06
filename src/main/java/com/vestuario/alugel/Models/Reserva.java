package com.vestuario.alugel.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador")
    private Utilizador utilizador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    private ItemVestuario item;

    @Column(nullable = false)
    private LocalDate data_inicio;

    @Column(nullable = false)
    private LocalDate data_fim;

    @Column(nullable = false)
    private String status;

}
