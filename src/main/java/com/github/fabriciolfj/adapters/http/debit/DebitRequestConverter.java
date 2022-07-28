package com.github.fabriciolfj.adapters.http.debit;

import com.github.fabriciolfj.entities.TransactionEntity;

public class DebitRequestConverter {

    private DebitRequestConverter() {

    }

    public static DebitRequestDTO toDTO(final TransactionEntity transactionEntity) {
        return DebitRequestDTO.builder()
                .codeConta(transactionEntity.account())
                .transaction(transactionEntity.transaction())
                .value(transactionEntity.value())
                .build();
    }
}
