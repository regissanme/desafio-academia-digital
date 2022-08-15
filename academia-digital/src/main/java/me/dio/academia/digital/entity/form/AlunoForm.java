package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoForm {

  @NotEmpty(message = "Preencha o campo corretamente.")
  @Size(min = 2, max = 60, message = "'${validateValue}' precisa estar entre {min} e {max} caracteres.")
  private String nome;

  @NotEmpty(message = "Preencha o campo corretamente.")
//  @CPF(message = "O CPF '${validatedValue}' é inválido.")
  private String cpf;

  @NotEmpty(message = "Preencha o campo corretamente.")
  @Size(min = 3, max = 60, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String bairro;

  @NotNull(message = "Preencha o campo corretamente.")
  @Past(message = "Data '${validatedValue}' é inválid.")
  private LocalDate dataDeNascimento;
}
