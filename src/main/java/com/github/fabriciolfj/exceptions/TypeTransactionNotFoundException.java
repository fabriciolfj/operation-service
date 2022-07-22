package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;

public class TypeTransactionNotFoundException extends BusinessException {

    public TypeTransactionNotFoundException() {
        super(Errors.ERROR_01.toMessage());
    }
}
