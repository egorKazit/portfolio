# For Java 17, try this
FROM amazoncorretto:17

# Refer to Maven build -> finalName
ARG JAR_FILE=target/maven-unit-test.jar
ARG DEFAULT_CONFIG=src/main/resources/application.properties
ARG CONFIG_LOCATION=/opt/app/application.properties

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/web.jar
COPY ${JAR_FILE} web.jar
COPY ${DEFAULT_CONFIG} application.properties

ENV CONFIG_LOCATION=$CONFIG_LOCATION

# expose port
EXPOSE 8080

# java -jar /opt/app/app.jar
CMD ["java","-jar","web.jar","--spring.config.location=${CONFIG_LOCATION}"]