package com.vestuario.alugel.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vestuario.alugel.Models.Reserva;
import com.vestuario.alugel.Models.Utilizador;
import com.vestuario.alugel.Models.ItemVestuario;
import com.vestuario.alugel.DTO.ReservaDTO;
import com.vestuario.alugel.Repositories.ReservaRepository;
import com.vestuario.alugel.Repositories.UtilizadorRepository;
import com.vestuario.alugel.Repositories.ItemVestuarioRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final ItemVestuarioRepository itemRepository;

    public ReservaService(
        ReservaRepository reservaRepository,
        UtilizadorRepository utilizadorRepository,
        ItemVestuarioRepository itemRepository
    ) {
        this.reservaRepository = reservaRepository;
        this.utilizadorRepository = utilizadorRepository;
        this.itemRepository = itemRepository;
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva criar(ReservaDTO dto) {
        Utilizador utilizador = utilizadorRepository.findById(dto.id_utilizador())
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        ItemVestuario item = itemRepository.findById(dto.id_item())
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Reserva reserva = dto.paraReserva(utilizador, item);
        return reservaRepository.save(reserva);
    }

    public Reserva atualizar(Long id, ReservaDTO dto) {
        reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        Utilizador utilizador = utilizadorRepository.findById(dto.id_utilizador())
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        ItemVestuario item = itemRepository.findById(dto.id_item())
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Reserva reservaAtualizada = dto.paraReserva(utilizador, item);
        reservaAtualizada.setId_reserva(id); // importante manter o ID

        return reservaRepository.save(reservaAtualizada);
    }

    public void deletar(Long id) {
        reservaRepository.deleteById(id);
    }
}
