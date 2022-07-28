package com.github.fabriciolfj.adapters.http.debit;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/api/v1/accounts/debits")
@RegisterRestClient(configKey = "debits-api")
public interface DebitClient {

    @PUT
    Uni<Void> create(final DebitRequestDTO dto);
}
