package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.paciente.DadosListagemPaciente;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosListagemConsulta(
        Long id,

        LocalDate dataConsulta,

        LocalTime horarioInicio,

        LocalTime horarioFim,

        DadosListagemMedico medico,

        DadosListagemPaciente paciente,

        Boolean ativa,
        MotivoCancelamento motivoCancelamento
) {
    public DadosListagemConsulta(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getDataConsulta(),
                consulta.getHorarioInicio(),
                consulta.getHorarioFim(),
                new DadosListagemMedico(consulta.getMedico()),
                new DadosListagemPaciente(consulta.getPaciente()),
                consulta.getAtiva(),
                consulta.getMotivoCancelamento()
        );
    }

    public DadosListagemConsulta(Long id, LocalDate dataConsulta, LocalTime horarioInicio, LocalTime horarioFim, DadosListagemMedico medico, DadosListagemPaciente paciente, Boolean ativa, MotivoCancelamento motivoCancelamento) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.medico = medico;
        this.paciente = paciente;
        this.ativa = ativa;
        this.motivoCancelamento = motivoCancelamento;
    }
}
