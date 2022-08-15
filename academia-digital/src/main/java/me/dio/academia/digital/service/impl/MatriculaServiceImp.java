package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Optional<Aluno> aluno = alunoRepository.findById(form.getAlunoId());
        if (aluno.isPresent()) {
            Matricula matricula = new Matricula();
            matricula.setAluno(aluno.get());

            matricula = matriculaRepository.save(matricula);
            return matricula;
        }
        return null;
    }

    @Override
    public Matricula get(Long id) {
        return matriculaRepository.getById(id);
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

    }
}
