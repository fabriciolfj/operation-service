package com.github.fabriciolfj.adapters.http.account;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/api/v1/accounts")
@RegisterRestClient(configKey = "account-api")
public interface AccountClient {

    @PUT
    @Path("/debits")
    Uni<Void> createDebit(final RequestDTO dto);

    @PUT
    @Path("/credits")
    Uni<Void> createCredit(final RequestDTO dto);
}
