package com.github.fabriciolfj.entities;

import java.math.BigDecimal;

public record TransactionLoanEntity(String customer, String account, BigDecimal value){ }
