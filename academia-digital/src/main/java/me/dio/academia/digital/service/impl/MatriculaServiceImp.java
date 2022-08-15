package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exceptions.EntidadeNaoEncontradaException;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Projeto: academia-digital
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 11/08/2022
 * Hora: 11:11
 */
@Service
public class MatriculaServiceImp implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoServiceImpl alunoService;

    @Autowired
    private AlunoRepository alunoRepository;



    @Override
    public Matricula create(MatriculaForm form) {
        Aluno aluno = alunoService.get(form.getAlunoId());
        if(!aluno.isAtivo()){
            aluno.setAtivo(true);
            alunoRepository.save(aluno);
        }

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula = matriculaRepository.save(matricula);

        return matricula;
    }

    @Override
    public Matricula get(Long id) {
        return matriculaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Matrícula com o id " + id + " não foi encontrada!"));
    }

    @Override
    public List<Matricula> getAll(String bairro) {
        if (bairro == null) {
            return matriculaRepository.findAll();
        }
        // Testes de Conceitos
        // Query nativa
        return matriculaRepository.findAlunosMatriculadosPorBairroSGBD(bairro);

        // HQL
//        return matriculaRepository.findAlunosMatriculadosPorBairro(bairro);

        // JPA
//        return matriculaRepository.findByAlunoBairro(bairro);
    }

    @Override
    public void delete(Long id) {
        this.get(id);
        matriculaRepository.deleteById(id);
    }
}
