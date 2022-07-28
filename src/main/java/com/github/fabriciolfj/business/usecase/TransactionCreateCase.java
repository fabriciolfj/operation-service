package com.github.fabriciolfj.business.usecase;

import com.github.fabriciolfj.business.SendTransactionDebitProvider;
import com.github.fabriciolfj.business.SaveTransactionProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class TransactionCreateCase {

    private final SaveTransactionProvider saveTransaction;
    private final SendTransactionDebitProvider envioTransaction;

    public Uni<String> execute(final TransactionEntity entity) {
        return envioTransaction.execute(entity)
                .onItem()
                .transformToUni(e -> saveTransaction.execute(entity));
    }
}
