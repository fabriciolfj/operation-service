package com.github.fabriciolfj.adapters.repository.converter;

import com.github.fabriciolfj.adapters.repository.data.TransactionData;
import com.github.fabriciolfj.entities.TransactionEntity;

public class TransactionDataConverter {

    private TransactionDataConverter() {

    }

    public static TransactionData toData(final TransactionEntity entity) {
        return TransactionData.builder()
                .transaction(entity.transaction())
                .account(entity.account())
                .customer(entity.customer())
                .type(entity.type().getDescribe())
                .value(entity.value())
                .build();
    }
}
