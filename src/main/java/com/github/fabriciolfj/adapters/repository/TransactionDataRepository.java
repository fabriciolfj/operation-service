package com.github.fabriciolfj.adapters.repository;

import com.github.fabriciolfj.adapters.repository.converter.TransactionDataConverter;
import com.github.fabriciolfj.adapters.repository.data.TransactionData;
import com.github.fabriciolfj.business.SaveTransactionProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class TransactionDataRepository implements SaveTransactionProvider {

    @Override
    public Uni<String> execute(final TransactionEntity entity) {
        return Uni.createFrom().item(entity)
                .onItem().transform(TransactionDataConverter::toData)
                .onItem().transformToUni(data -> Panache.withTransaction(() -> data.persist()))
                .invoke(s -> log.info("Transaction persisted: {}", entity.transaction()))
                .onItem().transform(s -> {
                    var result = (TransactionData) s;
                    return result.getTransaction();
                });

    }
}
