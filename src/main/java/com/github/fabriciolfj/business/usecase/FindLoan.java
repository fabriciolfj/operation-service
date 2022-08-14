package com.github.fabriciolfj.business.usecase;

import com.github.fabriciolfj.business.FindLoanProvider;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindLoan {

    private final FindLoanProvider provider;

    public Uni<BigDecimal> execute(final BigDecimal bigDecimal) {
        return Uni.createFrom().item(bigDecimal)
                .onItem()
                .invoke(b -> log.info("Value processing loan {}", b))
                .onItem()
                .transformToUni(b -> provider.process(b));
    }
}
