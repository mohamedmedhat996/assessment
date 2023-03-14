# FROM adoptopenjdk:16_36-jre-hotspot as builder
# WORKDIR extracted
# ADD ./target/*.jar app.jar
# RUN java -Djarmode=layertools -jar app.jar extract
#
# FROM adoptopenjdk:16_36-jre-hotspot
# WORKDIR application
# COPY --from=builder extracted/dependencies/ ./
# COPY --from=builder extracted/spring-boot-loader/ ./
# COPY --from=builder extracted/snapshot-dependencies/ ./
# COPY --from=builder extracted/application/ ./
#
# EXPOSE 8080
#
# ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

FROM openjdk:17-oracle
ARG JAR_FILE=*.jar
COPY ./target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]