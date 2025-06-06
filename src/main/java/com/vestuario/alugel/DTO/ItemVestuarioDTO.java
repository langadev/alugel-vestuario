package com.vestuario.alugel.DTO;

import com.vestuario.alugel.Enums.EstadoItem;
import jakarta.validation.constraints.*;

public record ItemVestuarioDTO(

    Long id,

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    String nome,

    @NotBlank(message = "Categoria é obrigatória")
    @Size(min = 2, max = 50, message = "Categoria deve ter entre 2 e 50 caracteres")
    String categoria,

    @NotBlank(message = "Tamanho é obrigatório")
    @Pattern(regexp = "^(PP|P|M|G|GG|XG|[0-9]{1,3})$", 
             message = "Tamanho deve ser PP, P, M, G, GG, XG ou número entre 0 e 999")
    String tamanho,

    @Size(max = 500, message = "Descrição pode ter até 500 caracteres")
    String descricao,

    @NotNull(message = "Estado é obrigatório")
    EstadoItem estado,

    @NotNull(message = "Preço diário é obrigatório")
    @Positive(message = "Preço diário deve ser maior que zero")
    @Digits(integer = 6, fraction = 2, message = "Preço diário deve ter até 6 dígitos inteiros e 2 decimais")
    Double precoDiario

) {}
