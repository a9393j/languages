# Design Netflix - System Design Interview

## Problem Statement

Design a video streaming platform like Netflix that provides on-demand video content with personalized recommendations and global content delivery.

Checkout Netflix blog for Tech used in Netflix
https://netflixtechblog.com/

## Functional Requirements

### Core Features
1. **User Management**
   - User registration and authentication
   - Profile creation and management
   - Subscription management
   - Multiple profiles per account

2. **Video Streaming**
   - High-quality video streaming
   - Adaptive bitrate streaming
   - Multiple quality options
   - Offline download capability

3. **Content Management**
   - Movie and TV show catalog
   - Content categorization and tagging
   - Content metadata management
   - Regional content licensing

4. **Recommendation System**
   - Personalized content recommendations
   - Collaborative filtering
   - Content-based filtering
   - A/B testing for algorithms

5. **Search & Discovery**
   - Content search functionality
   - Genre-based browsing
   - Trending content
   - Continue watching

## Non-Functional Requirements

### Scalability
- **Users**: 200M+ subscribers
- **Daily Streams**: 1B+ hours of content
- **Peak Load**: 500K+ concurrent streams
- **Response Time**: < 100ms for recommendations

### Availability
- **Uptime**: 99.9% availability
- **Fault Tolerance**: Graceful degradation during failures

### Performance
- **Video Start Time**: < 2 seconds
- **Recommendation Generation**: < 50ms
- **Search Results**: < 100ms

## System Architecture

### High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Client    │    │  Mobile Apps    │    │   Smart TV Apps │
│   (React/Ang)   │    │  (iOS/Android)  │    │   (Roku/FireTV) │
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
│  User Service   │    │  Content Service│    │  Recommendation │
│  (Authentication)│    │  (Catalog Mgmt) │    │  Service        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │  Streaming Service│
                    │  (Video Delivery)│
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Search Service │    │  Analytics      │    │  CDN            │
│  (Content Search)│    │  Service        │    │  (Global)       │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Design

#### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    subscription_plan ENUM('basic', 'standard', 'premium') DEFAULT 'basic',
    subscription_status ENUM('active', 'cancelled', 'suspended') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Profiles Table
```sql
CREATE TABLE profiles (
    profile_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    profile_name VARCHAR(50) NOT NULL,
    avatar_url VARCHAR(500),
    is_kids_profile BOOLEAN DEFAULT FALSE,
    maturity_level ENUM('kids', 'teen', 'adult') DEFAULT 'adult',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

#### Content Table
```sql
CREATE TABLE content (
    content_id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    content_type ENUM('movie', 'tv_show', 'documentary', 'standup') NOT NULL,
    release_year INT,
    duration_minutes INT,
    rating ENUM('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'PG-13',
    genre_tags JSON,
    cast_crew JSON,
    poster_url VARCHAR(500),
    trailer_url VARCHAR(500),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Episodes Table
```sql
CREATE TABLE episodes (
    episode_id BIGINT PRIMARY KEY,
    content_id BIGINT NOT NULL,
    season_number INT NOT NULL,
    episode_number INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration_minutes INT,
    thumbnail_url VARCHAR(500),
    video_urls JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (content_id) REFERENCES content(content_id)
);
```

#### User_Watch_History Table
```sql
CREATE TABLE user_watch_history (
    history_id BIGINT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    content_id BIGINT NOT NULL,
    episode_id BIGINT,
    watch_progress_seconds INT DEFAULT 0,
    is_completed BOOLEAN DEFAULT FALSE,
    watch_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id),
    FOREIGN KEY (content_id) REFERENCES content(content_id),
    FOREIGN KEY (episode_id) REFERENCES episodes(episode_id)
);
```

#### User_Ratings Table
```sql
CREATE TABLE user_ratings (
    profile_id BIGINT NOT NULL,
    content_id BIGINT NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    rating_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (profile_id, content_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id),
    FOREIGN KEY (content_id) REFERENCES content(content_id)
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
  - Subscription management
  - Account security
- **Database**: Users, Profiles tables
- **Technology**: Spring Boot, Node.js

#### 3. **Content Service**
- **Responsibilities**:
  - Content catalog management
  - Metadata management
  - Content categorization
  - Regional availability
- **Database**: Content, Episodes tables
- **Technology**: Spring Boot, Java

#### 4. **Streaming Service** (Core Service)
- **Responsibilities**:
  - Video delivery
  - Adaptive bitrate streaming
  - Quality selection
  - Bandwidth optimization
- **Technology**: HLS, DASH, WebRTC

#### 5. **Recommendation Service** (Core Service)
- **Responsibilities**:
  - Personalized recommendations
  - Collaborative filtering
  - Content-based filtering
  - A/B testing
- **Algorithm**: 
  - Matrix factorization
  - Deep learning models
  - Real-time personalization
- **Technology**: Python (TensorFlow, PyTorch), Java

#### 6. **Search Service**
- **Responsibilities**:
  - Content search
  - Search suggestions
  - Search analytics
  - Relevance ranking
- **Technology**: Elasticsearch, Apache Solr

#### 7. **Analytics Service**
- **Responsibilities**:
  - User behavior tracking
  - Content performance metrics
  - A/B testing data
  - Business intelligence
- **Technology**: Apache Kafka, Apache Spark, BigQuery

## Recommendation System

### 1. **Collaborative Filtering**
```java
public class CollaborativeFilteringService {
    
    public List<Content> getRecommendations(Long profileId, int limit) {
        // 1. Find similar users
        List<Long> similarUsers = findSimilarUsers(profileId);
        
        // 2. Get content liked by similar users
        List<Content> candidateContent = getContentLikedByUsers(similarUsers);
        
        // 3. Filter out already watched content
        List<Content> unwatchedContent = filterUnwatchedContent(profileId, candidateContent);
        
        // 4. Rank by similarity score
        return rankBySimilarityScore(profileId, unwatchedContent, limit);
    }
    
    private List<Long> findSimilarUsers(Long profileId) {
        // Use cosine similarity or Pearson correlation
        // to find users with similar viewing patterns
        return userSimilarityRepository.findSimilarUsers(profileId, 0.7);
    }
}
```

### 2. **Content-Based Filtering**
```java
public class ContentBasedFilteringService {
    
    public List<Content> getRecommendations(Long profileId, int limit) {
        // 1. Get user's viewing history
        List<Content> watchedContent = getUserWatchHistory(profileId);
        
        // 2. Extract user preferences
        UserPreferences preferences = extractUserPreferences(watchedContent);
        
        // 3. Find similar content
        List<Content> similarContent = findSimilarContent(preferences);
        
        // 4. Rank by similarity
        return rankByContentSimilarity(preferences, similarContent, limit);
    }
    
    private UserPreferences extractUserPreferences(List<Content> watchedContent) {
        UserPreferences preferences = new UserPreferences();
        
        for (Content content : watchedContent) {
            // Extract genre preferences
            for (String genre : content.getGenreTags()) {
                preferences.addGenrePreference(genre, 1.0);
            }
            
            // Extract cast preferences
            for (String actor : content.getCast()) {
                preferences.addActorPreference(actor, 1.0);
            }
            
            // Extract director preferences
            preferences.addDirectorPreference(content.getDirector(), 1.0);
        }
        
        return preferences.normalize();
    }
}
```

### 3. **Hybrid Recommendation**
```java
public class HybridRecommendationService {
    
    public List<Content> getRecommendations(Long profileId, int limit) {
        // 1. Get collaborative filtering recommendations
        List<Content> collaborativeRecs = collaborativeService.getRecommendations(profileId, limit);
        
        // 2. Get content-based recommendations
        List<Content> contentBasedRecs = contentBasedService.getRecommendations(profileId, limit);
        
        // 3. Combine and rank
        List<Content> combinedRecs = combineRecommendations(collaborativeRecs, contentBasedRecs);
        
        // 4. Apply business rules
        return applyBusinessRules(profileId, combinedRecs, limit);
    }
    
    private List<Content> combineRecommendations(List<Content> collaborative, List<Content> contentBased) {
        Map<Long, Double> scores = new HashMap<>();
        
        // Combine scores from both approaches
        for (Content content : collaborative) {
            scores.put(content.getContentId(), scores.getOrDefault(content.getContentId(), 0.0) + 0.6);
        }
        
        for (Content content : contentBased) {
            scores.put(content.getContentId(), scores.getOrDefault(content.getContentId(), 0.0) + 0.4);
        }
        
        // Sort by combined score
        return scores.entrySet().stream()
            .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
            .map(entry -> getContent(entry.getKey()))
            .collect(Collectors.toList());
    }
}
```

## Video Streaming Architecture

### 1. **Adaptive Bitrate Streaming**
```java
public class AdaptiveStreamingService {
    
    public StreamingSession createStreamingSession(Long profileId, Long contentId) {
        // 1. Get user's bandwidth and device capabilities
        UserCapabilities capabilities = getUserCapabilities(profileId);
        
        // 2. Get available video qualities
        List<VideoQuality> availableQualities = getAvailableQualities(contentId);
        
        // 3. Select initial quality
        VideoQuality initialQuality = selectInitialQuality(capabilities, availableQualities);
        
        // 4. Create streaming session
        return new StreamingSession(profileId, contentId, initialQuality);
    }
    
    public void adaptQuality(StreamingSession session, double currentBandwidth) {
        // Monitor bandwidth and adapt quality
        VideoQuality newQuality = calculateOptimalQuality(session, currentBandwidth);
        
        if (!newQuality.equals(session.getCurrentQuality())) {
            session.setQuality(newQuality);
            notifyClient(session);
        }
    }
}
```

### 2. **CDN Integration**
```java
public class CDNService {
    
    public String getVideoUrl(Long contentId, VideoQuality quality, String region) {
        // 1. Get CDN endpoint for region
        String cdnEndpoint = getCDNEndpoint(region);
        
        // 2. Generate signed URL
        String videoPath = generateVideoPath(contentId, quality);
        
        // 3. Create signed URL with expiration
        return createSignedUrl(cdnEndpoint, videoPath, 3600); // 1 hour expiration
    }
    
    private String getCDNEndpoint(String region) {
        // Return appropriate CDN endpoint based on region
        switch (region) {
            case "us-east": return "https://cdn-us-east.netflix.com";
            case "eu-west": return "https://cdn-eu-west.netflix.com";
            case "asia-pacific": return "https://cdn-asia.netflix.com";
            default: return "https://cdn-global.netflix.com";
        }
    }
}
```

## Global CDN Strategy

### 1. **Multi-Region Deployment**
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   US East       │    │   EU West       │    │   Asia Pacific  │
│   (Primary)     │    │   (Secondary)   │    │   (Secondary)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   Global CDN    │
                    │   (CloudFront)  │
                    └─────────────────┘
```

### 2. **Content Distribution**
- **Origin Servers**: Store master copies of content
- **Edge Servers**: Cache popular content globally
- **Regional Caches**: Store region-specific content
- **Dynamic Routing**: Route users to nearest server

## A/B Testing Framework

### 1. **Experiment Management**
```java
public class ABTestingService {
    
    public Experiment getExperiment(Long profileId, String feature) {
        // 1. Check if user is in any experiment
        List<Experiment> userExperiments = getUserExperiments(profileId);
        
        // 2. Find relevant experiment for feature
        return userExperiments.stream()
            .filter(exp -> exp.getFeature().equals(feature))
            .findFirst()
            .orElse(null);
    }
    
    public String getVariant(Long profileId, String feature) {
        Experiment experiment = getExperiment(profileId, feature);
        
        if (experiment != null) {
            // Return A or B variant based on user hash
            return getUserVariant(profileId, experiment);
        }
        
        return "control"; // Default variant
    }
}
```

### 2. **Metrics Collection**
```java
public class MetricsService {
    
    public void trackEvent(Long profileId, String event, Map<String, Object> properties) {
        // 1. Get user's experiment context
        ExperimentContext context = getExperimentContext(profileId);
        
        // 2. Add experiment metadata
        properties.put("experiment_id", context.getExperimentId());
        properties.put("variant", context.getVariant());
        
        // 3. Send to analytics pipeline
        analyticsProducer.send(new AnalyticsEvent(profileId, event, properties));
    }
}
```

## Monitoring & Analytics

### 1. **User Engagement Metrics**
- **Daily Active Users (DAU)**: Number of active users per day
- **Average Session Duration**: Time spent watching per session
- **Completion Rate**: Percentage of content completed
- **Churn Rate**: Subscription cancellation rate

### 2. **Content Performance**
- **View Count**: Number of times content is viewed
- **Completion Rate**: Percentage of content completed
- **Rating Distribution**: User ratings for content
- **Trending Content**: Most popular content

### 3. **System Performance**
- **Video Start Time**: Time to start video playback
- **Buffering Rate**: Percentage of time spent buffering
- **Quality Adaptation**: Frequency of quality changes
- **CDN Performance**: Response times from different regions

## Technology Stack

### **Backend**
- **Languages**: Java (Spring Boot), Python (Django)
- **Databases**: MySQL, PostgreSQL, Redis, Cassandra
- **Search**: Elasticsearch, Apache Solr
- **Message Queues**: Apache Kafka, RabbitMQ

### **Frontend**
- **Web**: React, Angular, Vue.js
- **Mobile**: React Native, Flutter
- **Smart TV**: Native apps for different platforms

### **Infrastructure**
- **Cloud**: AWS, Google Cloud, Azure
- **CDN**: CloudFront, Akamai, Fastly
- **Video Processing**: FFmpeg, AWS MediaConvert
- **Monitoring**: Prometheus, Grafana, ELK Stack

## Interview Discussion Points

### 1. **Scale Estimation**
- **Daily Active Users**: 150M
- **Daily Streams**: 1B hours
- **Peak Concurrent Streams**: 500K
- **Storage**: 500PB+ total storage

### 2. **Recommendation Algorithm Trade-offs**
- **Accuracy vs. Performance**: Complex algorithms vs. fast response
- **Personalization vs. Diversity**: Personalized vs. diverse recommendations
- **Real-time vs. Batch**: Real-time vs. batch processing

### 3. **Video Streaming Optimization**
- **Quality vs. Bandwidth**: Video quality vs. bandwidth usage
- **Latency vs. Quality**: Start time vs. video quality
- **Global vs. Local**: Global content vs. local preferences

## Conclusion

This Netflix system design demonstrates:
- **Scalable Architecture**: Microservices with horizontal scaling
- **Advanced Recommendations**: ML-based recommendation system
- **Global CDN**: Multi-region content delivery
- **Adaptive Streaming**: Quality adaptation based on bandwidth
- **A/B Testing**: Comprehensive experimentation framework
- **Performance Optimization**: Multi-layer caching and CDN

The design prioritizes user experience, content discovery, and global scalability while maintaining high performance and availability. 