package com.yk.protfolio.springportfolio.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    private static final List<String> CLASSPATH_RESOURCE_LOCATIONS =
            List.of(
                    "classpath:/META-INF/resources/",
                    "classpath:/resources/",
                    "classpath:/static/",
                    "classpath:/public/",
                    "classpath:/custom/"
            );

    @Autowired
    private CustomProperties customProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(Stream
                        .concat(CLASSPATH_RESOURCE_LOCATIONS.stream(), Stream.of("file:" + customProperties.getStaticImageFolder()))
                        .collect(Collectors.toList()).toArray(String[]::new));
    }

}
