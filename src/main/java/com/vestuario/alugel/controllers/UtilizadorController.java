package com.vestuario.alugel.controllers;

import com.vestuario.alugel.DTO.UtilizadorDTO;
import com.vestuario.alugel.Models.Utilizador;
import com.vestuario.alugel.Services.UtilizadorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // CREATE
    @PostMapping("register")
    public Utilizador criar(@RequestBody @Valid UtilizadorDTO dto) {
        Utilizador utilizador = dto.paraUtilizador();
        utilizador.setSenha(passwordEncoder.encode(utilizador.getSenha())); // 🔒 Codifica a senha
        return utilizadorService.save(utilizador);
    }

    // READ - listar todos
    @GetMapping("utilizadores")
    public List<Utilizador> listarTodos() {
        return utilizadorService.findAll();
    }

    // READ - buscar por ID
    @GetMapping("utilizadores/{id}")
    public Utilizador buscarPorId(@PathVariable Long id) {
        return utilizadorService.findById(id);
    }

    // UPDATE
    @PutMapping("utilizadores/{id}")
    public Utilizador atualizar(@PathVariable Long id, @RequestBody @Valid UtilizadorDTO dto) {
        Utilizador utilizador = dto.paraUtilizador();
        utilizador.setSenha(passwordEncoder.encode(utilizador.getSenha())); // Também codifica ao atualizar
        return utilizadorService.update(id, utilizador);
    }

    // DELETE
    @DeleteMapping("utilizadores/{id}")
    public void deletar(@PathVariable Long id) {
        utilizadorService.delete(id);
    }
}
