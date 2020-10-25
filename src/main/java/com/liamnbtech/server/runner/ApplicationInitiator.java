package com.liamnbtech.server.runner;

import com.liamnbtech.server.runner.project.Cs3700Project;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * The initiator for code to run as soon as the Spring server boots.  Delegates to one of the application runners
 * managed as beans by the Spring context.
 */
@Component
public class ApplicationInitiator implements ApplicationRunner {

    private static final String CS3700_PROJECT_NAME = "Project3";

    private final Cs3700Project cs3700Project;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cs3700Project.run(args);
    }

    /**
     * Constructs an instance of the application initiator with the injected runner
     *
     * @param cs3700Project The injected cs 3700 project
     */
    public ApplicationInitiator(@Qualifier(CS3700_PROJECT_NAME) Cs3700Project cs3700Project) {
        this.cs3700Project = cs3700Project;
    }
}
