package com.github.fabriciolfj.adapters.http.rules;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.math.BigDecimal;

@Path("/api/v1/loan")
@RegisterRestClient(configKey = "rules-api")
public interface RulesClient {

    @GET
    @Path("/{value}")
    Uni<BigDecimal> findLoan(@PathParam("value") final BigDecimal value);
}
