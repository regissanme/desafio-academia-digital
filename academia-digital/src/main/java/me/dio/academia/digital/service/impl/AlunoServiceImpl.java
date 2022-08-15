package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exceptions.EntidadeNaoEncontradaException;
import me.dio.academia.digital.exceptions.ErroDeNegocioException;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        Aluno cpfEmUso = alunoRepository.findByCpf(form.getCpf());
        if (cpfEmUso != null) {
            throw new ErroDeNegocioException("Já possui um registro cadastrado com esse CPF");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());
        aluno.setAtivo(form.isAtivo());

        aluno = alunoRepository.save(aluno);
        aluno.setAvaliacoes(null);

        return aluno;
    }

    @Override
    public Aluno get(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Aluno(o) com o id " + id + " não foi encontrado(a)!"));
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if (dataDeNascimento == null) {
            return alunoRepository.findAll();
        }
        LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
        return alunoRepository.findByDataDeNascimento(localDate);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = this.get(id);

        aluno.setNome(formUpdate.getNome());
        aluno.setBairro(formUpdate.getBairro());
        aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
        aluno.setAtivo(formUpdate.isAtivo());
        aluno = alunoRepository.save(aluno);

        return aluno;
    }

    @Override
    public void delete(Long id) {
        Aluno aluno = this.get(id);

        // O aluno não deve ser deletado, somente colocado como inativo
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacoesFisicasPorIdAluno(Long alunoId) {
        Aluno aluno = this.get(alunoId);
        return aluno.getAvaliacoes();
    }

    @Override
    public List<Aluno> findByDataDeNascimento(LocalDate dataDeNascimento) {
        return alunoRepository.findByDataDeNascimento(dataDeNascimento);
    }
}
