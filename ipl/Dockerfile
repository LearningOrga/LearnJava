FROM maven:3.8.3-openjdk-17 as maven

COPY ./ ./

RUN mvn package -DskipTests

EXPOSE 9090
ENTRYPOINT ["java","-jar","./target/LearnJava-0.0.1.jar"]
