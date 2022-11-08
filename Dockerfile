FROM openjdk:8-jdk-alpine
EXPOSE  8089
ADD target/DevopsProject.jar DevopsProject.jar
ENTRYPOINT ["java","-jar","/DevopsProject.jar"]