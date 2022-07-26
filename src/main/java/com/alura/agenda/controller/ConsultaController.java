package com.alura.agenda.controller;

import com.alura.agenda.dto.ConsultaDto;
import com.alura.agenda.dto.ConsultaFormDto;
import com.alura.agenda.dto.MedicoDto;
import com.alura.agenda.dto.MedicoFormDto;
import com.alura.agenda.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity<ConsultaDto> cadastrar(@RequestBody @Valid ConsultaFormDto dto, UriComponentsBuilder uriBuilder) {
        ConsultaDto consultaCadastrada = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaCadastrada.getId()).toUri();
        return ResponseEntity.created(endereco).body(consultaCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> cancelar(@PathVariable @NotNull Long id) {
        ConsultaDto consultaCancelada = service.cancelar(id);
        return ResponseEntity.ok(consultaCancelada);
    }

    // TODO - Implementar endpoint para as consultas do dia
    // TODO - Implementar endpoint para as consultas dos proximos 7 dias
}
