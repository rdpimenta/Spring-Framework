package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public void criaConsulta(
            @RequestBody
            @Valid
            DadosCadastroConsulta dados
    ) {
        consultaService.criaConsulta(dados);
    }

    @DeleteMapping
    @Transactional
    public void cancela(
            @RequestBody
            @Valid
            DadosCancelamentoConsulta dados
    ) {
        var consulta = consultaRepository.getReferenceById(dados.id());
        consulta.cancela(dados.motivo());
    }
}
