package com.github.fabriciolfj.business.usecase;

import com.github.fabriciolfj.business.FindLoanProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import com.github.fabriciolfj.entities.TypeTransaction;
import com.github.fabriciolfj.exceptions.LoanException;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.fabriciolfj.entities.TransactionLoanEntity;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindLoan {

    private final FindLoanProvider provider;
    private final TransactionCreateCase createCase;

    public Uni<String> execute(final TransactionLoanEntity entity) {
        return Uni.createFrom().item(entity)
                .onItem()
                .invoke(b -> log.info("Value processing loan {}", b))
                .onItem()
                .transformToUni(b -> provider.process(b.value()))
                .onItem().ifNull().failWith(new LoanException())
                .onItem()
                .transformToUni(c -> createCase.execute(
                        new TransactionEntity(UUID.randomUUID().toString(), entity.account(), entity.customer(), c, TypeTransaction.CREDIT)));
    }
}
