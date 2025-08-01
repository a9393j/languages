# Design Facebook - System Design Interview

## Problem Statement

Design a social networking platform like Facebook that allows users to connect, share content, and view personalized news feeds.

## Functional Requirements

### Core Features
1. **User Management**
   - User registration and authentication
   - Profile creation and management
   - Friend requests and connections
   - Privacy settings

2. **Content Sharing**
   - Text posts and status updates
   - Photo and video uploads
   - Stories and live streaming
   - Comments and reactions

3. **News Feed**
   - Personalized content feed
   - Real-time updates
   - Content ranking algorithm
   - Infinite scroll

4. **Social Features**
   - Friend suggestions
   - Groups and pages
   - Events and invitations
   - Messenger functionality

5. **Privacy & Security**
   - Content visibility controls
   - Block and report features
   - Data protection
   - Account security

## Non-Functional Requirements

### Scalability
- **Users**: 2B+ active users
- **Daily Posts**: 500M+ posts per day
- **Peak Load**: 1M+ concurrent users
- **Response Time**: < 200ms for feed requests

### Availability
- **Uptime**: 99.9% availability
- **Fault Tolerance**: Graceful degradation during failures

### Performance
- **Feed Generation**: < 100ms for personalized feed
- **Image/Video Loading**: Optimized media delivery
- **Search**: < 50ms for user/content search

## System Architecture

### High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Client    │    │  Mobile Apps    │    │   Admin Panel   │
│   (React/Ang)   │    │  (iOS/Android)  │    │   (Dashboard)   │
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
│  User Service   │    │  Content Service│    │  Feed Service   │
│  (Authentication)│    │  (Post/Media)   │    │  (News Feed)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │  Social Service │
                    │  (Friends/Groups)│
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Search Service │    │  Notification   │    │  Analytics      │
│  (User/Content) │    │  Service        │    │  Service        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Design

#### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    profile_picture_url VARCHAR(500),
    bio TEXT,
    date_of_birth DATE,
    location VARCHAR(200),
    privacy_level ENUM('public', 'friends', 'private') DEFAULT 'friends',
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Friendships Table
```sql
CREATE TABLE friendships (
    friendship_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status ENUM('pending', 'accepted', 'blocked') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (friend_id) REFERENCES users(user_id),
    UNIQUE KEY unique_friendship (user_id, friend_id)
);
```

#### Posts Table
```sql
CREATE TABLE posts (
    post_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT,
    media_urls JSON,
    post_type ENUM('text', 'photo', 'video', 'story') DEFAULT 'text',
    privacy_level ENUM('public', 'friends', 'private') DEFAULT 'friends',
    location VARCHAR(200),
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

#### Reactions Table
```sql
CREATE TABLE reactions (
    reaction_id BIGINT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reaction_type ENUM('like', 'love', 'haha', 'wow', 'sad', 'angry') DEFAULT 'like',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    UNIQUE KEY unique_reaction (post_id, user_id)
);
```

#### Comments Table
```sql
CREATE TABLE comments (
    comment_id BIGINT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    parent_comment_id BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (parent_comment_id) REFERENCES comments(comment_id)
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
  - Privacy settings
  - Account security
- **Database**: Users table
- **Technology**: Spring Boot, Node.js

#### 3. **Content Service**
- **Responsibilities**:
  - Post creation and management
  - Media upload and processing
  - Content moderation
  - Content search
- **Database**: Posts, Reactions, Comments tables
- **Technology**: Spring Boot, Java

#### 4. **Feed Service** (Core Service)
- **Responsibilities**:
  - News feed generation
  - Content ranking algorithm
  - Real-time updates
  - Feed personalization
- **Algorithm**: 
  - EdgeRank algorithm
  - Content relevance scoring
  - User engagement prediction
- **Technology**: Spring Boot, Java

#### 5. **Social Service**
- **Responsibilities**:
  - Friend management
  - Group and page management
  - Event coordination
  - Social graph maintenance
- **Database**: Friendships table
- **Technology**: Spring Boot, Java

#### 6. **Search Service**
- **Responsibilities**:
  - User search
  - Content search
  - Search suggestions
  - Search analytics
- **Technology**: Elasticsearch, Apache Solr

#### 7. **Notification Service**
- **Responsibilities**:
  - Push notifications
  - Email notifications
  - In-app notifications
  - Notification preferences
- **Technology**: Firebase Cloud Messaging, SendGrid

## News Feed Algorithm (EdgeRank)

### Algorithm Components

#### 1. **Affinity Score**
```java
public class AffinityCalculator {
    
    public double calculateAffinity(User user, User contentCreator) {
        // Factors:
        // - Friendship strength
        // - Interaction history
        // - Profile similarity
        // - Mutual friends count
        
        double friendshipScore = getFriendshipScore(user, contentCreator);
        double interactionScore = getInteractionScore(user, contentCreator);
        double similarityScore = getProfileSimilarity(user, contentCreator);
        
        return (friendshipScore * 0.4) + (interactionScore * 0.4) + (similarityScore * 0.2);
    }
}
```

#### 2. **Edge Weight**
```java
public class EdgeWeightCalculator {
    
    public double calculateEdgeWeight(Post post, User user) {
        // Factors:
        // - Post type (photo, video, text)
        // - Content length
        // - Media quality
        // - Time decay
        
        double typeScore = getPostTypeScore(post.getType());
        double contentScore = getContentQualityScore(post);
        double timeDecay = getTimeDecayScore(post.getCreatedAt());
        
        return (typeScore * 0.3) + (contentScore * 0.4) + (timeDecay * 0.3);
    }
}
```

#### 3. **Time Decay**
```java
public class TimeDecayCalculator {
    
    public double calculateTimeDecay(Date postTime) {
        long hoursSincePost = (System.currentTimeMillis() - postTime.getTime()) / (1000 * 60 * 60);
        
        // Exponential decay: newer posts get higher scores
        return Math.exp(-hoursSincePost / 24.0); // 24-hour half-life
    }
}
```

### Feed Generation Process

```java
public class NewsFeedService {
    
    public List<Post> generateFeed(User user, int page, int pageSize) {
        // 1. Get user's social graph
        Set<User> friends = getFriends(user);
        
        // 2. Get posts from friends and followed pages
        List<Post> candidatePosts = getCandidatePosts(user, friends);
        
        // 3. Calculate EdgeRank scores
        List<PostScore> scoredPosts = candidatePosts.stream()
            .map(post -> new PostScore(post, calculateEdgeRank(user, post)))
            .collect(Collectors.toList());
        
        // 4. Sort by score and apply pagination
        return scoredPosts.stream()
            .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
            .skip(page * pageSize)
            .limit(pageSize)
            .map(PostScore::getPost)
            .collect(Collectors.toList());
    }
    
    private double calculateEdgeRank(User user, Post post) {
        double affinity = affinityCalculator.calculateAffinity(user, post.getUser());
        double edgeWeight = edgeWeightCalculator.calculateEdgeWeight(post, user);
        double timeDecay = timeDecayCalculator.calculateTimeDecay(post.getCreatedAt());
        
        return affinity * edgeWeight * timeDecay;
    }
}
```

## Scalability Solutions

### 1. **Database Scaling**
- **Read Replicas**: Multiple read replicas for feed queries
- **Sharding**: Shard by user_id for user-specific data
- **Caching**: Redis for frequently accessed data

### 2. **Feed Optimization**
```
┌─────────────────┐
│   CDN Layer     │ ← Static content (images, videos)
└─────────────────┘
         │
┌─────────────────┐
│ Application Cache│ ← User sessions, feed data
│    (Redis)      │
└─────────────────┘
         │
┌─────────────────┐
│ Database Cache  │ ← User profiles, friendship data
│    (Redis)      │
└─────────────────┘
```

### 3. **Feed Pre-computation**
```java
public class FeedPrecomputer {
    
    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void precomputeFeeds() {
        List<User> activeUsers = getActiveUsers();
        
        for (User user : activeUsers) {
            List<Post> feed = generateFeed(user, 0, 50);
            cacheFeed(user.getUserId(), feed);
        }
    }
}
```

### 4. **Content Delivery Network (CDN)**
- **Static Content**: Images, videos, CSS, JS
- **Global Distribution**: Multiple edge locations
- **Caching Strategy**: Cache based on content type and popularity

## Privacy & Security

### 1. **Content Privacy**
```java
public class PrivacyService {
    
    public boolean canUserViewPost(User viewer, Post post) {
        User postOwner = post.getUser();
        
        // Check if viewer is the post owner
        if (viewer.getUserId().equals(postOwner.getUserId())) {
            return true;
        }
        
        // Check post privacy level
        switch (post.getPrivacyLevel()) {
            case PUBLIC:
                return true;
            case FRIENDS:
                return areFriends(viewer, postOwner);
            case PRIVATE:
                return false;
            default:
                return false;
        }
    }
}
```

### 2. **Data Protection**
- **Encryption**: All sensitive data encrypted at rest and in transit
- **GDPR Compliance**: User data protection and right to be forgotten
- **Access Control**: Role-based permissions for different user types

### 3. **Content Moderation**
```java
public class ContentModerationService {
    
    public ModerationResult moderateContent(Post post) {
        // Check for inappropriate content
        boolean hasInappropriateContent = checkInappropriateContent(post.getContent());
        
        // Check for spam
        boolean isSpam = checkSpam(post);
        
        // Check for copyright violations
        boolean hasCopyrightIssues = checkCopyright(post.getMediaUrls());
        
        if (hasInappropriateContent || isSpam || hasCopyrightIssues) {
            return new ModerationResult(false, "Content violates community guidelines");
        }
        
        return new ModerationResult(true, "Content approved");
    }
}
```

## Real-time Features

### 1. **WebSocket for Real-time Updates**
```java
@ServerEndpoint("/feed/{userId}")
public class FeedWebSocket {
    
    @OnMessage
    public void handleFeedUpdate(String message, Session session) {
        // Parse message
        FeedUpdate update = parseMessage(message);
        
        // Update feed in real-time
        updateUserFeed(update.getUserId(), update.getNewPost());
        
        // Notify connected clients
        broadcastToFriends(update.getUserId(), update);
    }
}
```

### 2. **Live Streaming**
- **WebRTC**: For real-time video streaming
- **Media Server**: For handling multiple concurrent streams
- **Quality Adaptation**: Adaptive bitrate streaming

## Monitoring & Analytics

### 1. **User Engagement Metrics**
- **Daily Active Users (DAU)**: Number of active users per day
- **Monthly Active Users (MAU)**: Number of active users per month
- **Engagement Rate**: Likes, comments, shares per post
- **Feed Scroll Depth**: How far users scroll in their feed

### 2. **Content Performance**
- **Post Reach**: Number of users who see a post
- **Engagement Rate**: Interactions per impression
- **Viral Coefficient**: How much content spreads
- **Content Quality Score**: Algorithm-based content quality

### 3. **System Performance**
- **Feed Generation Time**: Time to generate personalized feed
- **Media Loading Time**: Time to load images and videos
- **API Response Time**: Response time for all API calls
- **Error Rates**: Percentage of failed requests

## Technology Stack

### **Backend**
- **Languages**: Java (Spring Boot), Python (Django)
- **Databases**: MySQL, PostgreSQL, Redis, MongoDB
- **Search**: Elasticsearch, Apache Solr
- **Message Queues**: Apache Kafka, RabbitMQ

### **Frontend**
- **Web**: React, Angular, Vue.js
- **Mobile**: React Native, Flutter
- **Real-time**: WebSocket, Server-Sent Events

### **Infrastructure**
- **Cloud**: AWS, Google Cloud, Azure
- **CDN**: CloudFront, Akamai
- **Media Storage**: Amazon S3, Google Cloud Storage
- **Monitoring**: Prometheus, Grafana, ELK Stack

## Interview Discussion Points

### 1. **Scale Estimation**
- **Daily Active Users**: 1.5B
- **Daily Posts**: 500M
- **Daily Photos**: 300M
- **Daily Videos**: 100M
- **Storage**: 100PB+ total storage

### 2. **Feed Algorithm Trade-offs**
- **Relevance vs. Recency**: Balancing content relevance with freshness
- **Engagement vs. Quality**: Promoting engaging content vs. quality content
- **Personalization vs. Diversity**: Personalized feed vs. content diversity

### 3. **Privacy vs. Personalization**
- **Data Collection**: How much user data to collect for personalization
- **Privacy Controls**: Granular privacy settings for different content types
- **Transparency**: Clear communication about data usage

## Conclusion

This Facebook system design demonstrates:
- **Scalable Architecture**: Microservices with horizontal scaling
- **Advanced Algorithms**: EdgeRank for personalized feed generation
- **Privacy Focus**: Comprehensive privacy controls and data protection
- **Real-time Features**: WebSocket for live updates
- **Content Moderation**: Automated and manual content review
- **Performance Optimization**: CDN, caching, and pre-computation

The design prioritizes user experience, privacy, and scalability while maintaining high performance and security standards. 