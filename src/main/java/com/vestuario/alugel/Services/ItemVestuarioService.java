package com.vestuario.alugel.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import com.vestuario.alugel.Models.ItemVestuario;
import com.vestuario.alugel.Repositories.ItemVestuarioRepository;

@Service
public class ItemVestuarioService {

    private final ItemVestuarioRepository repository;

    public ItemVestuarioService(ItemVestuarioRepository repository) {
        this.repository = repository;
    }

    public List<ItemVestuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<ItemVestuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ItemVestuario criar(ItemVestuario item) {
        return repository.save(item);
    }

    public ItemVestuario atualizar(Long id, ItemVestuario novoItem) {
        return repository.findById(id)
            .map(item -> {
                item.setNome(novoItem.getNome());
                item.setCategoria(novoItem.getCategoria());
                item.setTamanho(novoItem.getTamanho());
                item.setDescricao(novoItem.getDescricao());
                item.setEstado(novoItem.getEstado());
                item.setPrecoDiario(novoItem.getPrecoDiario());
                return repository.save(item);
            })
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

      public List<ItemVestuario> pesquisarByExample(String nome, String categoria, String tamanho){
        var item = new ItemVestuario();
        item.setNome(nome);
        item.setCategoria(categoria);
        item.setTamanho(tamanho);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<ItemVestuario> itemExample = Example.of(item,matcher);
        return repository.findAll(itemExample);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
