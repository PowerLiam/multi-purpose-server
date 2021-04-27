package com.liamnbtech.server.aws.metrics;

import com.amazonaws.metrics.internal.cloudwatch.RequestMetricCollectorSupport;

import java.util.concurrent.LinkedBlockingQueue;

public class AwsRequestMetricsCollector extends RequestMetricCollectorSupport {
    public static final int DEFAULT_METRICS_QUEUE_SIZE = 10000;

    public AwsRequestMetricsCollector() {
        super(new LinkedBlockingQueue<>(DEFAULT_METRICS_QUEUE_SIZE));
    }
}
