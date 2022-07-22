package com.github.fabriciolfj.business.usecase;

import com.github.fabriciolfj.business.SaveTransactionProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Executors;

@ApplicationScoped
@RequiredArgsConstructor
public class TransactionCreateCase {

    private final SaveTransactionProvider saveTransaction;

    public Uni<String> execute(final TransactionEntity entity) {
        return saveTransaction.execute(entity);
                //.emitOn(Executors.newSingleThreadExecutor());
    }
}
