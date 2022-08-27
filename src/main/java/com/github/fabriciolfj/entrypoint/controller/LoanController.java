package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.business.usecase.FindLoan;
import com.github.fabriciolfj.entrypoint.converter.TransactionLoanRequestConverter;
import com.github.fabriciolfj.entrypoint.dto.TransactionLoanRequest;
import com.github.fabriciolfj.entrypoint.dto.TransactionResponse;
import com.github.fabriciolfj.exceptions.model.ErrorResponse;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/v1/loan")
@RequiredArgsConstructor
@Consumes("application/json")
@Produces("application/json")
public class LoanController {

    private final FindLoan findLoan;

    @POST
    public Uni<Response> findLoan(@Valid final TransactionLoanRequest request) {
        return findLoan.execute(TransactionLoanRequestConverter.toEntity(request))
                .onItem()
                .transform(c -> Response
                        .status(Response.Status.ACCEPTED)
                        .entity(TransactionResponse.builder().transaction(c).build())
                        .build())
                .onFailure()
                .recoverWithItem(e -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(ErrorResponse.builder().message(e.getMessage()).build())
                        .build());
    }
}
