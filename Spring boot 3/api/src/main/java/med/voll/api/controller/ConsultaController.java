package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<DadosListagemConsulta> listar() {
        return consultaRepository.findAll().stream().map(DadosListagemConsulta::new).toList();
    }
}
