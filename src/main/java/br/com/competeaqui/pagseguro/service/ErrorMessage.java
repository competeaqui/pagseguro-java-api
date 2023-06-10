package br.com.competeaqui.pagseguro.service;

/**
 * Mensagem de erro enviada como resposta de uma requisição
 * @author Manoel Campos da Silva Filho
 * @see RequestErrors
 */
public record ErrorMessage (String code, String description){

}
