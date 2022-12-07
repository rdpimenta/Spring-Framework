package med.voll.api.service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ConsultaServiceImp implements ConsultaService {
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void criaConsulta(DadosCadastroConsulta dados) {
        validaDataEHorario(dados);
        checaAntecedencia(dados);

        if (dados.nomeMedico().equals("")) {
            Medico medico = selecionarMedicoAleatorio(dados);
            Paciente paciente = pacienteRepository.findByNome(dados.nomePaciente());

            if (medico != null) {
                validaPacienteAtivo(paciente);
                validaConsultaPaciente(paciente, dados);

                salvaConsulta(dados, medico, paciente);
            } else {
                throw new RuntimeException("Consulta não pode ser salva.");
            }
        } else {
            Medico medico = medicoRepository.findByNome(dados.nomeMedico());
            Paciente paciente = pacienteRepository.findByNome(dados.nomePaciente());

            validaMedicoAtivo(medico);
            validaPacienteAtivo(paciente);
            validaConsultaPaciente(paciente, dados);
            if (medicoEstaDisponivel(medico, dados)) {
                salvaConsulta(dados, medico, paciente);
            } else {
                throw new RuntimeException("Consulta não pode ser salva.");
            }
        }
    }

    private void salvaConsulta(DadosCadastroConsulta dados, Medico medico, Paciente paciente) {
        Consulta consulta = new Consulta(
                dados.dataConsulta(),
                dados.horarioInicio(),
                dados.horarioInicio().plusHours(1),
                medico,
                paciente
        );

        consultaRepository.save(consulta);
    }

    private Medico selecionarMedicoAleatorio(DadosCadastroConsulta dados) {
        List<Medico> medicos = medicoRepository.findAll();
        for (Medico medico : medicos) {
            if (medicoEstaDisponivel(medico, dados) && medico.getAtivo()) {
                return medico;
            }
        }

        return null;
    }

    private void validaDataEHorario(DadosCadastroConsulta dados) {
        if (
                dados.dataConsulta().getDayOfWeek() == DayOfWeek.SUNDAY
                        || dados.horarioInicio().getHour() < 7
                        || dados.horarioInicio().getHour() > 19
        ) {
            throw new RuntimeException("Data ou horário inválidos.");
        }
    }

    private void checaAntecedencia(DadosCadastroConsulta dados) {
        if (
                dados.horarioInicio().isBefore(LocalTime.now().plusMinutes(30))
                        && dados.dataConsulta().equals(LocalDate.now())
        ) {
            throw new RuntimeException("Horário não bate com antecedência.");
        }
    }

    private void validaMedicoAtivo(Medico medico) {
        if (!medico.getAtivo()) {
            throw new RuntimeException("Médico não está ativo");
        }
    }

    private void validaPacienteAtivo(Paciente paciente) {
        if (!paciente.getAtivo()) {
            throw new RuntimeException("Paciente não está ativo");
        }
    }

    private void validaConsultaPaciente(Paciente paciente, DadosCadastroConsulta dados) {
        List<Consulta> consultas = consultaRepository.findAll();

        consultas.forEach(consulta -> {
            if (
                    consulta.getAtiva()
                            && consulta.getPaciente().getId().equals(paciente.getId())
                            && consulta.getDataConsulta().equals(dados.dataConsulta())
            ) {
                throw new RuntimeException("Paciente já possui consulta neste dia.");
            }
        });
    }

    private Boolean medicoEstaDisponivel(Medico medico, DadosCadastroConsulta dados) {
        List<Consulta> consultas = consultaRepository.findAll();

        for (Consulta consulta : consultas) {
            if (
                    consulta.getAtiva()
                            && consulta.getMedico().getId().equals(medico.getId())
                            && consulta.getDataConsulta().equals(dados.dataConsulta())
                            && !validaHorarioConsulta(consulta, dados.horarioInicio())
            ) {
                return false;
            }
        }

        return true;
    }

    private Boolean validaHorarioConsulta(Consulta consulta, LocalTime horarioNovaConsulta) {
        return (horarioNovaConsulta.isAfter(consulta.getHorarioInicio())
                && horarioNovaConsulta.isBefore(consulta.getHorarioFim()))
                || !horarioNovaConsulta.equals(consulta.getHorarioInicio());
    }
}
