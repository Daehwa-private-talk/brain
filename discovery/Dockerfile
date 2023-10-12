FROM amazoncorretto:17.0.7-al2023-headless

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

ENV TZ=Asia/Seoul
ENV LC_ALL=C.UTF-8

COPY discovery/build/libs/discovery-0.0.1-SNAPSHOT.jar ./APP.jar

CMD ["java", "-jar", "APP.jar"]
