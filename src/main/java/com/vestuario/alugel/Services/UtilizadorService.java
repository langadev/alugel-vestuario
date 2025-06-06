package com.vestuario.alugel.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vestuario.alugel.Models.Utilizador;
import com.vestuario.alugel.Repositories.UtilizadorRepository;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public Utilizador save(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador findById(Long id) {
        return utilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado com id: " + id));
    }
    public Utilizador findByEmail(String email){
        return utilizadorRepository.findByEmail(email);
    }

    public Utilizador update(Long id, Utilizador atualizado) {
        Utilizador existente = findById(id);

        existente.setNome(atualizado.getNome());
        existente.setEmail(atualizado.getEmail());
        existente.setSenha(atualizado.getSenha());
        existente.setTelefone(atualizado.getTelefone());
        existente.setPerfis(atualizado.getPerfis());

        return utilizadorRepository.save(existente);
    }

    public void delete(Long id) {
        utilizadorRepository.deleteById(id);
    }
}
