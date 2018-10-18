FROM openjdk:8-jre
LABEL maintainer="Piotr Stankiewicz"

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app/news-app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/app/news-app.jar