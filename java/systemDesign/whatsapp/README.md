# Design WhatsApp - System Design Interview

## Problem Statement

Design an instant messaging platform like WhatsApp that supports text messages, media sharing, voice/video calls, and group chats with end-to-end encryption.

## Functional Requirements

### Core Features
1. **User Management**
   - User registration and authentication
   - Profile creation and management
   - Contact synchronization
   - Account verification

2. **Messaging**
   - One-to-one messaging
   - Group messaging
   - Media sharing (photos, videos, documents)
   - Voice messages
   - Message status (sent, delivered, read)

3. **Real-time Communication**
   - Instant message delivery
   - Online/offline status
   - Typing indicators
   - Last seen functionality

4. **Voice & Video Calls**
   - One-to-one voice calls
   - Group voice calls
   - Video calls
   - Call quality adaptation

5. **Security & Privacy**
   - End-to-end encryption
   - Message encryption
   - Privacy settings
   - Two-factor authentication

## Non-Functional Requirements

### Scalability
- **Users**: 2B+ active users
- **Daily Messages**: 100B+ messages per day
- **Peak Load**: 1M+ concurrent users
- **Response Time**: < 100ms for message delivery

### Availability
- **Uptime**: 99.9% availability
- **Fault Tolerance**: Graceful degradation during failures

### Performance
- **Message Delivery**: < 50ms for instant delivery
- **Media Upload**: < 2 seconds for photos
- **Call Setup**: < 1 second for call initiation

## System Architecture

### High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Mobile Apps   │    │   Web Client    │    │   Desktop Apps  │
│  (iOS/Android)  │    │   (React/Ang)   │    │   (Electron)    │
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
│  User Service   │    │  Message Service│    │  Media Service  │
│  (Authentication)│    │  (Chat Mgmt)    │    │  (File Upload)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │  Call Service   │
                    │  (Voice/Video)  │
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  Encryption     │    │  Notification   │    │  Analytics      │
│  Service        │    │  Service        │    │  Service        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Design

#### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255),
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100),
    profile_picture_url VARCHAR(500),
    status_message VARCHAR(255),
    is_online BOOLEAN DEFAULT FALSE,
    last_seen TIMESTAMP,
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Contacts Table
```sql
CREATE TABLE contacts (
    contact_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    contact_phone_number VARCHAR(20) NOT NULL,
    contact_name VARCHAR(100),
    contact_picture_url VARCHAR(500),
    is_blocked BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    UNIQUE KEY unique_contact (user_id, contact_phone_number)
);
```

#### Chats Table
```sql
CREATE TABLE chats (
    chat_id BIGINT PRIMARY KEY,
    chat_type ENUM('individual', 'group') DEFAULT 'individual',
    chat_name VARCHAR(100),
    chat_picture_url VARCHAR(500),
    created_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(user_id)
);
```

#### Chat_Participants Table
```sql
CREATE TABLE chat_participants (
    chat_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role ENUM('admin', 'member') DEFAULT 'member',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (chat_id, user_id),
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

#### Messages Table
```sql
CREATE TABLE messages (
    message_id BIGINT PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    message_type ENUM('text', 'image', 'video', 'audio', 'document', 'location') DEFAULT 'text',
    content TEXT,
    media_url VARCHAR(500),
    media_size BIGINT,
    media_duration INT,
    reply_to_message_id BIGINT,
    is_forwarded BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id),
    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (reply_to_message_id) REFERENCES messages(message_id)
);
```

#### Message_Status Table
```sql
CREATE TABLE message_status (
    message_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status ENUM('sent', 'delivered', 'read') DEFAULT 'sent',
    status_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (message_id, user_id),
    FOREIGN KEY (message_id) REFERENCES messages(message_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
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
  - Contact synchronization
  - Account verification
- **Database**: Users, Contacts tables
- **Technology**: Spring Boot, Node.js

#### 3. **Message Service** (Core Service)
- **Responsibilities**:
  - Message creation and delivery
  - Chat management
  - Message status tracking
  - Real-time messaging
- **Database**: Chats, Messages, Message_Status tables
- **Technology**: Spring Boot, Java

#### 4. **Media Service**
- **Responsibilities**:
  - Media upload and processing
  - File compression and optimization
  - Media storage management
  - CDN integration
- **Technology**: Spring Boot, Java

#### 5. **Call Service**
- **Responsibilities**:
  - Voice and video call management
  - WebRTC signaling
  - Call quality monitoring
  - Call recording (if applicable)
- **Technology**: WebRTC, Spring Boot

#### 6. **Encryption Service**
- **Responsibilities**:
  - End-to-end encryption
  - Key management
  - Message encryption/decryption
  - Security compliance
- **Technology**: Signal Protocol, Java

#### 7. **Notification Service**
- **Responsibilities**:
  - Push notifications
  - Message delivery notifications
  - Call notifications
  - Notification preferences
- **Technology**: Firebase Cloud Messaging, SendGrid

## End-to-End Encryption

### 1. **Signal Protocol Implementation**
```java
public class EncryptionService {
    
    public EncryptedMessage encryptMessage(Message message, User recipient) {
        // 1. Get recipient's public key
        PublicKey recipientKey = getPublicKey(recipient.getUserId());
        
        // 2. Generate session key
        byte[] sessionKey = generateSessionKey();
        
        // 3. Encrypt message content
        byte[] encryptedContent = encryptWithAES(message.getContent(), sessionKey);
        
        // 4. Encrypt session key with recipient's public key
        byte[] encryptedSessionKey = encryptWithRSA(sessionKey, recipientKey);
        
        return new EncryptedMessage(encryptedContent, encryptedSessionKey);
    }
    
    public String decryptMessage(EncryptedMessage encryptedMessage, User recipient) {
        // 1. Get recipient's private key
        PrivateKey privateKey = getPrivateKey(recipient.getUserId());
        
        // 2. Decrypt session key
        byte[] sessionKey = decryptWithRSA(encryptedMessage.getEncryptedSessionKey(), privateKey);
        
        // 3. Decrypt message content
        return decryptWithAES(encryptedMessage.getEncryptedContent(), sessionKey);
    }
}
```

### 2. **Key Management**
```java
public class KeyManagementService {
    
    public void generateKeyPair(Long userId) {
        // Generate RSA key pair
        KeyPair keyPair = generateRSAKeyPair();
        
        // Store public key in database
        storePublicKey(userId, keyPair.getPublic());
        
        // Store private key securely (encrypted)
        storePrivateKey(userId, encryptPrivateKey(keyPair.getPrivate()));
    }
    
    public void rotateKeys(Long userId) {
        // Generate new key pair
        generateKeyPair(userId);
        
        // Notify contacts about key change
        notifyKeyChange(userId);
    }
}
```

## Real-time Messaging

### 1. **WebSocket Implementation**
```java
@ServerEndpoint("/chat/{userId}")
public class ChatWebSocket {
    
    private static Map<Long, Session> userSessions = new ConcurrentHashMap<>();
    
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        userSessions.put(userId, session);
        updateUserStatus(userId, true);
    }
    
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") Long userId) {
        // Parse message
        ChatMessage chatMessage = parseMessage(message);
        
        // Process message
        processMessage(chatMessage);
        
        // Broadcast to recipients
        broadcastMessage(chatMessage);
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("userId") Long userId) {
        userSessions.remove(userId);
        updateUserStatus(userId, false);
    }
    
    private void broadcastMessage(ChatMessage message) {
        // Get chat participants
        List<Long> participants = getChatParticipants(message.getChatId());
        
        // Send to online participants
        for (Long participantId : participants) {
            if (participantId.equals(message.getSenderId())) {
                continue; // Skip sender
            }
            
            Session participantSession = userSessions.get(participantId);
            if (participantSession != null && participantSession.isOpen()) {
                participantSession.getAsyncRemote().sendText(message.toJson());
            } else {
                // Send push notification for offline users
                sendPushNotification(participantId, message);
            }
        }
    }
}
```

### 2. **Message Queue for Reliability**
```java
public class MessageQueueService {
    
    public void sendMessage(ChatMessage message) {
        // 1. Save message to database
        saveMessage(message);
        
        // 2. Send to message queue for delivery
        messageProducer.send(new MessageEvent(message));
        
        // 3. Update message status
        updateMessageStatus(message.getMessageId(), "sent");
    }
    
    @KafkaListener(topics = "message-delivery")
    public void processMessageDelivery(MessageEvent event) {
        ChatMessage message = event.getMessage();
        
        // Get chat participants
        List<Long> participants = getChatParticipants(message.getChatId());
        
        for (Long participantId : participants) {
            if (participantId.equals(message.getSenderId())) {
                continue;
            }
            
            // Try to deliver message
            if (deliverMessage(participantId, message)) {
                updateMessageStatus(message.getMessageId(), participantId, "delivered");
            } else {
                // Queue for retry
                retryMessageDelivery(participantId, message);
            }
        }
    }
}
```

## Group Chat Management

### 1. **Group Creation and Management**
```java
public class GroupChatService {
    
    public Chat createGroupChat(String groupName, Long creatorId, List<Long> memberIds) {
        // 1. Create chat record
        Chat chat = new Chat();
        chat.setChatType("group");
        chat.setChatName(groupName);
        chat.setCreatedBy(creatorId);
        chat = chatRepository.save(chat);
        
        // 2. Add participants
        for (Long memberId : memberIds) {
            ChatParticipant participant = new ChatParticipant();
            participant.setChatId(chat.getChatId());
            participant.setUserId(memberId);
            participant.setRole(memberId.equals(creatorId) ? "admin" : "member");
            chatParticipantRepository.save(participant);
        }
        
        // 3. Send group creation notification
        sendGroupCreationNotification(chat, memberIds);
        
        return chat;
    }
    
    public void addGroupMember(Long chatId, Long adminId, Long newMemberId) {
        // Verify admin permissions
        if (!isGroupAdmin(chatId, adminId)) {
            throw new UnauthorizedException("Only admins can add members");
        }
        
        // Add new member
        ChatParticipant participant = new ChatParticipant();
        participant.setChatId(chatId);
        participant.setUserId(newMemberId);
        participant.setRole("member");
        chatParticipantRepository.save(participant);
        
        // Send notification to group
        sendMemberAddedNotification(chatId, newMemberId);
    }
}
```

### 2. **Group Message Broadcasting**
```java
public class GroupMessageService {
    
    public void sendGroupMessage(ChatMessage message) {
        // 1. Get group participants
        List<Long> participants = getGroupParticipants(message.getChatId());
        
        // 2. Send to each participant
        for (Long participantId : participants) {
            if (participantId.equals(message.getSenderId())) {
                continue;
            }
            
            // Check if user is blocked
            if (isUserBlocked(participantId, message.getSenderId())) {
                continue;
            }
            
            // Send message
            sendMessageToUser(participantId, message);
        }
    }
}
```

## Voice and Video Calls

### 1. **Call Signaling**
```java
public class CallService {
    
    public CallSession initiateCall(Long callerId, Long calleeId, CallType callType) {
        // 1. Create call session
        CallSession session = new CallSession();
        session.setCallerId(callerId);
        session.setCalleeId(calleeId);
        session.setCallType(callType);
        session.setStatus("ringing");
        session = callSessionRepository.save(session);
        
        // 2. Send call invitation
        sendCallInvitation(session);
        
        // 3. Start call timeout
        scheduleCallTimeout(session.getCallId());
        
        return session;
    }
    
    public void acceptCall(Long callId, Long calleeId) {
        CallSession session = callSessionRepository.findById(callId)
            .orElseThrow(() -> new CallNotFoundException("Call not found"));
        
        if (!session.getCalleeId().equals(calleeId)) {
            throw new UnauthorizedException("Not authorized to accept this call");
        }
        
        // Update call status
        session.setStatus("connected");
        session.setConnectedAt(new Date());
        callSessionRepository.save(session);
        
        // Send acceptance notification
        sendCallAcceptedNotification(session);
    }
}
```

### 2. **WebRTC Integration**
```java
public class WebRTCService {
    
    public void handleSignalingMessage(SignalingMessage message) {
        switch (message.getType()) {
            case "offer":
                handleOffer(message);
                break;
            case "answer":
                handleAnswer(message);
                break;
            case "ice-candidate":
                handleIceCandidate(message);
                break;
            case "hangup":
                handleHangup(message);
                break;
        }
    }
    
    private void handleOffer(SignalingMessage message) {
        // Forward offer to callee
        Long calleeId = getCalleeId(message.getCallId());
        sendSignalingMessage(calleeId, message);
    }
    
    private void handleAnswer(SignalingMessage message) {
        // Forward answer to caller
        Long callerId = getCallerId(message.getCallId());
        sendSignalingMessage(callerId, message);
    }
}
```

## Media Handling

### 1. **Media Upload and Processing**
```java
public class MediaService {
    
    public MediaUploadResponse uploadMedia(MultipartFile file, Long userId) {
        // 1. Validate file
        validateFile(file);
        
        // 2. Generate unique filename
        String filename = generateUniqueFilename(file.getOriginalFilename());
        
        // 3. Upload to cloud storage
        String mediaUrl = uploadToCloudStorage(file, filename);
        
        // 4. Process media (compress, generate thumbnails)
        MediaMetadata metadata = processMedia(file, mediaUrl);
        
        // 5. Save metadata
        saveMediaMetadata(userId, filename, mediaUrl, metadata);
        
        return new MediaUploadResponse(mediaUrl, metadata);
    }
    
    private MediaMetadata processMedia(MultipartFile file, String mediaUrl) {
        String fileType = getFileType(file.getContentType());
        
        switch (fileType) {
            case "image":
                return processImage(file, mediaUrl);
            case "video":
                return processVideo(file, mediaUrl);
            case "audio":
                return processAudio(file, mediaUrl);
            default:
                return processDocument(file, mediaUrl);
        }
    }
}
```

### 2. **Media Compression**
```java
public class MediaCompressionService {
    
    public byte[] compressImage(byte[] originalImage, ImageFormat format) {
        // Use appropriate compression based on format
        switch (format) {
            case JPEG:
                return compressJPEG(originalImage, 0.8f);
            case PNG:
                return compressPNG(originalImage);
            case WEBP:
                return compressWebP(originalImage, 0.8f);
            default:
                return originalImage;
        }
    }
    
    public byte[] compressVideo(byte[] originalVideo) {
        // Use FFmpeg for video compression
        return ffmpegService.compressVideo(originalVideo, VideoCodec.H264, 720);
    }
}
```

## Security and Privacy

### 1. **Message Encryption**
```java
public class MessageEncryptionService {
    
    public EncryptedMessage encryptMessage(Message message, List<Long> recipients) {
        // 1. Generate message key
        byte[] messageKey = generateMessageKey();
        
        // 2. Encrypt message content
        byte[] encryptedContent = encryptWithAES(message.getContent(), messageKey);
        
        // 3. Encrypt message key for each recipient
        Map<Long, byte[]> encryptedKeys = new HashMap<>();
        for (Long recipientId : recipients) {
            PublicKey recipientKey = getPublicKey(recipientId);
            encryptedKeys.put(recipientId, encryptWithRSA(messageKey, recipientKey));
        }
        
        return new EncryptedMessage(encryptedContent, encryptedKeys);
    }
}
```

### 2. **Privacy Controls**
```java
public class PrivacyService {
    
    public boolean canUserSeeLastSeen(Long viewerId, Long targetId) {
        UserPrivacySettings settings = getUserPrivacySettings(targetId);
        
        switch (settings.getLastSeenVisibility()) {
            case "everyone":
                return true;
            case "contacts":
                return areContacts(viewerId, targetId);
            case "nobody":
                return false;
            default:
                return false;
        }
    }
    
    public boolean canUserSeeProfilePicture(Long viewerId, Long targetId) {
        UserPrivacySettings settings = getUserPrivacySettings(targetId);
        
        switch (settings.getProfilePictureVisibility()) {
            case "everyone":
                return true;
            case "contacts":
                return areContacts(viewerId, targetId);
            case "nobody":
                return false;
            default:
                return false;
        }
    }
}
```

## Monitoring & Analytics

### 1. **Message Delivery Metrics**
- **Message Delivery Rate**: Percentage of messages delivered successfully
- **Average Delivery Time**: Time from send to delivery
- **Failed Delivery Rate**: Percentage of failed deliveries
- **Retry Success Rate**: Success rate of retry attempts

### 2. **User Engagement Metrics**
- **Daily Active Users (DAU)**: Number of active users per day
- **Messages per User**: Average messages sent per user
- **Group Chat Participation**: Percentage of users in group chats
- **Call Duration**: Average call duration

### 3. **System Performance**
- **WebSocket Connection Count**: Number of active connections
- **Message Queue Latency**: Time in message queue
- **Media Upload Success Rate**: Percentage of successful uploads
- **Call Quality Metrics**: Call quality and drop rates

## Technology Stack

### **Backend**
- **Languages**: Java (Spring Boot), Erlang (for real-time)
- **Databases**: MySQL, PostgreSQL, Redis, MongoDB
- **Message Queues**: Apache Kafka, RabbitMQ
- **Real-time**: WebSocket, Server-Sent Events

### **Frontend**
- **Mobile**: React Native, Flutter
- **Web**: React, Angular
- **Desktop**: Electron

### **Infrastructure**
- **Cloud**: AWS, Google Cloud, Azure
- **CDN**: CloudFront, Akamai
- **Media Storage**: Amazon S3, Google Cloud Storage
- **Monitoring**: Prometheus, Grafana, ELK Stack

## Interview Discussion Points

### 1. **Scale Estimation**
- **Daily Active Users**: 1.5B
- **Daily Messages**: 100B
- **Peak Concurrent Users**: 1M
- **Storage**: 100PB+ total storage

### 2. **Encryption Trade-offs**
- **Security vs. Performance**: Strong encryption vs. fast delivery
- **Key Management**: Centralized vs. distributed key management
- **Backup vs. Privacy**: Message backup vs. privacy protection

### 3. **Real-time Delivery Optimization**
- **WebSocket vs. Polling**: Real-time vs. polling for message delivery
- **Reliability vs. Speed**: Guaranteed delivery vs. instant delivery
- **Global vs. Local**: Global message routing vs. local optimization

## Conclusion

This WhatsApp system design demonstrates:
- **Scalable Architecture**: Microservices with horizontal scaling
- **End-to-End Encryption**: Signal Protocol for message security
- **Real-time Communication**: WebSocket for instant messaging
- **Media Handling**: Efficient media upload and processing
- **Group Chat Management**: Sophisticated group functionality
- **Voice/Video Calls**: WebRTC for real-time communication

The design prioritizes security, real-time performance, and user privacy while maintaining high availability and scalability. 