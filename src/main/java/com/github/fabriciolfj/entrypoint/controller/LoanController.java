package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.business.usecase.FindLoan;
import com.github.fabriciolfj.entrypoint.dto.LoanResponse;
import com.github.fabriciolfj.exceptions.model.ErrorResponse;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/api/v1/loan")
@RequiredArgsConstructor
@Consumes("application/json")
@Produces("application/json")
public class LoanController {

    private final FindLoan findLoan;

    @Path("{value}")
    @GET
    public Uni<Response> findLoan(@PathParam("value") @Valid final BigDecimal value) {
        return findLoan.execute(value)
                .onItem()
                .transform(c -> Response
                        .status(Response.Status.ACCEPTED)
                        .entity(LoanResponse.builder().value(c).build())
                        .build())
                .onFailure()
                .recoverWithItem(e -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(ErrorResponse.builder().message(e.getMessage()).build())
                        .build());
    }
}
