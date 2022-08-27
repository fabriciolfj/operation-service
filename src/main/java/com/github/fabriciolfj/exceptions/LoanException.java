package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;

public class LoanException extends BusinessException {

    public LoanException() {
        super(Errors.ERROR_03.toMessage());
    }
}
