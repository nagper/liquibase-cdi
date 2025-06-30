package com.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
@Startup
@Dependent
public class LiquibaseCdiIntegration {

    private static final Logger logger = LoggerFactory.getLogger(LiquibaseCdiIntegration.class);

//    @Resource(mappedName = "java:/iocc/mrs/jdbc/MRSDS")
//    protected DataSource ds;

    @PostConstruct
    public void init() {
        logger.info("LiquibaseCdiIntegration bean initialized");
        try {
            // Force Liquibase to run by requesting the config
            CDILiquibaseConfig config = createConfig();
            logger.info("Liquibase config created: {}", config);
        } catch (Exception e) {
            logger.error("Failed to initialize Liquibase", e);
        }
    }

    @Produces
    @LiquibaseType
    public CDILiquibaseConfig createConfig() {
        logger.info("Creating Liquibase config");
        CDILiquibaseConfig config = new CDILiquibaseConfig();
        config.setChangeLog("database/liquibase/mrs2.xml");
        logger.info("Liquibase changelog set to: {}", config.getChangeLog());
        return config;
    }

    @Produces
    @LiquibaseType
    public DataSource createDataSource() throws SQLException {
        return null;
    }

    @Produces
    @LiquibaseType
    public ResourceAccessor create() {
        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }

}