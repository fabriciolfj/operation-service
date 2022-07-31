package com.github.fabriciolfj.business;

import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;

public interface FindTransaction {

    Uni<TransactionEntity> processing(final String transaction);
}
