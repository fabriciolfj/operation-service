package com.github.fabriciolfj.entrypoint.converter;

import com.github.fabriciolfj.entities.TransactionEntity;
import com.github.fabriciolfj.entities.TypeTransaction;
import com.github.fabriciolfj.entrypoint.dto.TransactionRequest;

import java.util.UUID;

public class TransactionRequestConveter {

    private TransactionRequestConveter() { }

    public static TransactionEntity toEntityDebit(final TransactionRequest request) {
        return new TransactionEntity(
                UUID.randomUUID().toString(),
                request.getAccount(),
                request.getCustomer(),
                request.getValue(),
                TypeTransaction.DEBIT);
    }

    public static TransactionEntity toEntityCredit(final TransactionRequest request) {
        return new TransactionEntity(
                UUID.randomUUID().toString(),
                request.getAccount(),
                request.getCustomer(),
                request.getValue(),
                TypeTransaction.CREDIT);
    }
}
