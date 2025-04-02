FROM openjdk:17
WORKDIR /app
COPY target/offshore-proxy.jar offshore-proxy.jar
CMD ["java", "-jar", "offshore-proxy.jar"]
EXPOSE 9090