package com.github.fabriciolfj.business;

import io.smallrye.mutiny.Uni;

import java.math.BigDecimal;

public interface FindLoanProvider {

    Uni<BigDecimal> process(final BigDecimal value);
}
