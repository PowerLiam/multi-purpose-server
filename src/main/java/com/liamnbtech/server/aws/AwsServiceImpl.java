package com.liamnbtech.server.aws;

import com.amazonaws.metrics.AwsSdkMetrics;
import org.springframework.stereotype.Service;

@Service
public class AwsServiceImpl implements AwsService {

    public AwsServiceImpl() {
        AwsSdkMetrics.enableDefaultMetrics();
    }
}
