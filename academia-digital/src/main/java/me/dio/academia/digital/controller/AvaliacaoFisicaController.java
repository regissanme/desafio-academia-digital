package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImp service;


    @GetMapping
    public ResponseEntity<List<AvaliacaoFisica>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoFisica> create(@Valid @RequestBody AvaliacaoFisicaForm avaliacaoFisicaForm) throws URISyntaxException {
        AvaliacaoFisica avaliacaoFisicaSalva = service.create(avaliacaoFisicaForm);
        URI uri = new URI("/avaliacoes/" + avaliacaoFisicaSalva.getId());

        return ResponseEntity.created(uri).body(avaliacaoFisicaSalva);
    }
}
