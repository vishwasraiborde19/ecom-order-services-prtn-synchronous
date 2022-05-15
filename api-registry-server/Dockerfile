FROM openjdk:14.0.2
EXPOSE 8761
ADD target/registry.jar registry.jar
ENTRYPOINT ["java","-jar","/registry.jar"]