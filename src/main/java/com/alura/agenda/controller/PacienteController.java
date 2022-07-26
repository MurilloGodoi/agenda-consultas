package com.alura.agenda.controller;

import com.alura.agenda.dto.AtualizacaoPacienteFormDto;
import com.alura.agenda.dto.PacienteDto;
import com.alura.agenda.dto.PacienteFormDto;
import com.alura.agenda.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public Page<PacienteDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> cadastrar(@RequestBody @Valid PacienteFormDto dto, UriComponentsBuilder uriBuilder) {
        PacienteDto pacienteCadastrado = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteCadastrado.getId()).toUri();
        return ResponseEntity.created(endereco).body(pacienteCadastrado);
    }

    @PutMapping
    public ResponseEntity<PacienteDto> atualizar(@RequestBody @Valid AtualizacaoPacienteFormDto dto) {
        PacienteDto pacienteAtualizado = service.atualizar(dto);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
