package com.yk.protfolio.springportfolio.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Handler to setup static folders
 */
@Configuration
@Log4j2
public class StaticFilesConfiguration implements WebMvcConfigurer {

    @Autowired
    private CustomProperties customProperties;

    private static final List<String> CLASSPATH_RESOURCE_LOCATIONS =
            List.of(
                    "classpath:/META-INF/resources/",
                    "classpath:/resources/",
                    "classpath:/static/",
                    "classpath:/public/",
                    "classpath:/custom/"
            );

    /**
     * sets resources for static folders
     *
     * @param registry registry instance
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(Stream
                        .concat(CLASSPATH_RESOURCE_LOCATIONS.stream(), Stream.of("file:" + customProperties.getStaticImageFolder()))
                        .collect(Collectors.toList()).toArray(String[]::new));
    }

}
