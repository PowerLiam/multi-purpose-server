package com.liamnbtech.server.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.ExecutorFactory;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSAsyncClientBuilder;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.secretsmanager.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Configuration for the various AWS service clients.  Provides builder instances from which other components may
 * create the actual service clients.  Also provides a global, preconfigured instance of each client for shared use by
 * other components.
 *
 * The standard builders will use params (region, credentials, etc) specified in environment variables or
 * configuration files if present.  Otherwise, it will obtain the params from the EC2 metadata service.  See
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-region-selection.html for more information
 * about the standard provider chain for these parameters.
 */
@Configuration
public class AwsClientConfiguration {
    private static final int AWS_CLIENT_EXECUTOR_CORE_THREADS = 1;
    private static final int AWS_CLIENT_EXECUTOR_MAX_THREADS = 50;
    private static final long AWS_CLIENT_EXECUTOR_THREAD_KEEP_ALIVE_SECONDS = 60;
    private static final int AWS_CLIENT_EXECUTOR_TASKS = 10000;

    // ------------------------ Dynamo DB ------------------------

    @Bean
    public AmazonDynamoDBClientBuilder getAmazonDynamoDBClientBuilder(ClientConfiguration defaultClientConfiguration) {
        return AmazonDynamoDBClientBuilder.standard()
                .withClientConfiguration(defaultClientConfiguration);
    }

    @Bean
    public AmazonDynamoDB getAmazonDynamoDBClient(AmazonDynamoDBClientBuilder amazonDynamoDBClientBuilder) {
        return amazonDynamoDBClientBuilder.build();
    }

    @Bean
    public AmazonDynamoDBAsyncClientBuilder getAmazonDynamoDBAsyncClientBuilder(
            ClientConfiguration defaultClientConfiguration,
            ExecutorFactory awsApiClientExecutorFactory
    ) {
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withClientConfiguration(defaultClientConfiguration)
                .withExecutorFactory(awsApiClientExecutorFactory);
    }

    @Bean
    public AmazonDynamoDBAsync getAmazonDynamoDBAsyncClient(
            AmazonDynamoDBAsyncClientBuilder amazonDynamoDBClientBuilder) {
        return amazonDynamoDBClientBuilder.build();
    }


    // ------------------------ S3 ------------------------

    @Bean
    public AmazonS3ClientBuilder getAmazonS3ClientBuilder(ClientConfiguration defaultClientConfiguration) {
        return AmazonS3ClientBuilder.standard()
                .withClientConfiguration(defaultClientConfiguration);
    }

    @Bean
    public AmazonS3 getAmazons3Client(AmazonS3ClientBuilder amazonS3ClientBuilder) {
        return amazonS3ClientBuilder.build();
    }


    // ------------------------ KMS ------------------------

    @Bean
    public AWSKMSClientBuilder getAWSKMSClientBuilder() {
        return AWSKMSClient.builder();
    }

    @Bean
    public AWSKMS getAWSKMSClient(AWSKMSClientBuilder awsKmsClientBuilder) {
        return awsKmsClientBuilder.build();
    }

    @Bean
    public AWSKMSAsyncClientBuilder getAWSKMSAsyncClientBuilder(
            ClientConfiguration defaultClientConfiguration,
            ExecutorFactory awsApiClientExecutorFactory)
    {
        return AWSKMSAsyncClientBuilder.standard()
                .withClientConfiguration(defaultClientConfiguration)
                .withExecutorFactory(awsApiClientExecutorFactory);
    }

    @Bean
    public AWSKMS getAWSKMSAsyncClient(AWSKMSAsyncClientBuilder awsKmsAsyncClientBuilder) {
        return awsKmsAsyncClientBuilder.build();
    }


    // ------------------------ Secrets Manager ------------------------

    @Bean
    public AWSSecretsManagerClientBuilder getAWSSecretsManagerClientBuilder(
            ClientConfiguration defaultClientConfiguration)
    {
        return AWSSecretsManagerClient.builder()
                .withClientConfiguration(defaultClientConfiguration);
    }

    @Bean
    public AWSSecretsManager getAWSSecretsManager(AWSSecretsManagerClientBuilder awsSecretsManagerClientBuilder) {
        return awsSecretsManagerClientBuilder.build();
    }

    @Bean
    public AWSSecretsManagerAsyncClientBuilder getAWSSecretsManagerAsyncClientBuilder(
            ClientConfiguration defaultClientConfiguration,
            ExecutorFactory awsApiClientExecutorFactory)
    {
        return AWSSecretsManagerAsyncClient.asyncBuilder()
                .withClientConfiguration(defaultClientConfiguration)
                .withExecutorFactory(awsApiClientExecutorFactory);
    }

    @Bean
    public AWSSecretsManagerAsync getAWSAsyncSecretsManager(
            AWSSecretsManagerAsyncClientBuilder awsSecretsManagerAsyncClientBuilder)
    {
        return awsSecretsManagerAsyncClientBuilder.build();
    }


    // ------------------------ SHARED ------------------------

    @Bean
    ClientConfiguration getDefaultClientConfiguration() {
        return new ClientConfiguration();
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
