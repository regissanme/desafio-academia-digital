package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImp service;

    @PostMapping
    public ResponseEntity<Matricula> create(@Valid @RequestBody MatriculaForm matriculaForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(matriculaForm));
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll(
            @RequestParam(value = "bairro", required = false) String bairro
    ) {
        return ResponseEntity.ok(service.getAll(bairro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
