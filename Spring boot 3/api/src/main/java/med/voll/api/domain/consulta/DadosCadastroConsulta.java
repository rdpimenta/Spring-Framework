package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastroConsulta(
        String nomeMedico,
        @NotBlank
        String nomePaciente,
        @NotNull
        LocalDate dataConsulta,
        @NotNull
        LocalTime horarioInicio
) {
}
