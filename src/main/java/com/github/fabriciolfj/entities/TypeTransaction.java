package com.github.fabriciolfj.entities;

import com.github.fabriciolfj.exceptions.TypeTransactionNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum TypeTransaction {
    DEBIT("debit"),
    CREDIT("credit");

    private final String describe;

    public static TypeTransaction toEnum(final String describe) {
        return Stream.of(TypeTransaction.values())
                .filter(value -> value.equals(describe))
                .findFirst()
                .orElseThrow(() -> new TypeTransactionNotFoundException());
    }
}