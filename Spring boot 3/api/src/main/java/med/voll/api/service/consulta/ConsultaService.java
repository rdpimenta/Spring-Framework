package med.voll.api.service.consulta;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCadastroConsulta;

public interface ConsultaService {
    Consulta criaConsulta(DadosCadastroConsulta dados);
}
