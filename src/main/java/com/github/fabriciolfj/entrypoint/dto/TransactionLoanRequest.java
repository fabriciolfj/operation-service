package com.github.fabriciolfj.entrypoint.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLoanRequest {

    @NotEmpty(message = "Numero da conta nao informado")
    private String account;
    @NotEmpty(message = "Cliente nao informado")
    private String customer;
    @NotNull(message = "Valor nao informado")
    private BigDecimal value;
}
