package com.vestuario.alugel.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vestuario.alugel.DTO.ItemVestuarioDTO;
import com.vestuario.alugel.Models.ItemVestuario;
import com.vestuario.alugel.Services.ItemVestuarioService;
import com.vestuario.alugel.exceptions.RecursoNaoEncontradoException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemVestuarioController {

    private final ItemVestuarioService service;

    public ItemVestuarioController(ItemVestuarioService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public List<ItemVestuario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVestuario> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

 @PostMapping
public ResponseEntity<Object> criar(@RequestBody @Valid ItemVestuarioDTO dto) {
    ItemVestuario novo = new ItemVestuario();
    novo.setNome(dto.nome());
    novo.setCategoria(dto.categoria());
    novo.setTamanho(dto.tamanho());
    novo.setDescricao(dto.descricao());
    novo.setEstado(dto.estado());
    novo.setPrecoDiario(dto.precoDiario());

    ItemVestuario criado = service.criar(novo);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(criado.getId_item())
            .toUri();

    return ResponseEntity.created(uri).body(criado);
}

@PutMapping("/{id}")
public ResponseEntity<ItemVestuario> atualizar(@PathVariable Long id, @RequestBody @Valid ItemVestuarioDTO dto) {
    service.buscarPorId(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Item com ID " + id + " não encontrado"));

    ItemVestuario atualizado = new ItemVestuario();
    atualizado.setNome(dto.nome());
    atualizado.setCategoria(dto.categoria());
    atualizado.setTamanho(dto.tamanho());
    atualizado.setDescricao(dto.descricao());
    atualizado.setEstado(dto.estado());
    atualizado.setPrecoDiario(dto.precoDiario());

    ItemVestuario resultado = service.atualizar(id, atualizado);
    return ResponseEntity.ok(resultado);
}


@GetMapping
    public ResponseEntity<List<ItemVestuarioDTO>> pesquisar(
       @RequestParam(value = "nome", required = false) String nome,
       @RequestParam(value = "categoria", required = false)  String categoria,
       @RequestParam(value = "tamanho", required = false)  String tamanho
       ){
        List<ItemVestuario> resultado = service.pesquisarByExample(nome, categoria,tamanho);
        List<ItemVestuarioDTO> lista = resultado.stream().map(item-> new ItemVestuarioDTO(item.getId_item(), item.getNome(), item.getCategoria(), item.getTamanho(),item.getDescricao(), item.getEstado(),item.getPrecoDiario())).collect(Collectors.toList());

        return ResponseEntity.ok(lista);

    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (service.buscarPorId(id).isEmpty()) {
        throw new RecursoNaoEncontradoException("Item com ID " + id + " não encontrado");
    }

    

    service.deletar(id);
    return ResponseEntity.noContent().build();
}
}
