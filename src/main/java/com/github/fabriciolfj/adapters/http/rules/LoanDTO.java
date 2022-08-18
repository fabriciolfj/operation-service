package com.github.fabriciolfj.adapters.http.rules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private BigDecimal value;
}
