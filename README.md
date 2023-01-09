    Requirement :
    Spring WebFlux
    R2DBC Postgres
    Spring Cloud Stream
    Project Reactor
    Spring Cloud Eureka
    SpringFox-UI


    Spring webflux service :
    inventoryService = http://localhost:8090
    orderService = http://localhost:8000
    orchestratorDomainService = http://localhost:5000
    paymentService = http://localhost:9002
    emailService = http://localhost:9003
    
    Spring cloud service :
    springCloudServer = http://localhost:8761
    swaggerUIDocsServer = http://localhost:9093
    
    All endpoint service streaming on :
    URL : http://localhost:9093/swagger-ui/index.html?
    
    Docker Requirement : 
    Docker & Postgresql
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