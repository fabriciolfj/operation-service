package com.github.fabriciolfj.adapters.http.rules;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Path;

@Path("/api/v1/loan")
@RegisterRestClient(configKey = "rules-api")
public interface RulesClient {
}
