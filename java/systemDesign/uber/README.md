# Design Uber - System Design Interview

## Problem Statement

Design a ride-sharing service like Uber that connects riders with drivers in real-time.

## Functional Requirements

### Core Features
1. **User Registration & Authentication**
   - Rider and driver registration
   - Profile management
   - Authentication and authorization

2. **Ride Booking**
   - Location-based ride requests
   - Driver matching and assignment
   - Real-time tracking
   - Ride status updates

3. **Payment Processing**
   - Multiple payment methods
   - Fare calculation
   - Payment processing
   - Receipt generation

4. **Driver Management**
   - Driver availability status
   - Earnings tracking
   - Rating and feedback system

5. **Real-time Features**
   - Live location tracking
   - ETA calculation
   - Real-time notifications

## Non-Functional Requirements

### Scalability
- **Users**: 100M+ registered users
- **Daily Rides**: 10M+ rides per day
- **Peak Load**: 100K+ concurrent users
- **Response Time**: < 200ms for ride requests

### Availability
- **Uptime**: 99.9% availability
- **Fault Tolerance**: System continues working despite component failures

### Performance
- **Latency**: < 100ms for location updates
- **Throughput**: Handle 10K+ requests per second

## System Architecture

### High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Mobile Apps   │    │   Web Portal    │    │   Admin Panel   │
│  (iOS/Android)  │    │   (React/Ang)   │    │   (Dashboard)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   Load Balancer │
                    │   (HAProxy/Nginx)│
                    └─────────────────┘
                                 │
                    ┌─────────────────┐
                    │   API Gateway   │
                    │   (Kong/AWS API)│
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  User Service   │    │  Driver Service │    │  Payment Service│
│  (Authentication)│    │  (Driver Mgmt)  │    │  (Payment Proc) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │  Ride Service   │
                    │  (Core Logic)   │
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Location Service│    │  Notification   │    │  Analytics      │
│  (GPS Tracking) │    │  Service        │    │  Service        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Design

#### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    user_type ENUM('rider', 'driver') NOT NULL,
    status ENUM('active', 'inactive', 'suspended') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Drivers Table
```sql
CREATE TABLE drivers (
    driver_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    vehicle_info JSON,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    current_location POINT,
    is_available BOOLEAN DEFAULT FALSE,
    rating DECIMAL(3,2) DEFAULT 0.0,
    total_rides INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

#### Rides Table
```sql
CREATE TABLE rides (
    ride_id BIGINT PRIMARY KEY,
    rider_id BIGINT NOT NULL,
    driver_id BIGINT,
    pickup_location POINT NOT NULL,
    dropoff_location POINT NOT NULL,
    status ENUM('requested', 'accepted', 'started', 'completed', 'cancelled') DEFAULT 'requested',
    fare DECIMAL(10,2),
    distance DECIMAL(10,2),
    duration INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (rider_id) REFERENCES users(user_id),
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);
```

#### Payments Table
```sql
CREATE TABLE payments (
    payment_id BIGINT PRIMARY KEY,
    ride_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50),
    status ENUM('pending', 'completed', 'failed', 'refunded') DEFAULT 'pending',
    transaction_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ride_id) REFERENCES rides(ride_id)
);
```

### Key Components

#### 1. **API Gateway**
- **Purpose**: Single entry point for all client requests
- **Features**: 
  - Rate limiting
  - Authentication
  - Request routing
  - API versioning
- **Technology**: Kong, AWS API Gateway

#### 2. **User Service**
- **Responsibilities**:
  - User registration and authentication
  - Profile management
  - Session management
- **Database**: Users table
- **Technology**: Spring Boot, Node.js

#### 3. **Driver Service**
- **Responsibilities**:
  - Driver registration and verification
  - Availability management
  - Earnings tracking
- **Database**: Drivers table
- **Technology**: Spring Boot, Node.js

#### 4. **Ride Service** (Core Service)
- **Responsibilities**:
  - Ride request processing
  - Driver matching algorithm
  - Ride status management
- **Algorithm**: 
  - Find nearest available drivers
  - Consider driver rating and distance
  - Handle multiple concurrent requests
- **Technology**: Spring Boot, Java

#### 5. **Location Service**
- **Responsibilities**:
  - Real-time location tracking
  - ETA calculation
  - Route optimization
- **Technology**: Redis for real-time data, PostgreSQL for historical data

#### 6. **Payment Service**
- **Responsibilities**:
  - Payment processing
  - Fare calculation
  - Refund handling
- **Integrations**: Stripe, PayPal, local payment gateways
- **Technology**: Spring Boot, Node.js

#### 7. **Notification Service**
- **Responsibilities**:
  - Push notifications
  - SMS notifications
  - Email notifications
- **Technology**: Firebase Cloud Messaging, Twilio, SendGrid

## Scalability Solutions

### 1. **Database Scaling**
- **Read Replicas**: Multiple read replicas for scaling read operations
- **Sharding**: Shard by user_id or geographic region
- **Caching**: Redis for frequently accessed data

### 2. **Load Balancing**
- **Application Load Balancer**: Distribute traffic across multiple service instances
- **Database Load Balancer**: Route read queries to replicas
- **CDN**: Serve static content globally

### 3. **Caching Strategy**
```
┌─────────────────┐
│   CDN Layer     │ ← Static content (images, CSS, JS)
└─────────────────┘
         │
┌─────────────────┐
│ Application Cache│ ← Session data, user profiles
│    (Redis)      │
└─────────────────┘
         │
┌─────────────────┐
│ Database Cache  │ ← Query results, frequently accessed data
│    (Redis)      │
└─────────────────┘
```

### 4. **Message Queues**
- **Apache Kafka**: For real-time location updates
- **RabbitMQ**: For payment processing and notifications
- **AWS SQS**: For asynchronous task processing

## Real-time Features

### 1. **WebSocket Implementation**
```java
@ServerEndpoint("/location/{userId}")
public class LocationWebSocket {
    
    @OnMessage
    public void handleLocationUpdate(String message, Session session) {
        // Update location in Redis
        // Broadcast to relevant users
        // Update ETA calculations
    }
}
```

### 2. **Location Tracking**
- **GPS Updates**: Every 5-10 seconds
- **Real-time Storage**: Redis for current locations
- **Historical Data**: PostgreSQL for analytics

### 3. **Driver Matching Algorithm**
```java
public class DriverMatchingService {
    
    public List<Driver> findNearbyDrivers(Location pickupLocation, int radius) {
        // 1. Query Redis for drivers in radius
        // 2. Filter by availability and rating
        // 3. Sort by distance and rating
        // 4. Return top candidates
    }
    
    public Driver selectBestDriver(List<Driver> candidates, RideRequest request) {
        // Consider factors:
        // - Distance to pickup
        // - Driver rating
        // - Driver's current earnings
        // - Historical performance
    }
}
```

## Security Considerations

### 1. **Authentication & Authorization**
- **JWT Tokens**: For API authentication
- **OAuth 2.0**: For third-party integrations
- **Role-based Access**: Different permissions for riders, drivers, admins

### 2. **Data Protection**
- **Encryption**: All sensitive data encrypted at rest and in transit
- **PCI Compliance**: For payment processing
- **GDPR Compliance**: For user data protection

### 3. **API Security**
- **Rate Limiting**: Prevent abuse
- **Input Validation**: Sanitize all inputs
- **HTTPS**: All communications encrypted

## Monitoring & Analytics

### 1. **Application Monitoring**
- **APM**: New Relic, Datadog for performance monitoring
- **Logging**: ELK Stack for centralized logging
- **Metrics**: Prometheus + Grafana for metrics visualization

### 2. **Business Metrics**
- **Ride Success Rate**: Percentage of completed rides
- **Average ETA**: Time from request to pickup
- **Driver Utilization**: Percentage of active drivers
- **Revenue Metrics**: Daily/Monthly revenue tracking

## Deployment Architecture

### 1. **Containerization**
- **Docker**: Containerize all services
- **Kubernetes**: Orchestration and scaling
- **Helm**: Package management

### 2. **Cloud Infrastructure**
- **AWS/GCP/Azure**: Cloud provider
- **Auto Scaling**: Automatic scaling based on load
- **Multi-region**: Global deployment for low latency

### 3. **CI/CD Pipeline**
```
Code Commit → Automated Testing → Build → Deploy to Staging → Deploy to Production
```

## Interview Discussion Points

### 1. **Scale Estimation**
- **Daily Active Users**: 10M
- **Peak Concurrent Users**: 100K
- **Daily Rides**: 1M
- **Data Storage**: 1TB per day
- **Bandwidth**: 10GB per day

### 2. **Trade-offs**
- **Consistency vs. Availability**: Eventual consistency for location data
- **Latency vs. Accuracy**: Approximate location for real-time updates
- **Cost vs. Performance**: Caching vs. database queries

### 3. **Failure Scenarios**
- **Database Failure**: Read replicas, failover
- **Service Failure**: Circuit breakers, fallback mechanisms
- **Network Issues**: Retry mechanisms, offline capabilities

## Technology Stack

### **Backend**
- **Languages**: Java (Spring Boot), Node.js
- **Databases**: PostgreSQL, Redis, MongoDB
- **Message Queues**: Apache Kafka, RabbitMQ
- **Caching**: Redis, Memcached

### **Frontend**
- **Mobile**: React Native, Flutter
- **Web**: React, Angular
- **Real-time**: WebSocket, Server-Sent Events

### **Infrastructure**
- **Cloud**: AWS/GCP/Azure
- **Containerization**: Docker, Kubernetes
- **Monitoring**: Prometheus, Grafana, ELK Stack
- **CI/CD**: Jenkins, GitLab CI, GitHub Actions

## Conclusion

This Uber system design demonstrates:
- **Microservices Architecture**: Scalable and maintainable
- **Real-time Processing**: WebSocket for live updates
- **Scalable Database**: Sharding and read replicas
- **Caching Strategy**: Multi-layer caching
- **Security**: Comprehensive security measures
- **Monitoring**: Full observability stack

The design prioritizes scalability, availability, and real-time performance while maintaining security and user experience. 