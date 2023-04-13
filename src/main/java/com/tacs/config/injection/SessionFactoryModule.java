package com.tacs.config.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.tacs.util.Configuration;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.h2.server.web.WebServer;
import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;

import java.sql.SQLException;

@Slf4j
public class SessionFactoryModule extends AbstractModule {

    @Provides
    @Singleton
    @Inject
    public SessionFactory providesSessionFactory(Configuration configuration) throws SQLException {
        var server = new Server(new WebServer(), "-tcp", "-tcpPort", "8082");
        server.start();
        log.info(server.getStatus());

        var standardServerRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", configuration.getString("database.url"))
                .applySetting("hibernate.connection.username", "sa")
                .applySetting("hibernate.connection.password", "")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.current_session_context_class", "thread")
                .applySetting("hibernate.hbm2ddl.auto", "create-drop")
                // Connection pool config
                .applySetting("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider")
                .applySetting("hibernate.hikari.connectionTimeout", "10000")
                .applySetting("hibernate.hikari.minimumIdle", "20")
                .applySetting("hibernate.hikari.maximumPoolSize", "300")
                .applySetting("hibernate.hikari.idleTimeout", "200000")
                .build();

        var metadataSources = new MetadataSources(standardServerRegistry);

        var entities = new Reflections("com.tacs.model").getTypesAnnotatedWith(Entity.class);

        log.info(String.format("Found entities: %s", entities.stream().map(Class::getSimpleName).toList()));

        entities.forEach(metadataSources::addAnnotatedClass);

        var metadata = metadataSources.buildMetadata();

        return metadata.buildSessionFactory();
    }

}
