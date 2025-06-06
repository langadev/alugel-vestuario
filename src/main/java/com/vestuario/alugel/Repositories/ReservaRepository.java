package com.vestuario.alugel.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vestuario.alugel.Models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    
}
