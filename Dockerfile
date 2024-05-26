FROM openjdk:17
COPY ./build/libs/stock-exchange-v1.jar stock-exchange.jar
ENTRYPOINT ["java","-jar","/stock-exchange.jar"]
