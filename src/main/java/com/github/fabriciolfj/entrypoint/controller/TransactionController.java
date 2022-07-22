package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.business.usecase.TransactionCreateCase;
import com.github.fabriciolfj.entrypoint.converter.TransactionRequestConveter;
import com.github.fabriciolfj.entrypoint.dto.TransactionRequest;
import com.github.fabriciolfj.entrypoint.dto.TransactionResponse;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCreateCase transactionCreateCase;

    @POST
    @Path("/debit")
    public Uni<Response> createDebit(final TransactionRequest request) {
        return transactionCreateCase.execute(TransactionRequestConveter.toEntityDebit(request))
                .onItem()
                .ifNull().failWith(new WebApplicationException("Fail save transaction", Response.Status.BAD_REQUEST))
                .onItem().ifNotNull().transform(c -> Response.accepted().entity(TransactionResponse.builder().transaction(c).build()).build())
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
    }
}
