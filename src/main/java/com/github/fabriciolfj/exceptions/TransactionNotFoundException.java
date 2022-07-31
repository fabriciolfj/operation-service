package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;

public class TransactionNotFoundException extends BusinessException {

    public TransactionNotFoundException() {
        super(Errors.ERROR_01.toMessage());
    }
}
