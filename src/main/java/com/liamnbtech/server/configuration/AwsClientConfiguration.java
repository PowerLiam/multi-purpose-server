package com.liamnbtech.server.configuration;

import com.amazonaws.client.builder.ExecutorFactory;
import com.amazonaws.services.dynamodbv2.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Configuration for the various AWS service clients.  Provides builder instances from which other components may
 * create the actual service clients.
 *
 * The standard builders will use params (region, credentials, etc) specified in environment variables or
 * configuration files if present.  Otherwise, it will obtain the params from the EC2 metadata service.  See
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-region-selection.html for more information
 * about the standard provider chain for these parameters.
 */
@Configuration
public class AwsClientConfiguration {
    private static final int AWS_CLIENT_EXECUTOR_CORE_THREADS = 10;
    private static final int AWS_CLIENT_EXECUTOR_MAX_THREADS = 50;
    private static final long AWS_CLIENT_EXECUTOR_THREAD_KEEP_ALIVE_SECONDS = 60;
    private static final int AWS_CLIENT_EXECUTOR_TASKS = 1000;

    @Bean
    public AmazonDynamoDBClientBuilder getAmazonDynamoDBClientBuilder() {
        return AmazonDynamoDBClientBuilder.standard();
    }

    @Bean
    public AmazonDynamoDB getAmazonDynamoDBClient(AmazonDynamoDBClientBuilder amazonDynamoDBClientBuilder) {
        return amazonDynamoDBClientBuilder.build();
    }

    @Bean
    public AmazonDynamoDBAsyncClientBuilder getAmazonDynamoDBAsyncClientBuilder(ExecutorFactory executorFactory) {
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withExecutorFactory(executorFactory);
    }

    @Bean
    public AmazonDynamoDBAsync getAmazonDynamoDBAsyncClient(
            AmazonDynamoDBAsyncClientBuilder amazonDynamoDBClientBuilder
    ) {
        return amazonDynamoDBClientBuilder.build();
    }

    @Bean
    public ExecutorFactory getAwsApiClientExecutorFactory() {
        return () -> new ThreadPoolExecutor(
                AWS_CLIENT_EXECUTOR_CORE_THREADS,
                AWS_CLIENT_EXECUTOR_MAX_THREADS,
                AWS_CLIENT_EXECUTOR_THREAD_KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(AWS_CLIENT_EXECUTOR_TASKS));
    }
}
