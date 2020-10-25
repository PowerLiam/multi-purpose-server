package com.liamnbtech.server.service.arguments;

import org.apache.commons.cli.*;
import org.springframework.boot.ApplicationArguments;

public interface ArgumentParsingService {

    CommandLine parseArgs(ApplicationArguments args, Options options) throws ParseException;
}
