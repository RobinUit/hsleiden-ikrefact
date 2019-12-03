package com.DigitaleFactuur;

import io.dropwizard.Application;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.*;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class AuthIntegrationTest {
    private static final String CONFIG_PATH
            = ResourceHelpers.resourceFilePath("test-config.yml");

    @ClassRule
    public static final DropwizardAppRule<KilometerRegistratieConfiguration> RULE
            = new DropwizardAppRule<>(KilometerRegistratieApplication.class,
                CONFIG_PATH);

    private static final HttpAuthenticationFeature FEATURE
            = HttpAuthenticationFeature.basic("udemy", "p@ssw0rd");

    private static final String TARGET =
            "https://localhost:8443";

    private static final String PATH
            = "/hello/secured";

    private static final String TRUST_STORE_FILE_NAME
            = "dropbookmarks.keystore";

    private static final String TRUST_STORE_PASSWORD
            = "p@ssw0rd";

    private Client client;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Application<KilometerRegistratieConfiguration> application
                =RULE.getApplication();
        application.run("db", "migrate", "-i DEV", CONFIG_PATH);
    }

    @Before
    public void setUp(){
        SslConfigurator configurator
                =SslConfigurator.newInstance();
        configurator.trustStoreFile(TRUST_STORE_FILE_NAME)
                .trustStorePassword(TRUST_STORE_PASSWORD);
        SSLContext context = configurator.createSSLContext();
        client = ClientBuilder.newBuilder()
                .sslContext(context)
                .build();
    }

    @After
    public void tearDown(){
        client.close();
    }

    @Test
    public void testSadPath(){
        Response response = client
                .target(TARGET)
                .path(PATH)
                .request()
                .get();

        assertEquals(Response.Status
                .UNAUTHORIZED.getStatusCode(),
                response.getStatus());
    }

    @Test
    public void testHappyPath(){
        String expected = "Hello secured world";
        client.register(FEATURE);
        String actual = client
                .target(TARGET)
                .path(PATH)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        assertEquals(expected, actual);
    }

}
