package com.vestuario.alugel.DTO;

import com.vestuario.alugel.Models.ItemVestuario;
import com.vestuario.alugel.Models.Reserva;
import com.vestuario.alugel.Models.Utilizador;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ReservaDTO(

    @NotNull(message = "ID do utilizador é obrigatório")
    Long id_utilizador,

    @NotNull(message = "ID do item é obrigatório")
    Long id_item,

    @NotNull(message = "Data de início é obrigatória")
    @FutureOrPresent(message = "A data de início deve ser hoje ou futura")
    LocalDate data_inicio,

    @NotNull(message = "Data de fim é obrigatória")
    @Future(message = "A data de fim deve ser no futuro")
    LocalDate data_fim,

    @NotBlank(message = "Status da reserva é obrigatório")
    String status

) {
    public Reserva paraReserva(Utilizador utilizador, ItemVestuario item) {
        Reserva reserva = new Reserva();
        reserva.setUtilizador(utilizador);
        reserva.setItem(item);
        reserva.setData_inicio(data_inicio);
        reserva.setData_fim(data_fim);
        reserva.setStatus(status);
        return reserva;
    }
}
