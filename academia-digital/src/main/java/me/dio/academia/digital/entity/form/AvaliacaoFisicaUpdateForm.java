package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoFisicaUpdateForm {

    @NotNull(message = "Preencha o campo corretamente.")
    @Positive(message = "${validatedValue} precisa ser positivo.")
    private double peso;

    @NotNull(message = "Preencha o campo corretamente.")
    @Positive(message = "${validatedValue} precisa ser positivo.")
    @DecimalMin(value = "1.40", message = "${validatedValue} precisa se no mínimo {value}.")
    private double altura;
}
