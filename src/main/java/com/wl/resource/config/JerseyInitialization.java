package com.wl.resource.config;



import org.glassfish.jersey.server.ResourceConfig;

public class JerseyInitialization extends ResourceConfig {
    
    public JerseyInitialization(){
        this.packages("com.wl.resource");
    }
}