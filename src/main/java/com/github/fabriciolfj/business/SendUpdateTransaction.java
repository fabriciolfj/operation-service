package com.github.fabriciolfj.business;

import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;

public interface SendUpdateTransaction {

    Uni<Void> send(final TransactionEntity entity);
}
