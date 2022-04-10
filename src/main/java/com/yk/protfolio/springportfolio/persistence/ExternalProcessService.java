package com.yk.protfolio.springportfolio.persistence;

import java.io.IOException;

public interface ExternalProcessService {

    ExternalProcessService init();

    ExternalProcessService setContext(String arg);

    ExternalProcessService setFinishMessage(String finishMessage);

    void load() throws IOException, InterruptedException;

    void waitForTeardown();

}
