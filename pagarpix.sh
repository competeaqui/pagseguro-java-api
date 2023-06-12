#!/bin/bash

echo "Simula o pagamento de um QRCode PIX, fazendo a API do PagSeguro notificar a URL de retorno que você indicou na requisição de criação do QRCode"
# https://dev.pagseguro.uol.com.br/reference/simular-o-pagamento-de-um-qr-code-pix-em-sandbox

source .env

if [[ $# -lt 1 ]]; then
  echo "Passe o QRCode ID retornado pela requisição de criação do PIX como parâmetro"
  exit
fi

QRCO_ID=$1

curl -v --location --request POST "https://sandbox.api.pagseguro.com/pix/pay/$QRCO_ID" \
--header "Content-Type: application/json" \
--header "Authorization: Bearer $PAYMENT_SERVICE_TOKEN"