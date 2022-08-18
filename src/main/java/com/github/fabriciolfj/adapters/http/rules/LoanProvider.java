package com.github.fabriciolfj.adapters.http.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.business.FindLoanProvider;
import com.github.fabriciolfj.entrypoint.dto.LoanResponse;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

@ApplicationScoped
@Slf4j
public class LoanProvider implements FindLoanProvider {

    @RestClient
    private RulesClient client;

    @Inject
    private LoanCache loanCache;

    @Override
    public Uni<BigDecimal> process(final BigDecimal value) {
        return loanCache.getValue(value)
                .onFailure()
                .recoverWithUni(client.findLoan(value)
                        .onItem().transformToUni(c -> {
                            var loanValue = c.getValue();
                            log.info("Value loan: {}", loanValue);
                            return loanCache.setValue(value, loanValue);
                        }));
    }

}
