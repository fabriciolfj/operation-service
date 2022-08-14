package com.github.fabriciolfj.adapters.http.rules;

import com.github.fabriciolfj.business.FindLoanProvider;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
@Slf4j
public class LoanProvider implements FindLoanProvider {

    @RestClient
    private RulesClient client;

    @Override
    public Uni<BigDecimal> process(final BigDecimal value) {
        return client.findLoan(value)
                .onItem()
                .invoke(c -> log.info("Return : {}", c))
                .onItem()
                .transform(c -> c.getValue());
    }
}
