# GYM Management System — Backend

Spring Boot Microservices architecture for a gym management platform.

## Quick Start

### Prerequisites
- Java 17
- Maven 3.8+
- Docker & Docker Compose
- MySQL 8 (for local dev without Docker)

### Run with Docker (recommended)
```bash
# 1. Create your .env file
cp .env.example .env
# Edit .env with your values

# 2. Build and start all services
docker-compose up --build

# 3. Verify all services are registered
open http://localhost:8761   # Eureka Dashboard
```

### Run locally (without Docker)
Start services in this order:
```bash
# 1. Eureka Server (port 8761)
cd eureka-server && mvn spring-boot:run

# 2. Config Server (port 8888)
cd config-server && mvn spring-boot:run

# 3. API Gateway (port 8080)
cd api-gateway && mvn spring-boot:run

# 4. All other services (in any order)
cd auth-service && mvn spring-boot:run        # 8081
cd member-service && mvn spring-boot:run      # 8082
cd subscription-service && mvn spring-boot:run # 8083
cd equipment-service && mvn spring-boot:run   # 8084
cd attendance-service && mvn spring-boot:run  # 8085
cd reporting-service && mvn spring-boot:run   # 8086
```

### Build all without running tests
```bash
mvn clean install -DskipTests
```

## Service Map

| Service              | Port | Purpose                          |
|----------------------|------|----------------------------------|
| eureka-server        | 8761 | Service Discovery Dashboard      |
| config-server        | 8888 | Centralized Configuration        |
| api-gateway          | 8080 | JWT Filter + Request Routing     |
| auth-service         | 8081 | Registration & Login             |
| member-service       | 8082 | Member Profiles & Management     |
| subscription-service | 8083 | Plans, Subscriptions, Invitations|
| equipment-service    | 8084 | Device Inventory                 |
| attendance-service   | 8085 | Check-in / Check-out             |
| reporting-service    | 8086 | Dashboard & Accounting           |

## API Entry Point
All requests go through the API Gateway: **http://localhost:8080/api/**

## Swagger UI (per service)
Each service exposes its own Swagger UI directly:
- Auth:         http://localhost:8081/swagger-ui.html
- Member:       http://localhost:8082/swagger-ui.html
- Subscription: http://localhost:8083/swagger-ui.html
- Equipment:    http://localhost:8084/swagger-ui.html
- Attendance:   http://localhost:8085/swagger-ui.html
- Reporting:    http://localhost:8086/swagger-ui.html

## Architecture
```
Client → API Gateway (8080)
              ├── /api/auth/**          → auth-service (8081)      [public]
              ├── /api/members/**       → member-service (8082)    [JWT]
              ├── /api/plans            → subscription-service      [public GET]
              ├── /api/plans/**         → subscription-service      [JWT]
              ├── /api/subscriptions/** → subscription-service      [JWT]
              ├── /api/invitations/**   → subscription-service      [JWT]
              ├── /api/equipment        → equipment-service (8084)  [public GET]
              ├── /api/equipment/**     → equipment-service         [JWT]
              ├── /api/attendance/**    → attendance-service (8085) [JWT]
              └── /api/reports/**       → reporting-service (8086) [JWT ADMIN]

All services register with Eureka (8761) for load-balanced routing (lb://).
```

## Java Version Fix
This project requires **Java 17**. The parent `pom.xml` explicitly enforces this
via `<maven.compiler.release>17</maven.compiler.release>` to prevent Spring Boot's
parent POM from defaulting to Java 21.
