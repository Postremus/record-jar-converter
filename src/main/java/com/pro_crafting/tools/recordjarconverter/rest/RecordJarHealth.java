package com.pro_crafting.tools.recordjarconverter.rest;

import com.pro_crafting.tools.recordjarconverter.RestApplication;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class RecordJarHealth implements HealthCheck {

    @Inject
    RecordJarResource resource;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named(RestApplication.VERSION_PATH + RecordJarResource.RESOURCE_PATH)
                .state(resource != null)
                .build();
    }
}
