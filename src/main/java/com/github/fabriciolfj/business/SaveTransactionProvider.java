package com.github.fabriciolfj.business;

import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;

public interface SaveTransactionProvider {

    Uni<String> execute(final TransactionEntity entity);
}
