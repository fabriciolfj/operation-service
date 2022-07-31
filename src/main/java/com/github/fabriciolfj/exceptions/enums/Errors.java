package com.github.fabriciolfj.exceptions.enums;

import java.util.ResourceBundle;

public enum Errors {

    ERROR_01,
    ERROR_02;

    public String toMessage() {
        var bundle = ResourceBundle.getBundle("messages/exceptions");
        return bundle.getString(this.name() + ".message");
    }
}
