package com.github.fabriciolfj.adapters.http.rules;

import com.github.fabriciolfj.business.FindLoanProvider;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

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
                        }))
                .onFailure().recoverWithUni(Uni.createFrom().nullItem());
    }

}