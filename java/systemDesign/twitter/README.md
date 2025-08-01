# Design Twitter - System Design Interview

## Problem Statement

Design a microblogging platform like Twitter that allows users to post short messages (tweets), follow others, and view personalized timelines.

## Functional Requirements

### Core Features
1. **User Management**
   - User registration and authentication
   - Profile creation and management
   - Follow/unfollow functionality
   - Account verification

2. **Tweet Management**
   - Post tweets (280 characters)
   - Retweet functionality
   - Quote tweets
   - Thread creation

3. **Timeline Generation**
   - Home timeline (following tweets)
   - User profile timeline
   - Real-time updates
   - Infinite scroll

4. **Social Features**
   - Like/unlike tweets
   - Reply to tweets
   - Direct messaging
   - Trending topics

5. **Search & Discovery**
   - Search tweets and users
   - Trending hashtags
   - Who to follow suggestions
   - Explore page

## Non-Functional Requirements

### Scalability
- **Users**: 300M+ active users
- **Daily Tweets**: 500M+ tweets per day
- **Peak Load**: 100K+ concurrent users
- **Response Time**: < 200ms for timeline requests

### Availability
- **Uptime**: 99.9% availability
- **Fault Tolerance**: Graceful degradation during failures

### Performance
- **Timeline Generation**: < 100ms for personalized timeline
- **Search**: < 50ms for search results
- **Real-time Updates**: < 50ms for live updates

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
│  User Service   │    │  Tweet Service  │    │  Timeline Service│
│  (Authentication)│    │  (Tweet Mgmt)   │    │  (Feed Gen)     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │  Social Service │
                    │  (Follow/Like)  │
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Search Service │    │  Notification   │    │  Analytics      │
│  (Tweet/User)   │    │  Service        │    │  Service        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Design

#### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    username VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    bio TEXT,
    profile_image_url VARCHAR(500),
    banner_image_url VARCHAR(500),
    location VARCHAR(100),
    website VARCHAR(255),
    is_verified BOOLEAN DEFAULT FALSE,
    is_private BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Followers Table
```sql
CREATE TABLE followers (
    follower_id BIGINT NOT NULL,
    following_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES users(user_id),
    FOREIGN KEY (following_id) REFERENCES users(user_id)
);
```

#### Tweets Table
```sql
CREATE TABLE tweets (
    tweet_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    tweet_type ENUM('tweet', 'retweet', 'quote', 'reply') DEFAULT 'tweet',
    parent_tweet_id BIGINT,
    quoted_tweet_id BIGINT,
    retweeted_tweet_id BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (parent_tweet_id) REFERENCES tweets(tweet_id),
    FOREIGN KEY (quoted_tweet_id) REFERENCES tweets(tweet_id),
    FOREIGN KEY (retweeted_tweet_id) REFERENCES tweets(tweet_id)
);
```

#### Likes Table
```sql
CREATE TABLE likes (
    user_id BIGINT NOT NULL,
    tweet_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, tweet_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (tweet_id) REFERENCES tweets(tweet_id)
);
```

#### Hashtags Table
```sql
CREATE TABLE hashtags (
    hashtag_id BIGINT PRIMARY KEY,
    hashtag VARCHAR(50) UNIQUE NOT NULL,
    tweet_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Tweet_Hashtags Table
```sql
CREATE TABLE tweet_hashtags (
    tweet_id BIGINT NOT NULL,
    hashtag_id BIGINT NOT NULL,
    PRIMARY KEY (tweet_id, hashtag_id),
    FOREIGN KEY (tweet_id) REFERENCES tweets(tweet_id),
    FOREIGN KEY (hashtag_id) REFERENCES hashtags(hashtag_id)
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
  - Account verification
  - Privacy settings
- **Database**: Users table
- **Technology**: Spring Boot, Node.js

#### 3. **Tweet Service**
- **Responsibilities**:
  - Tweet creation and management
  - Retweet and quote functionality
  - Tweet deletion and moderation
  - Media upload handling
- **Database**: Tweets, Hashtags, Tweet_Hashtags tables
- **Technology**: Spring Boot, Java

#### 4. **Timeline Service** (Core Service)
- **Responsibilities**:
  - Home timeline generation
  - User profile timeline
  - Real-time timeline updates
  - Timeline optimization
- **Algorithm**: 
  - Fan-out on write
  - Pull model for inactive users
  - Hybrid approach for active users
- **Technology**: Spring Boot, Java

#### 5. **Social Service**
- **Responsibilities**:
  - Follow/unfollow functionality
  - Like/unlike tweets
  - Reply management
  - Social graph maintenance
- **Database**: Followers, Likes tables
- **Technology**: Spring Boot, Java

#### 6. **Search Service**
- **Responsibilities**:
  - Tweet search
  - User search
  - Hashtag search
  - Search suggestions
- **Technology**: Elasticsearch, Apache Solr

#### 7. **Notification Service**
- **Responsibilities**:
  - Push notifications
  - Email notifications
  - In-app notifications
  - Notification preferences
- **Technology**: Firebase Cloud Messaging, SendGrid

## Timeline Generation Strategies

### 1. **Fan-out on Write (Push Model)**
```java
public class TimelineService {
    
    public void postTweet(Tweet tweet) {
        // 1. Save tweet to database
        saveTweet(tweet);
        
        // 2. Get user's followers
        List<User> followers = getFollowers(tweet.getUserId());
        
        // 3. Fan-out to followers' timelines
        for (User follower : followers) {
            addToTimeline(follower.getUserId(), tweet);
        }
    }
    
    private void addToTimeline(Long userId, Tweet tweet) {
        // Add to Redis timeline
        redisTemplate.opsForZSet().add(
            "timeline:" + userId, 
            tweet.getTweetId(), 
            tweet.getCreatedAt().getTime()
        );
        
        // Keep only recent tweets (e.g., last 1000)
        redisTemplate.opsForZSet().removeRange(
            "timeline:" + userId, 
            0, 
            -1001
        );
    }
}
```

### 2. **Pull Model (On-demand)**
```java
public class TimelineService {
    
    public List<Tweet> getTimeline(Long userId, int page, int pageSize) {
        // 1. Get user's following list
        List<Long> followingIds = getFollowingIds(userId);
        
        // 2. Query tweets from followed users
        List<Tweet> tweets = tweetRepository.findByUserIdInOrderByCreatedAtDesc(
            followingIds, 
            PageRequest.of(page, pageSize)
        );
        
        // 3. Apply ranking algorithm
        return rankTweets(tweets, userId);
    }
}
```

### 3. **Hybrid Approach**
```java
public class TimelineService {
    
    public List<Tweet> getTimeline(Long userId, int page, int pageSize) {
        User user = getUser(userId);
        
        if (user.isActive()) {
            // Use fan-out model for active users
            return getTimelineFromCache(userId, page, pageSize);
        } else {
            // Use pull model for inactive users
            return getTimelineFromDatabase(userId, page, pageSize);
        }
    }
}
```

## Trending Topics Algorithm

### 1. **Hashtag Processing**
```java
public class HashtagService {
    
    public void processTweetHashtags(Tweet tweet) {
        // Extract hashtags from tweet content
        List<String> hashtags = extractHashtags(tweet.getContent());
        
        for (String hashtag : hashtags) {
            // Update hashtag count
            incrementHashtagCount(hashtag);
            
            // Add to trending calculation
            addToTrendingCalculation(hashtag, tweet.getCreatedAt());
        }
    }
    
    private List<String> extractHashtags(String content) {
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(content);
        
        List<String> hashtags = new ArrayList<>();
        while (matcher.find()) {
            hashtags.add(matcher.group().toLowerCase());
        }
        
        return hashtags;
    }
}
```

### 2. **Trending Calculation**
```java
public class TrendingService {
    
    public List<TrendingTopic> getTrendingTopics() {
        // Calculate trending score for each hashtag
        List<TrendingTopic> trendingTopics = hashtagRepository.findAll().stream()
            .map(hashtag -> new TrendingTopic(
                hashtag.getHashtag(),
                calculateTrendingScore(hashtag)
            ))
            .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
            .limit(10)
            .collect(Collectors.toList());
        
        return trendingTopics;
    }
    
    private double calculateTrendingScore(Hashtag hashtag) {
        // Factors:
        // - Recent tweet volume
        // - Tweet velocity (tweets per hour)
        // - User engagement (likes, retweets)
        // - Time decay
        
        double recentVolume = getRecentTweetVolume(hashtag);
        double velocity = getTweetVelocity(hashtag);
        double engagement = getEngagementScore(hashtag);
        double timeDecay = getTimeDecay();
        
        return (recentVolume * 0.4) + (velocity * 0.3) + (engagement * 0.2) + (timeDecay * 0.1);
    }
}
```

## Real-time Features

### 1. **WebSocket for Live Updates**
```java
@ServerEndpoint("/timeline/{userId}")
public class TimelineWebSocket {
    
    @OnMessage
    public void handleTimelineUpdate(String message, Session session) {
        // Parse message
        TimelineUpdate update = parseMessage(message);
        
        // Update timeline in real-time
        updateUserTimeline(update.getUserId(), update.getNewTweet());
        
        // Broadcast to connected clients
        broadcastToFollowers(update.getUserId(), update);
    }
}
```

### 2. **Real-time Notifications**
```java
public class NotificationService {
    
    public void sendLikeNotification(Long likerId, Long tweetId) {
        Tweet tweet = getTweet(tweetId);
        User tweetOwner = getUser(tweet.getUserId());
        
        // Create notification
        Notification notification = new Notification(
            tweetOwner.getUserId(),
            likerId,
            "like",
            tweetId
        );
        
        // Send real-time notification
        sendRealTimeNotification(notification);
        
        // Send push notification if user is offline
        if (!isUserOnline(tweetOwner.getUserId())) {
            sendPushNotification(tweetOwner, notification);
        }
    }
}
```

## Search Implementation

### 1. **Elasticsearch Index**
```json
{
  "mappings": {
    "properties": {
      "tweet_id": { "type": "long" },
      "user_id": { "type": "long" },
      "content": { 
        "type": "text",
        "analyzer": "standard"
      },
      "hashtags": { 
        "type": "keyword" 
      },
      "created_at": { "type": "date" },
      "like_count": { "type": "integer" },
      "retweet_count": { "type": "integer" }
    }
  }
}
```

### 2. **Search Service**
```java
public class SearchService {
    
    public SearchResult searchTweets(String query, int page, int pageSize) {
        // Build Elasticsearch query
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
            .must(QueryBuilders.multiMatchQuery(query, "content", "hashtags"))
            .filter(QueryBuilders.termQuery("is_deleted", false));
        
        // Execute search
        SearchRequest searchRequest = new SearchRequest("tweets")
            .source(new SearchSourceBuilder()
                .query(queryBuilder)
                .sort("created_at", SortOrder.DESC)
                .from(page * pageSize)
                .size(pageSize));
        
        SearchResponse response = elasticsearchClient.search(searchRequest);
        
        // Process results
        return processSearchResults(response);
    }
}
```

## Scalability Solutions

### 1. **Database Scaling**
- **Read Replicas**: Multiple read replicas for timeline queries
- **Sharding**: Shard by user_id for user-specific data
- **Caching**: Redis for frequently accessed data

### 2. **Timeline Optimization**
```
┌─────────────────┐
│   CDN Layer     │ ← Static content (images, videos)
└─────────────────┘
         │
┌─────────────────┐
│ Application Cache│ ← User sessions, timeline data
│    (Redis)      │
└─────────────────┘
         │
┌─────────────────┐
│ Database Cache  │ ← User profiles, tweet data
│    (Redis)      │
└─────────────────┘
```

### 3. **Message Queues**
- **Apache Kafka**: For real-time tweet distribution
- **RabbitMQ**: For notification processing
- **AWS SQS**: For asynchronous task processing

## Security Considerations

### 1. **Rate Limiting**
```java
public class RateLimitService {
    
    public boolean checkRateLimit(Long userId, String action) {
        String key = "rate_limit:" + userId + ":" + action;
        
        Long count = redisTemplate.opsForValue().increment(key);
        
        if (count == 1) {
            redisTemplate.expire(key, getRateLimitWindow(action), TimeUnit.SECONDS);
        }
        
        return count <= getRateLimit(action);
    }
    
    private int getRateLimit(String action) {
        switch (action) {
            case "tweet": return 300; // 300 tweets per hour
            case "follow": return 1000; // 1000 follows per hour
            case "like": return 1000; // 1000 likes per hour
            default: return 100;
        }
    }
}
```

### 2. **Content Moderation**
- **Automated Filtering**: ML-based content moderation
- **Manual Review**: Human review for flagged content
- **Spam Detection**: Pattern-based spam detection

## Monitoring & Analytics

### 1. **User Engagement Metrics**
- **Daily Active Users (DAU)**: Number of active users per day
- **Tweet Engagement Rate**: Likes, retweets, replies per tweet
- **Timeline Scroll Depth**: How far users scroll in timeline
- **Search Usage**: Number of searches per day

### 2. **Content Performance**
- **Tweet Reach**: Number of users who see a tweet
- **Viral Coefficient**: How much tweets spread
- **Hashtag Performance**: Trending hashtag analytics
- **User Growth**: New user registration rate

### 3. **System Performance**
- **Timeline Generation Time**: Time to generate personalized timeline
- **Search Response Time**: Time for search results
- **API Response Time**: Response time for all API calls
- **Error Rates**: Percentage of failed requests

## Technology Stack

### **Backend**
- **Languages**: Java (Spring Boot), Scala (Play Framework)
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
- **Daily Active Users**: 300M
- **Daily Tweets**: 500M
- **Peak Concurrent Users**: 100K
- **Storage**: 50PB+ total storage

### 2. **Timeline Algorithm Trade-offs**
- **Fan-out vs. Pull**: Push model for active users, pull for inactive
- **Consistency vs. Performance**: Eventual consistency for timeline
- **Storage vs. Computation**: Cache timeline vs. compute on-demand

### 3. **Search Optimization**
- **Real-time vs. Batch**: Real-time indexing vs. batch processing
- **Relevance vs. Recency**: Balancing search relevance with recency
- **Global vs. Local**: Global search vs. local trending

## Conclusion

This Twitter system design demonstrates:
- **Scalable Architecture**: Microservices with horizontal scaling
- **Real-time Processing**: WebSocket for live updates
- **Advanced Search**: Elasticsearch for fast search
- **Timeline Optimization**: Hybrid fan-out/pull model
- **Trending Algorithm**: Sophisticated trending topic calculation
- **Performance Optimization**: Multi-layer caching and CDN

The design prioritizes real-time performance, search capabilities, and scalability while maintaining high availability and user experience. 