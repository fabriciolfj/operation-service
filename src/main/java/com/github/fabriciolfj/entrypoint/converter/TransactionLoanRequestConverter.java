package com.github.fabriciolfj.entrypoint.converter;

import com.github.fabriciolfj.entities.TransactionLoanEntity;
import com.github.fabriciolfj.entrypoint.dto.TransactionLoanRequest;

public class TransactionLoanRequestConverter {

    private TransactionLoanRequestConverter() { }

    public static TransactionLoanEntity toEntity(final TransactionLoanRequest request) {
        return new TransactionLoanEntity(
                request.getCustomer(),
                request.getAccount(),
                request.getValue());
    }
}
