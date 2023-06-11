package br.com.competeaqui.pagseguro.service.response;

import lombok.NonNull;

/**
 * @author Manoel Campos da Silva Filho
 * @see QrCodeResponse
 */
public record Link(@NonNull String rel, @NonNull String href, @NonNull String media, @NonNull String type) {
}
