FROM  openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN curl -u admin:esprit -o tpachat.jar "http://192.168.1.148:8081/service/rest/v1/search/assets/download?sort=version&repository=maven-nexus-repo&maven.groupId=tn.esprit.rh&maven.artifactId=achat&maven.baseVersion=1.0&maven.extension=jar" -L
ADD /spring/target/achat-1.0.jar achat.jar
ENTRYPOINT ["java","-jar","/spring/achat-1.0.jar"]
EXPOSE 8083