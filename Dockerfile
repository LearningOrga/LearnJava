FROM maven:3.5-jdk-8 as maven

COPY ./ipl

RUN mvn package -DskipTests

CMD ["java","-jar","./ipl/target/LearnJava-0.0.1.jar"]
