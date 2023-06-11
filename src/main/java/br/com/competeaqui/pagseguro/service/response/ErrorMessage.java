package br.com.competeaqui.pagseguro.service.response;

/**
 * Mensagem de erro enviada como resposta de uma requisição
 * @author Manoel Campos da Silva Filho
 * @see ResponseError
 */
public record ErrorMessage (String code, String description, String parameter_name){

}
