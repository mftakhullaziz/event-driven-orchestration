## Event-Driven Saga Orchestration
   
    Author : Miftakhul Aziz
    Github : https://github.com/mftakhullaziz
   
   Dependency requirement :
    
    Spring WebFlux
    R2DBC Postgres (Non-Blocking Database)
    Spring Cloud Stream
    Project Reactor
    Spring Cloud Eureka Server
    Spring Cloud Eureka Client
    SpringFox-UI
    Spring Kafka
    Lombok

   Spring webflux service :
    
    emailService = http://localhost:8081
    inventoryService = http://localhost:8082
    orderService = http://localhost:8083
    paymentService = http://localhost:8084
    orchestratorDomainService = http://localhost:8085
    
   Spring cloud service :
    
    springCloudServer = http://localhost:8761
    swaggerUIDocsServer = http://localhost:8000
    
    All endpoint service streaming on :
    URL : http://localhost:8000/swagger-ui/index.html?
    
   Docker Requirement : 
    
   Postgresql
    
    1. add postgres images
        $~: docker pull postgres
    2. create user authentication with postgres db on docker container
        $~: docker run --name eventdriven-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
        Explain :
            --name : container name is "evendriven-postgres"
            -e POSTGRES_USER : username is "user"
            -e POSTGRES_PASSWORD : password is "password"
            -p : port is "5432" running on http://localhost:5432/postgres
            -d : databases is "postgres"
        To stop container
        $~: docker stop eventdriven-postgres
        To start container
        $~: docker start eventdriven-postgres

   Kafka-Broker :
    
    1. Run docker compose up in path kafka-broker:
        $~: docker compose up

   How to use :
    
    1. Run Postgres database and migration query
    2. Run Kafka Broker
    3. Build all project reactor in default module 
       $~: cd saga-orchestration
       saga-orchestration$~: mvn clean install
    4. Run all service
    5. Request API from Postman or other

   Saga Orchestrator Pattern :
   ![OrchestratorSaga](https://github.com/mftakhullaziz/event-driven-orchestration-app/blob/main/docs/orchestration-saga.jpg)
   
   Spring Cloud Eureka :
   ![SpringCloud](https://github.com/mftakhullaziz/event-driven-orchestration-app/blob/main/docs/spring-cloud.png)
   

   Next updated :
    
    add payment service
    add notification mail service
    add consumer inventory
    add consumer payment
    add consumer notification email
    
   Happy coders:
    
    
