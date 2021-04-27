package com.liamnbtech.server.cli;

import org.apache.commons.cli.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class ArgumentParsingServiceImpl implements ArgumentParsingService
{

    public CommandLine parseArgs(ApplicationArguments args, Options options) throws ParseException {
        CommandLineParser parser = new DefaultParser();

        return parser.parse(options, args.getSourceArgs());
    }
}
