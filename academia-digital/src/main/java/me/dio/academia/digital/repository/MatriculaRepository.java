package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Query nativa == Mesma forma do SGBD
    @Query(value = "SELECT * FROM tb_matriculas m "
            + "INNER JOIN tb_alunos a ON m.aluno_id = a.id "
            + "WHERE a.bairro = :bairro", nativeQuery = true)
    List<Matricula> findAlunosMatriculadosPorBairroSGBD(String bairro);

    // HQL
    @Query("FROM Matricula m WHERE  m.aluno.bairro = :bairro ")
    // Pode ser o nome que desejar
    List<Matricula> findAlunosMatriculadosPorBairro(String bairro);

    // O nome tem que seguir o padrão do JPA
    List<Matricula> findByAlunoBairro(String bairro);
}
