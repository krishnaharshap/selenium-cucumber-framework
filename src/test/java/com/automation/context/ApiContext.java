package com.automation.context;

import io.restassured.response.Response;

/**
 * Holds per-scenario API state (last response, base URL).
 *
 * Picocontainer injects one instance per scenario, so ApiSteps and any other
 * step class that needs the response can share the same object without static fields.
 */
public class ApiContext {

    private Response lastResponse;
    private String baseUri;

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public boolean hasResponse() {
        return lastResponse != null;
    }
}
