FROM java:17
COPY *.jar /app.jar

CMD ["--server.port=8080"]

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]