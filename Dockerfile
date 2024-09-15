FROM maven:3.8.3-openjdk-17 as maven

COPY ./ipl ./

RUN mvn package -DskipTests

ENV PORT 8090

CMD ["java","-jar","./target/LearnJava-0.0.1.jar"]
