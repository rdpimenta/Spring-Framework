package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(
            @RequestBody
            @Valid
            DadosCadastroMedico dados,
            UriComponentsBuilder builder
    ) {
        var medico = new Medico(dados);
        medicoRepository.save(medico);
        var uri = builder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity< Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable paginacao
    ) {
        var page = medicoRepository
                .findAllByAtivoTrueOrderById(paginacao)
                .map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(
            @RequestBody
            @Valid
            DadosAtualizacaoMedico dados
    ) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity exclui(
            @PathVariable
            Long id
    ) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(
            @PathVariable
            Long id
    ) {
        var medico = medicoRepository.getReferenceById(id);
        var dto = new DadosDetalhamentoMedico(medico);

        return ResponseEntity.ok(dto);
    }
}
