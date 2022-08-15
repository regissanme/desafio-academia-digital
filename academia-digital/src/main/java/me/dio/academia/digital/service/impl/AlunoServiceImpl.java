package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Projeto: academia-digital
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 11/08/2022
 * Hora: 00:01
 */
@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        aluno = alunoRepository.save(aluno);
        aluno.setAvaliacoes(null);

        return aluno;
    }

    @Override
    public Aluno get(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if(dataDeNascimento == null) {
            return alunoRepository.findAll();
        }
        LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
        return alunoRepository.findByDataDeNascimento(localDate);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacoesFisicasPorIdAluno(Long alunoId) {
        Optional<Aluno> alunoEncontrado = alunoRepository.findById(alunoId);
        return alunoEncontrado.map(Aluno::getAvaliacoes).orElse(null);
    }

    @Override
    public List<Aluno> findByDataDeNascimento(LocalDate dataDeNascimento) {
        return null;
    }
}
