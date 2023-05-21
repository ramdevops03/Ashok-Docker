FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY Main.java .
COPY index.html .
RUN javac Main.java
CMD ["java", "Main"]