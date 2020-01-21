FROM openjdk:8
EXPOSE 9090
ADD target/todo-app-docker.jar todo-app-docker.jar
ENTRYPOINT ["java","-jar","/todo-app-docker.jar"]