package com.liamnbtech.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Class representing the SSL configuration of the entire server.  Defines classes needed to create connections via
 * SSL, and provides them to Spring as managed Beans.
 */
@Configuration
public class SSLConfiguration {

    /**
     * Creates and returns the SSLSocketFactory for the application to be made available by Spring for other components.
     *
     * @return the SSLSocketFactory for the application to be made available by Spring for other components
     *
     * @throws NoSuchAlgorithmException If the SSLSocketFactory could not be obtained, created, or configured
     */
    @Bean
    public SSLSocketFactory sslSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        return sslContext.getSocketFactory();
    }
}
