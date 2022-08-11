package me.dio.academia.digital.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_alunos")
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return Objects.equals(id, aluno.id) && Objects.equals(cpf, aluno.cpf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpf);
  }

  @Override
  public String toString() {
    return "Aluno{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", bairro='" + bairro + '\'' +
            ", dataDeNascimento=" + dataDeNascimento +
            ", avaliacoes=" + avaliacoes +
            '}';
  }
}
