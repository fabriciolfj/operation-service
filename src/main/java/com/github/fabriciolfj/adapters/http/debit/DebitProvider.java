package com.github.fabriciolfj.adapters.http.debit;

import com.github.fabriciolfj.business.SendTransactionDebitProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DebitProvider implements SendTransactionDebitProvider {

    @RestClient
    private DebitClient client;

    @Override
    public Uni<Void> execute(final TransactionEntity entity) {
        return client.create(DebitRequestConverter.toDTO(entity));
    }
}
