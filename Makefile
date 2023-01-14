SHELL=/bin/bash
HOME := $(shell pwd)

build/email-srv:
	mvn -pl :emailService -am clean install -Dmaven.test.skip=true
run/email-srv:
	mvn -pl :emailService spring-boot:run


build/inventory-srv:
	mvn -pl :inventoryService -am clean install -Dmaven.test.skip=true
run/inventory-srv:
	mvn -pl :inventoryService spring-boot:run


build/order-srv:
	mvn -pl :orderService -am clean install -Dmaven.test.skip=true
run/order-srv:
	mvn -pl :orderService spring-boot:run


build/payment-srv:
	mvn -pl :paymentService -am clean install -Dmaven.test.skip=true
run/payment-srv:
	mvn -pl :paymentService spring-boot:run


build/orchestrator-srv:
	mvn -pl :orchestratorDomainService -am clean install -Dmaven.test.skip=true
run/orchestrator-srv:
	mvn -pl :orchestratorDomainService spring-boot:run


build/spring-cloud-srv:
	mvn -pl :springCloudServer -am clean install -Dmaven.test.skip=true
run/spring-cloud-srv:
	mvn -pl :springCloudServer spring-boot:run


build/swagger-ui-srv:
	mvn -pl :swaggerUIDocsServer -am clean install -Dmaven.test.skip=true
run/swagger-ui-srv:
	mvn -pl :swaggerUIDocsServer spring-boot:run


buildInstall/parent-module:
	mvn clean install


buildRun/email-srv: build/email-srv run/email-srv
buildRun/inventory-srv: build/inventory-srv run/inventory-srv
buildRun/email-srv: build/order-srv run/order-srv
buildRun/payment-srv: build/payment-srv run/payment-srv
buildRun/orchestrator-srv: build/orchestrator-srv run/orchestrator-srv
buildRun/spring-cloud-srv: build/spring-cloud-srv run/spring-cloud-srv
buildRun/swagger-ui-srv: build/swagger-ui-srv run/swagger-ui-srv

