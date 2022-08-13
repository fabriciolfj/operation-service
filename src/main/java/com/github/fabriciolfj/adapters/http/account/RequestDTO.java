package com.github.fabriciolfj.adapters.http.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {

    @JsonProperty("code_conta")
    private String codeConta;
    private BigDecimal value;
    private String transaction;
}
