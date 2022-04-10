package com.yk.protfolio.springportfolio.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ExternalProcessServiceImp implements ExternalProcessService {

    private static final List<String> COMMAND_ARGUMENTS = new ArrayList<>();
    private static final List<Process> PROCESSES = new ArrayList<>();
    private String finishMessage;
    private boolean isInitialized = false;

    @Override
    public ExternalProcessService init() {
        COMMAND_ARGUMENTS.clear();
        isInitialized = false;
        return this;
    }

    @Override
    public ExternalProcessService setContext(String arg) {
        COMMAND_ARGUMENTS.add(arg);
        return this;
    }

    @Override
    public ExternalProcessService setFinishMessage(String finishMessage) {
        this.finishMessage = finishMessage;
        return this;
    }

    @Override
    public void load() throws IOException, InterruptedException {
        assert !isInitialized;
        isInitialized = true;
        ProcessBuilder processBuilder = new ProcessBuilder().command(COMMAND_ARGUMENTS.toArray(String[]::new));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        final Process process = processBuilder.start();
        PROCESSES.add(process);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final AtomicBoolean isUpAndRunning = new AtomicBoolean(false);
        new Thread(() -> redirectStream(process, inputStream, isUpAndRunning, finishMessage, Process::notifyAll)).start();
        log.atInfo().log("Process is started");
        synchronized (process) {
            while (process.isAlive()) {
                process.wait();
            }
        }
    }

    @SneakyThrows
    @Override
    public void waitForTeardown() {
        while (PROCESSES.stream().noneMatch(Process::isAlive)) {
            Thread.sleep(500);
        }
        PROCESSES.forEach(Process::destroy);
        log.atInfo().log("All processes are ended");
    }

    private static void redirectStream(final Process process, BufferedReader inputStream, AtomicBoolean isUpAndRunning, String finishMessage,
                                       Consumer<Process> notifyFunction) {
        String line;
        while (!isUpAndRunning.get()) {
            try {
                if ((line = inputStream.readLine()) != null) {
                    log.atInfo().log(line);
                    if (!isUpAndRunning.get() && line.contains(finishMessage)) {
                        notifyFunction.accept(process);
                        isUpAndRunning.set(true);
                    }
                }
            } catch (IOException e) {
                log.atError().log(e.getMessage());
                break;
            }
        }
    }

}
