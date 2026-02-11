# 🛒 E-Commerce Microservices Architecture

This project demonstrates a production-ready Microservices Architecture built using Spring Boot and Spring Cloud.

It includes Service Discovery, API Gateway, Load Balancing, Circuit Breaker, Retry Mechanism, Distributed Tracing, and Logging.

---

## 🏗 Architecture Overview

Client  
↓  
API Gateway  
↓  
Order Service  
↓  
Payment Service  

All services are registered with Eureka Server.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- Eureka Server (Service Discovery)
- Spring Cloud Gateway
- OpenFeign
- Resilience4j (Circuit Breaker + Retry)
- Micrometer Tracing
- Zipkin
- Logback (File Logging)
- Maven

---

## 📦 Microservices Included

### 1️⃣ Service Registry (Eureka Server)
- Registers all microservices
- Runs on port 8761

### 2️⃣ API Gateway
- Single entry point
- Dynamic routing using Eureka
- Runs on port 8080

### 3️⃣ Order Service
- Calls Payment Service using Feign Client
- Implements Circuit Breaker and Retry
- Runs on port 8081

### 4️⃣ Payment Service
- Processes payment requests
- Runs on port 8082 / 8083 (for load balancing demo)

---

## 🔁 Features Implemented

✅ Service Discovery  
✅ Client-side Load Balancing  
✅ Feign Inter-service Communication  
✅ Circuit Breaker  
✅ Retry Mechanism  
✅ API Gateway Routing  
✅ File-based Logging  
✅ TraceId & SpanId Logging  
✅ Distributed Tracing (Zipkin Ready)

---

## 🧪 How to Run

### 1️⃣ Start Eureka Server
