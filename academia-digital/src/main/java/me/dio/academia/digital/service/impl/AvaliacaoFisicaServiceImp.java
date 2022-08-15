package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exceptions.EntidadeNaoEncontradaException;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Projeto: academia-digital
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 11/08/2022
 * Hora: 09:17
 */
@Service
public class AvaliacaoFisicaServiceImp implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoServiceImpl alunoService;


    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {

        Aluno alunoEncontrado = alunoService.get(form.getAlunoId());

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        avaliacaoFisica.setAluno(alunoEncontrado);
        avaliacaoFisica.setAltura(form.getAltura());
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.calcularImc();

        avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);


        return avaliacaoFisica;
    }

    @Override
    public AvaliacaoFisica get(Long id) {

        return avaliacaoFisicaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Avaliação Física com o id " + id + " não foi encontrada!"));
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        AvaliacaoFisica avaliacaoFisica = this.get(id);
        avaliacaoFisica.setAltura(formUpdate.getAltura());
        avaliacaoFisica.setPeso(formUpdate.getPeso());

        avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);
        return avaliacaoFisica;
    }

    @Override
    public void delete(Long id) {
        this.get(id);
        avaliacaoFisicaRepository.deleteById(id);
    }
}
