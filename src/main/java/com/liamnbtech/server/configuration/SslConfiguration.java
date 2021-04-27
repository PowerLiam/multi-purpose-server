package com.liamnbtech.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Class representing the SSL configuration of the entire server.  Defines classes needed to create connections via
 * SSL, and provides them to Spring as managed Beans.
 */
@Configuration
public class SslConfiguration {

    private static final String SSL_CONTEXT_INSTANCE_NAME = "SSL";

    /**
     * Creates and returns the SSLContext for the application, to be made available by Spring for other components.
     *
     * @return the SSLContext for the application to be made available by Spring for other components
     *
     * @throws NoSuchAlgorithmException If the SSLContext could not be obtained, created, or configured
     */
    @Bean
    public SSLContext sslContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance(SSL_CONTEXT_INSTANCE_NAME);
        sslContext.init(null, null, null);

        return sslContext;
    }

    @Bean
    public SSLSocketFactory sslSocketFactory(SSLContext sslContext) {
        return sslContext.getSocketFactory();
    }

    @Bean
    public SSLServerSocketFactory sslServerSocketFactory(SSLContext sslContext) {
        return sslContext.getServerSocketFactory();
    }
}
