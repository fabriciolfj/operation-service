# Operation service
- Tem por objetivo as seguintes regras:
  - analise de fraude com base no codigo do cliente (enviar para service rule), fazer via http e colocar no cache
  - efetuar operação de debito e credito no serviço de conta (controle transacional)
  - solicitar limite de emprestimo (pegar a métida dos valores da transação e enviar ao rule service) user kafka
  - beneficios bonus com base na movimentacao transacional
