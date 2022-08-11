package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody AlunoForm alunoForm) throws URISyntaxException {
        Aluno alunoSalvo = service.create(alunoForm);
        URI uri = new URI("/alunos/" + alunoSalvo.getId());

        return ResponseEntity.created(uri).body(alunoSalvo);
    }
}
