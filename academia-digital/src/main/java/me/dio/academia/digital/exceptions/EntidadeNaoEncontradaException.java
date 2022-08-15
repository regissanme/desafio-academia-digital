package me.dio.academia.digital.exceptions;

/**
 * Projeto: academia-digital
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/08/2022
 * Hora: 11:18
 */
public class EntidadeNaoEncontradaException extends ErroDeNegocioException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
