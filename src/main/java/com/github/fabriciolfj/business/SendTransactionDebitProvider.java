package com.github.fabriciolfj.business;

import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;

public interface SendTransactionDebitProvider {

    Uni<Void> execute(final TransactionEntity entity);
}
