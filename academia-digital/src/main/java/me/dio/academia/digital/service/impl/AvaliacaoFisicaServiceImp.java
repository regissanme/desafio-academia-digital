package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    private AlunoRepository alunoRepository;


    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {

        Optional<Aluno> alunoEncontrado = alunoRepository.findById(form.getAlunoId());

        AvaliacaoFisica avaliacaoFisica = null;

        if(alunoEncontrado.isPresent()) {
            avaliacaoFisica = new AvaliacaoFisica();
            avaliacaoFisica.setAluno(alunoEncontrado.get());
            avaliacaoFisica.setAltura(form.getAltura());
            avaliacaoFisica.setPeso(form.getPeso());
            avaliacaoFisica.calcularImc();

            avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);
        }

        return avaliacaoFisica;
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        return avaliacaoFisicaRepository.findById(id).orElse(null);
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
