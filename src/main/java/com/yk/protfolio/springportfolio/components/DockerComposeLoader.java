package com.yk.protfolio.springportfolio.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Configuration
public class DockerComposeLoader {// implements ApplicationRunner {

    private static final List<Process> PROCESSES = new ArrayList<>();

    //    @Override
//    public void run(ApplicationArguments args) throws Exception {
    @EventListener(ApplicationStartingEvent.class)
    public void onApplicationEvent() throws IOException {
//        URL dockerComposeYml = DockerComposeLoader.class.getClassLoader().getResource("docker-compose.yml");
//        assert dockerComposeYml != null;
//        ProcessBuilder processBuilder = new ProcessBuilder().command("docker-compose", "-f", dockerComposeYml.getPath(), "up", "--build");
//        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
//        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
//        final Process process = processBuilder.start();
//        PROCESSES.add(process);
//        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        final AtomicBoolean isUpAndRunning = new AtomicBoolean(false);
//        new Thread(() -> {
//            String line;
//            while (true) {
//                try {
//                    if ((line = inputStream.readLine()) != null) {
//                        log.atInfo().log(line);
//                        if (!isUpAndRunning.get()) {
//                            if (line.contains("/usr/sbin/mysqld: ready for connections")) {
//                                synchronized (process) {
//                                    process.notify();
//                                }
//                                isUpAndRunning.set(true);
//                            }
//                        }
//                    }
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                    break;
//                }
//            }
//        }).start();
//        log.atInfo().log("Process is started");
//        synchronized (process) {
//            try {
//                process.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }


}

