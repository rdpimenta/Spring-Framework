package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosListagemConsulta;
import med.voll.api.service.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity criaConsulta(
            @RequestBody
            @Valid
            DadosCadastroConsulta dados,
            UriComponentsBuilder builder
    ) {
        var consulta = consultaService.criaConsulta(dados);
        var uri = builder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancela(
            @RequestBody
            @Valid
            DadosCancelamentoConsulta dados
    ) {
        var consulta = consultaRepository.getReferenceById(dados.id());
        consulta.cancela(dados.motivo());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemConsulta>> listar() {
        var lista = consultaRepository.findAll().stream().map(DadosListagemConsulta::new).toList();

        return ResponseEntity.ok(lista);
    }
}
