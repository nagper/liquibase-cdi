package com.example;

//import com.module.ModuleClass;
//import com.lhsystems.iocc.security.auth.AuthenticationException;
//import com.lhsystems.iocc.service.common.configuration.AbstractEjbDynamicMBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import liquibase.osgi.Activator;

@Startup
@Singleton
public class Qwe {


    liquibase.resource.ResourceAccessor ar;

    @PostConstruct // This method will be called after the bean is constructed and dependencies are injected
    public void init() {
        System.out.println("Hello from ApplicationStartupBean! The application is deployed.");
        // You can add more complex startup logic here, e.g.,
        // - Initializing caches
        // - Loading configuration
        // - Performing database checks
    }
}
