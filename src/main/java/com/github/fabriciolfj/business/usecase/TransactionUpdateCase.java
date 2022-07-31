package com.github.fabriciolfj.business.usecase;

import com.github.fabriciolfj.business.FindTransaction;
import com.github.fabriciolfj.business.SendUpdateTransaction;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class TransactionUpdateCase {

    private final SendUpdateTransaction sendUpdateTransaction;
    private final FindTransaction findTransaction;

    public Uni<Void> execute(final String transaction) {
        return findTransaction.processing(transaction)
                .invoke(t -> log.info("Transaction : {}", t))
                .onItem()
                .transformToUni(t -> sendUpdateTransaction.send(t))
                .invoke(() -> log.info("Transaction send update"));

    }
}
