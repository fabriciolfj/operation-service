package com.github.fabriciolfj.adapters.repository.converter;

import com.github.fabriciolfj.adapters.repository.data.TransactionData;
import com.github.fabriciolfj.entities.TransactionEntity;
import com.github.fabriciolfj.entities.TypeTransaction;

import java.time.LocalDateTime;

public class TransactionDataConverter {

    private TransactionDataConverter() {

    }

    public static TransactionEntity toEntity(final TransactionData data) {
        return new TransactionEntity(
                data.getTransaction(),
                data.getAccount(),
                data.getCustomer(),
                data.getValue(),
                TypeTransaction.toEnum(data.getType()));
    }

    public static TransactionData toData(final TransactionEntity entity) {
        return TransactionData.builder()
                .transaction(entity.transaction())
                .account(entity.account())
                .customer(entity.customer())
                .dateTime(LocalDateTime.now())
                .type(entity.type().getDescribe())
                .value(entity.value())
                .build();
    }
}
