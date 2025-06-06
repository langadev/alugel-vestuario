package com.vestuario.alugel.controllers;


import com.vestuario.alugel.DTO.ReservaDTO;
import com.vestuario.alugel.Models.Reserva;
import com.vestuario.alugel.Services.ReservaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> criar(@Valid @RequestBody ReservaDTO dto) {
        Reserva novaReserva = reservaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO dto) {
        if (reservaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Reserva reservaAtualizada = reservaService.atualizar(id, dto);
        return ResponseEntity.ok(reservaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (reservaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

