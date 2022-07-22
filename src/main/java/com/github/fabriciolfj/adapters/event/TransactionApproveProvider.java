package com.github.fabriciolfj.adapters.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.entities.TransactionEntity;
import com.github.fabriciolfj.util.ConvertObjectoToJson;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransactionApproveProvider {

    @Channel("transaction-approve")
    @OnOverflow(value = OnOverflow.Strategy.BUFFER)
    private MutinyEmitter<String> producer;

    @Inject
    private ObjectMapper mapper;

    public Uni<Void> send(final TransactionEntity entity) {
        var dto = new TransactionApproveDTO(entity.transaction());
        var json = new ConvertObjectoToJson<TransactionApproveDTO>().toJson(dto);
        return producer.send(json);
    }
}
