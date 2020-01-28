package com.DigitaleFactuur;

import com.DigitaleFactuur.auth.UserAuthenticator;
import com.DigitaleFactuur.auth.UserAuthorizer;
import com.DigitaleFactuur.db.*;
import com.DigitaleFactuur.models.*;
import com.DigitaleFactuur.resources.*;
import com.DigitaleFactuur.services.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class KilometerRegistratieApplication extends Application<KilometerRegistratieConfiguration> {
    private final HibernateBundle<KilometerRegistratieConfiguration> hibernateBundle
            = new HibernateBundle<KilometerRegistratieConfiguration>(User.class,
            Car.class, Project.class, Client.class, Declaration.class
            ) {
        @Override
        public DataSourceFactory getDataSourceFactory(KilometerRegistratieConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new KilometerRegistratieApplication().run(args);
    }

    @Override
    public String getName() {
        return "KilometerRegistratie API";
    }

    @Override
    public void initialize(final Bootstrap<KilometerRegistratieConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<KilometerRegistratieConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(KilometerRegistratieConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final KilometerRegistratieConfiguration configuration,
                    final Environment environment) {

        final UserDAO userDAO
                = new UserDAO(hibernateBundle.getSessionFactory());
        final CarDAO carDAO
                = new CarDAO(hibernateBundle.getSessionFactory());
        final ClientDAO clientDAO
                = new ClientDAO(hibernateBundle.getSessionFactory());
        final DeclarationDAO declarationDAO
                = new DeclarationDAO(hibernateBundle.getSessionFactory());
        final ProjectDAO projectDAO
                = new ProjectDAO(hibernateBundle.getSessionFactory());


        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new UserAuthenticator(userDAO))
                .setAuthorizer(new UserAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        environment.jersey().register(
                new HelloResource()
        );
        environment.jersey().register(
                new CarResource(new CarService(carDAO))
        );
        environment.jersey().register(
                new ClientResource(new ClientService(clientDAO))
        );
        environment.jersey().register(
                new DeclarationResource(new DeclarationService(declarationDAO))
        );
        environment.jersey().register(
                new UserResource(new UserService(userDAO))
        );
        environment.jersey().register(
                new ProjectResource(new ProjectService(projectDAO))
        );


    }

}
