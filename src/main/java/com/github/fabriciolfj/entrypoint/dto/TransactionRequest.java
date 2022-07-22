package com.github.fabriciolfj.entrypoint.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String account;
    private String customer;
    private BigDecimal value;
}
