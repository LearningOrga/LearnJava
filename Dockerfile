FROM maven:3.5-jdk-8 as maven

COPY ./ipl ./

RUN mvn package -DskipTests

ENV PORT 8090

CMD ["java","-jar","./target/LearnJava-0.0.1.jar"]
