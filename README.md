# Kubernetes Java Shopping Demo - Modernized

Three Java 21 microservices deployed to Kubernetes:

- **shopfront** – web UI and API, port `8010`
- **productcatalogue** – product API, port `8020`
- **stockmanager** – stock API, port `8030`

## Technology

- Java 21 LTS
- Spring Boot 3.5
- Maven
- Docker
- Kubernetes `apps/v1`
- Spring Boot Actuator health probes
- H2 for the stock demo

## Build

Build each application:

```bash
cd productcatalogue && mvn clean package
cd ../stockmanager && mvn clean package
cd ../shopfront && mvn clean package
```

Run tests:

```bash
mvn test
```

## Docker

Each Dockerfile uses a multi-stage Java 21 build.

```bash
docker build -t ajaypasili/productcatalogue:1.0.0 ./productcatalogue
docker build -t ajaypasili/stockmanager:1.0.0 ./stockmanager
docker build -t ajaypasili/shopfront:1.0.0 ./shopfront
```

For Minikube without pushing images, load them:

```bash
minikube image load ajaypasili/productcatalogue:1.0.0
minikube image load ajaypasili/stockmanager:1.0.0
minikube image load ajaypasili/shopfront:1.0.0
```

## Deploy to Kubernetes

```bash
kubectl apply -f kubernetes/namespace.yaml
kubectl apply -f kubernetes/productcatalogue-service.yaml
kubectl apply -f kubernetes/stockmanager-service.yaml
kubectl apply -f kubernetes/shopfront-service.yaml
```

Check:

```bash
kubectl get all -n djshopping
kubectl get pods -n djshopping
```

For Minikube access:

```bash
kubectl -n djshopping port-forward service/shopfront 8010:8010
```

Open `http://localhost:8010`.

## Ingress (optional)

Enable the Minikube ingress addon:

```bash
minikube addons enable ingress
kubectl apply -f kubernetes/shopfront-ingress.yaml
```

## Health endpoints

```bash
curl http://localhost:8010/actuator/health
curl http://localhost:8020/actuator/health
curl http://localhost:8030/actuator/health
```

Kubernetes uses `/actuator/health/liveness` and `/actuator/health/readiness`.

## Important modernization changes

The original project contained Java 8-era Spring Boot, Netflix Hystrix/Eureka dependencies, Dropwizard 1.x, old JPA `javax.*` imports, Cucumber 1.x dependencies, generated build artifacts, and basic Kubernetes probes.

This version upgrades the applications to Java 21 and Spring Boot 3.x, removes obsolete Netflix dependencies, migrates Product Catalogue to Spring Boot for a consistent stack, uses Jakarta persistence APIs, modern JUnit 5 tests, multi-stage Docker builds, ClusterIP internal services, namespace isolation, health probes, and resource requests/limits.
