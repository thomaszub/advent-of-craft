package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        boolean testsPassed = runTests(project);
        if (!testsPassed) {
            sendEmail("Tests failed");
            return;
        }

        boolean deploySuccessful = deploy(project);
        if (!deploySuccessful) {
            sendEmail("Deployment failed");
            return;
        }
        sendEmail("Deployment completed successfully");
    }

    private boolean runTests(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            return true;
        }
        if (!"success".equals(project.runTests())) {
            log.error("Tests failed");
            return false;
        }
        log.info("Tests passed");
        return true;
    }

    private boolean deploy(Project project) {
        if (!"success".equals(project.deploy())) {
            log.error("Deployment failed");
            return false;
        }
        log.info("Deployment successful");
        return true;
    }

    private void sendEmail(String text) {
        if (!config.sendEmailSummary()) {
            log.info("Email disabled");
            return;
        }
        log.info("Sending email");
        emailer.send(text);
    }

}
