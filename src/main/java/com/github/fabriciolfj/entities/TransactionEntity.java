package com.github.fabriciolfj.entities;

import java.math.BigDecimal;

public record TransactionEntity(String transaction, String account, String customer, BigDecimal value, TypeTransaction type) { }
