package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(
            @RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento) {
        return ResponseEntity.ok().body(service.getAll(dataDeNascimento));
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<Aluno> getById(@PathVariable Long alunoId){
        return ResponseEntity.ok(service.get(alunoId));
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm alunoForm) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(alunoForm));
    }

    @GetMapping("/avaliacoes/{alunoId}")
    public ResponseEntity<List<AvaliacaoFisica>> getAllAvaliacoesFisicasPorIdAluno(@PathVariable Long alunoId){

        return ResponseEntity.ok().body(service.getAllAvaliacoesFisicasPorIdAluno(alunoId));
    }
}
