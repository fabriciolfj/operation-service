package com.github.fabriciolfj.adapters.http.account;

import com.github.fabriciolfj.entities.TransactionEntity;

public class RequestConverter {

    private RequestConverter() {

    }

    public static RequestDTO toDTO(final TransactionEntity transactionEntity) {
        return RequestDTO.builder()
                .codeConta(transactionEntity.account())
                .transaction(transactionEntity.transaction())
                .value(transactionEntity.value())
                .build();
    }
}
