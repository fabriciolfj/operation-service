package com.github.fabriciolfj.adapters.repository.data;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionData extends PanacheEntity {

    @Column(name = "transaction", nullable = false)
    private String transaction;
    @Column(name = "account", nullable = false)
    private String account;
    @Column(name = "customer", nullable = false)
    private String customer;
    @Column(name = "value", nullable = false)
    private BigDecimal value;
    @Column(name = "dateTime")
    private LocalDateTime dateTime;
    private String type;
}
