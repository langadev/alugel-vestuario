package com.vestuario.alugel.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vestuario.alugel.Models.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador,Long>{

    Utilizador findByEmail(String email);
    
} 