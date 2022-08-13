package com.github.fabriciolfj.adapters.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.business.SendUpdateTransaction;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class TransactionApproveProvider implements SendUpdateTransaction {

    @Channel("transaction-approve")
    @Inject
    @OnOverflow(value = OnOverflow.Strategy.BUFFER)
    private MutinyEmitter<TransactionApproveDTO> producer;

    @Inject
    private ObjectMapper mapper;

    @Override
    public Uni<Void> send(final TransactionEntity entity) {
        return Uni.createFrom()
                .item(entity)
                .onItem().transform(value -> new TransactionApproveDTO(value.transaction()))
                .onItem().transformToUni(json -> producer.send(json))
                .invoke(() -> log.info("Producer success message"));
    }
}
