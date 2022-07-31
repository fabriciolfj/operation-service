package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.business.usecase.TransactionCreateCase;
import com.github.fabriciolfj.business.usecase.TransactionUpdateCase;
import com.github.fabriciolfj.entrypoint.converter.TransactionRequestConveter;
import com.github.fabriciolfj.entrypoint.dto.TransactionRequest;
import com.github.fabriciolfj.entrypoint.dto.TransactionResponse;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCreateCase transactionCreateCase;
    private final TransactionUpdateCase transactionUpdateCase;

    @POST
    @Path("/debit")
    public Uni<Response> createDebit(@Valid final TransactionRequest request) {
        return transactionCreateCase.execute(TransactionRequestConveter.toEntityDebit(request))
                .onItem()
                .ifNull().failWith(new WebApplicationException("Fail save transaction", Response.Status.BAD_REQUEST))
                .onItem().ifNotNull().transform(c -> Response.accepted().entity(TransactionResponse.builder().transaction(c).build()).build())
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updateTransaction(@PathParam("id") final String id) {
        return transactionUpdateCase.execute(id)
                .onItem().transform(t -> Response.accepted().build())
                .onFailure().recoverWithItem(t -> Response.status(Response.Status.BAD_REQUEST).entity(t.getMessage()).build());
    }
}
