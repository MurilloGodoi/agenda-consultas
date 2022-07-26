package com.alura.agenda.controller;

import com.alura.agenda.dto.*;
import com.alura.agenda.service.MedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping
    public Page<MedicoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    public ResponseEntity<MedicoDto> cadastrar(@RequestBody @Valid MedicoFormDto dto, UriComponentsBuilder uriBuilder) {
        MedicoDto medicoCadastrado = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoCadastrado.getId()).toUri();
        return ResponseEntity.created(endereco).body(medicoCadastrado);
    }

    @PutMapping
    public ResponseEntity<MedicoDto> atualizar(@RequestBody @Valid AtualizacaoMedicoFormDto dto) {
        MedicoDto medicoAtualizado = service.atualizar(dto);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
