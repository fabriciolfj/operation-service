package com.github.fabriciolfj.adapters.http.account;

import com.github.fabriciolfj.business.SendTransactionDebitProvider;
import com.github.fabriciolfj.entities.TransactionEntity;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountProvider implements SendTransactionDebitProvider {

    @RestClient
    private AccountClient client;

    @Override
    public Uni<Void> execute(final TransactionEntity entity) {
        return switch (entity.type()) {
            case CREDIT -> client.createCredit(RequestConverter.toDTO(entity));
            case DEBIT -> client.createDebit(RequestConverter.toDTO(entity));
        };
    }
}
