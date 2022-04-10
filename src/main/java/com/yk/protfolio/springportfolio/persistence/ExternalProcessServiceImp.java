package com.yk.protfolio.springportfolio.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
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
    public void load() throws IOException {
        assert !isInitialized;
        isInitialized = true;
        ProcessBuilder processBuilder = new ProcessBuilder().command(COMMAND_ARGUMENTS.toArray(String[]::new));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        final Process process = processBuilder.start();
        PROCESSES.add(process);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final AtomicBoolean isUpAndRunning = new AtomicBoolean(false);
        new Thread(() -> redirectStream(process, inputStream, isUpAndRunning, finishMessage)).start();
        log.atInfo().log("Process is started");
        synchronized (process) {
            try {
                process.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    @Override
    public void waitForTeardown() {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> promise = executor.scheduleAtFixedRate(() -> {
            if (PROCESSES.stream().noneMatch(Process::isAlive)) {
                executor.shutdown();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
        executor.schedule(() -> promise.cancel(false), 1, TimeUnit.MINUTES);
        log.atInfo().log("All processes are ended");
    }

    private static void redirectStream(final Process process, BufferedReader inputStream, AtomicBoolean isUpAndRunning, String finishMessage) {
        String line;
        while (true) {
            try {
                if ((line = inputStream.readLine()) != null) {
                    log.atInfo().log(line);
                    if (!isUpAndRunning.get()) {
                        if (line.contains(finishMessage)) {
                            synchronized (process) {
                                process.notify();
                            }
                            isUpAndRunning.set(true);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

}
