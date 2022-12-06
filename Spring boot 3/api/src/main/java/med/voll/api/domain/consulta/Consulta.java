package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_consulta")
    private LocalDate dataConsulta;
    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;
    @Column(name = "horario_fim")
    private LocalTime horarioFim;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    public Consulta(LocalDate dataConsulta, LocalTime horarioInicio, LocalTime horarioFim, Medico medico, Paciente paciente) {
        this.dataConsulta = dataConsulta;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.medico = medico;
        this.paciente = paciente;
    }
}
