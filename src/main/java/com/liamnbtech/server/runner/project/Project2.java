package com.liamnbtech.server.runner.project;

import org.apache.commons.cli.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The executable code for Project 2.
 */
@Component("Project2")
public class Project2 implements Cs3700Project {

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        CommandLine commandLine = parseArgs(args.getSourceArgs());
        List<String> positionalArguments = commandLine.getArgList();

        // Exit the entire application after successfully outputting the result.
        int exitCode = SpringApplication.exit(applicationContext, () -> 0);
        System.exit(exitCode);
    }

    /**
     * Parses the raw command line arguments.
     *
     * @param args The raw command line arguments
     *
     * @return The parsed command line arguments
     *
     * @throws ParseException If the command line arguments could not be parsed
     */
    private CommandLine parseArgs(String[] args) throws ParseException {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        return parser.parse( options, args );
    }

    /**
     * Constructs an instance of Project2 with the injected services
     *
     * @param applicationContext The injected application context
     */
    public Project2(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
