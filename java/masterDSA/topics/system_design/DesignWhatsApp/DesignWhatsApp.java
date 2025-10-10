package masterDSA.topics.system_design.DesignWhatsApp;
import java.util.*;


/**
 * Design WhatsApp
 * 
 * Problem Statement:
 * Design a simplified version of WhatsApp with features like user registration,
 * messaging, group chats, status updates, and file sharing.
 * 
 * LeetCode Link: N/A (System Design Problem)
 * Difficulty: Hard
 * Tags: System Design, Hash Table, Message Queue, Real-time Communication
 * 
 * Approach Summary:
 * 1. Use HashMap to store users, chats, and messages
 * 2. Implement message queue for real-time delivery
 * 3. Use Graph structure for user relationships
 * 4. Handle group chat management
 * 5. Implement status updates and file sharing
 * 
 * Pseudocode:
 * 1. Create User, Chat, Message, and Group classes
 * 2. Implement message sending and receiving
 * 3. Handle group chat operations
 * 4. Implement status updates
 * 5. Handle file sharing and media messages
 * 
 * Time Complexity: O(1) for most operations, O(n) for group operations
 * Space Complexity: O(n + m) where n is users, m is messages
 */
public class DesignWhatsApp {
    
    // User class
    static class User {
        int userId;
        String name;
        String phoneNumber;
        String profilePicture;
        String status;
        long lastSeen;
        boolean isOnline;
        Set<Integer> contacts;
        List<StatusUpdate> statusUpdates;
        
        User(int userId, String name, String phoneNumber) {
            this.userId = userId;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.profilePicture = "";
            this.status = "Hey there! I am using WhatsApp";
            this.lastSeen = System.currentTimeMillis();
            this.isOnline = false;
            this.contacts = new HashSet<>();
            this.statusUpdates = new ArrayList<>();
        }
        
        void updateStatus(String status) {
            this.status = status;
        }
        
        void setOnline(boolean online) {
            this.isOnline = online;
            if (!online) {
                this.lastSeen = System.currentTimeMillis();
            }
        }
        
        void addContact(int contactId) {
            contacts.add(contactId);
        }
        
        void removeContact(int contactId) {
            contacts.remove(contactId);
        }
        
        void addStatusUpdate(StatusUpdate statusUpdate) {
            statusUpdates.add(statusUpdate);
        }
    }
    
    // Chat class
    static class Chat {
        int chatId;
        ChatType type;
        String name;
        Set<Integer> participants;
        List<Message> messages;
        int lastMessageId;
        long lastActivity;
        
        Chat(int chatId, ChatType type, String name) {
            this.chatId = chatId;
            this.type = type;
            this.name = name;
            this.participants = new HashSet<>();
            this.messages = new ArrayList<>();
            this.lastMessageId = 0;
            this.lastActivity = System.currentTimeMillis();
        }
        
        void addParticipant(int userId) {
            participants.add(userId);
        }
        
        void removeParticipant(int userId) {
            participants.remove(userId);
        }
        
        void addMessage(Message message) {
            messages.add(message);
            lastMessageId = message.messageId;
            lastActivity = System.currentTimeMillis();
        }
        
        boolean hasParticipant(int userId) {
            return participants.contains(userId);
        }
    }
    
    // Message class
    static class Message {
        int messageId;
        int chatId;
        int senderId;
        String content;
        MessageType type;
        long timestamp;
        MessageStatus status;
        Map<String, Object> metadata;
        
        Message(int messageId, int chatId, int senderId, String content, MessageType type) {
            this.messageId = messageId;
            this.chatId = chatId;
            this.senderId = senderId;
            this.content = content;
            this.type = type;
            this.timestamp = System.currentTimeMillis();
            this.status = MessageStatus.SENT;
            this.metadata = new HashMap<>();
        }
        
        void updateStatus(MessageStatus status) {
            this.status = status;
        }
    }
    
    // Group class
    static class Group {
        int groupId;
        String name;
        String description;
        int adminId;
        Set<Integer> members;
        Set<Integer> admins;
        String groupPicture;
        long createdAt;
        
        Group(int groupId, String name, int adminId) {
            this.groupId = groupId;
            this.name = name;
            this.description = "";
            this.adminId = adminId;
            this.members = new HashSet<>();
            this.admins = new HashSet<>();
            this.groupPicture = "";
            this.createdAt = System.currentTimeMillis();
            
            this.members.add(adminId);
            this.admins.add(adminId);
        }
        
        void addMember(int userId) {
            members.add(userId);
        }
        
        void removeMember(int userId) {
            members.remove(userId);
            admins.remove(userId);
        }
        
        void addAdmin(int userId) {
            if (members.contains(userId)) {
                admins.add(userId);
            }
        }
        
        void removeAdmin(int userId) {
            if (userId != adminId) { // Cannot remove main admin
                admins.remove(userId);
            }
        }
        
        boolean isAdmin(int userId) {
            return admins.contains(userId);
        }
        
        boolean isMember(int userId) {
            return members.contains(userId);
        }
    }
    
    // Status update class
    static class StatusUpdate {
        int statusId;
        int userId;
        String content;
        StatusType type;
        long timestamp;
        long expiresAt;
        Set<Integer> viewers;
        
        StatusUpdate(int statusId, int userId, String content, StatusType type) {
            this.statusId = statusId;
            this.userId = userId;
            this.content = content;
            this.type = type;
            this.timestamp = System.currentTimeMillis();
            this.expiresAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000); // 24 hours
            this.viewers = new HashSet<>();
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
        
        void addViewer(int userId) {
            viewers.add(userId);
        }
    }
    
    // File sharing class
    static class FileShare {
        int fileId;
        String fileName;
        String fileType;
        long fileSize;
        String filePath;
        int uploadedBy;
        long uploadedAt;
        
        FileShare(int fileId, String fileName, String fileType, long fileSize, String filePath, int uploadedBy) {
            this.fileId = fileId;
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = fileSize;
            this.filePath = filePath;
            this.uploadedBy = uploadedBy;
            this.uploadedAt = System.currentTimeMillis();
        }
    }
    
    // Enums
    enum ChatType {
        PRIVATE, GROUP
    }
    
    enum MessageType {
        TEXT, IMAGE, VIDEO, AUDIO, DOCUMENT, LOCATION, CONTACT
    }
    
    enum MessageStatus {
        SENT, DELIVERED, READ
    }
    
    enum StatusType {
        TEXT, IMAGE, VIDEO
    }
    
    private Map<Integer, User> users;
    private Map<Integer, Chat> chats;
    private Map<Integer, Group> groups;
    private Map<Integer, Message> messages;
    private Map<Integer, FileShare> files;
    private Map<Integer, List<Integer>> userChats;
    private int nextUserId;
    private int nextChatId;
    private int nextGroupId;
    private int nextMessageId;
    private int nextStatusId;
    private int nextFileId;
    
    public DesignWhatsApp() {
        users = new HashMap<>();
        chats = new HashMap<>();
        groups = new HashMap<>();
        messages = new HashMap<>();
        files = new HashMap<>();
        userChats = new HashMap<>();
        nextUserId = 1;
        nextChatId = 1;
        nextGroupId = 1;
        nextMessageId = 1;
        nextStatusId = 1;
        nextFileId = 1;
    }
    
    /**
     * Register a new user
     * 
     * @param name User's name
     * @param phoneNumber User's phone number
     * @return User ID
     */
    public int registerUser(String name, String phoneNumber) {
        int userId = nextUserId++;
        User user = new User(userId, name, phoneNumber);
        users.put(userId, user);
        userChats.put(userId, new ArrayList<>());
        return userId;
    }
    
    /**
     * Add a contact
     * 
     * @param userId User ID
     * @param contactId Contact's user ID
     * @return True if successful
     */
    public boolean addContact(int userId, int contactId) {
        if (!users.containsKey(userId) || !users.containsKey(contactId)) {
            return false;
        }
        
        users.get(userId).addContact(contactId);
        users.get(contactId).addContact(userId);
        return true;
    }
    
    /**
     * Create a private chat
     * 
     * @param userId1 First user ID
     * @param userId2 Second user ID
     * @return Chat ID
     */
    public int createPrivateChat(int userId1, int userId2) {
        if (!users.containsKey(userId1) || !users.containsKey(userId2)) {
            return -1;
        }
        
        int chatId = nextChatId++;
        Chat chat = new Chat(chatId, ChatType.PRIVATE, "");
        chat.addParticipant(userId1);
        chat.addParticipant(userId2);
        
        chats.put(chatId, chat);
        userChats.get(userId1).add(chatId);
        userChats.get(userId2).add(chatId);
        
        return chatId;
    }
    
    /**
     * Create a group
     * 
     * @param name Group name
     * @param adminId Admin user ID
     * @return Group ID
     */
    public int createGroup(String name, int adminId) {
        if (!users.containsKey(adminId)) {
            return -1;
        }
        
        int groupId = nextGroupId++;
        Group group = new Group(groupId, name, adminId);
        groups.put(groupId, group);
        
        // Create group chat
        int chatId = nextChatId++;
        Chat chat = new Chat(chatId, ChatType.GROUP, name);
        chat.addParticipant(adminId);
        
        chats.put(chatId, chat);
        userChats.get(adminId).add(chatId);
        
        return groupId;
    }
    
    /**
     * Add member to group
     * 
     * @param groupId Group ID
     * @param adminId Admin user ID
     * @param memberId Member user ID
     * @return True if successful
     */
    public boolean addGroupMember(int groupId, int adminId, int memberId) {
        if (!groups.containsKey(groupId) || !users.containsKey(memberId)) {
            return false;
        }
        
        Group group = groups.get(groupId);
        if (!group.isAdmin(adminId)) {
            return false;
        }
        
        group.addMember(memberId);
        
        // Add to group chat
        for (Chat chat : chats.values()) {
            if (chat.type == ChatType.GROUP && chat.name.equals(group.name)) {
                chat.addParticipant(memberId);
                userChats.get(memberId).add(chat.chatId);
                break;
            }
        }
        
        return true;
    }
    
    /**
     * Remove member from group
     * 
     * @param groupId Group ID
     * @param adminId Admin user ID
     * @param memberId Member user ID
     * @return True if successful
     */
    public boolean removeGroupMember(int groupId, int adminId, int memberId) {
        if (!groups.containsKey(groupId)) {
            return false;
        }
        
        Group group = groups.get(groupId);
        if (!group.isAdmin(adminId) || memberId == group.adminId) {
            return false;
        }
        
        group.removeMember(memberId);
        
        // Remove from group chat
        for (Chat chat : chats.values()) {
            if (chat.type == ChatType.GROUP && chat.name.equals(group.name)) {
                chat.removeParticipant(memberId);
                userChats.get(memberId).remove(Integer.valueOf(chat.chatId));
                break;
            }
        }
        
        return true;
    }
    
    /**
     * Send a message
     * 
     * @param chatId Chat ID
     * @param senderId Sender user ID
     * @param content Message content
     * @param type Message type
     * @return Message ID
     */
    public int sendMessage(int chatId, int senderId, String content, MessageType type) {
        if (!chats.containsKey(chatId) || !users.containsKey(senderId)) {
            return -1;
        }
        
        Chat chat = chats.get(chatId);
        if (!chat.hasParticipant(senderId)) {
            return -1;
        }
        
        int messageId = nextMessageId++;
        Message message = new Message(messageId, chatId, senderId, content, type);
        messages.put(messageId, message);
        chat.addMessage(message);
        
        return messageId;
    }
    
    /**
     * Get chat messages
     * 
     * @param chatId Chat ID
     * @param userId User ID
     * @param limit Number of messages to return
     * @return List of message IDs
     */
    public List<Integer> getChatMessages(int chatId, int userId, int limit) {
        List<Integer> result = new ArrayList<>();
        
        if (!chats.containsKey(chatId) || !users.containsKey(userId)) {
            return result;
        }
        
        Chat chat = chats.get(chatId);
        if (!chat.hasParticipant(userId)) {
            return result;
        }
        
        List<Message> chatMessages = new ArrayList<>(chat.messages);
        chatMessages.sort((a, b) -> Long.compare(b.timestamp, a.timestamp));
        
        for (int i = 0; i < Math.min(limit, chatMessages.size()); i++) {
            result.add(chatMessages.get(i).messageId);
        }
        
        return result;
    }
    
    /**
     * Mark message as read
     * 
     * @param messageId Message ID
     * @param userId User ID
     * @return True if successful
     */
    public boolean markMessageAsRead(int messageId, int userId) {
        if (!messages.containsKey(messageId) || !users.containsKey(userId)) {
            return false;
        }
        
        Message message = messages.get(messageId);
        if (message.senderId == userId) {
            return false; // Cannot mark own message as read
        }
        
        message.updateStatus(MessageStatus.READ);
        return true;
    }
    
    /**
     * Create status update
     * 
     * @param userId User ID
     * @param content Status content
     * @param type Status type
     * @return Status ID
     */
    public int createStatusUpdate(int userId, String content, StatusType type) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        int statusId = nextStatusId++;
        StatusUpdate statusUpdate = new StatusUpdate(statusId, userId, content, type);
        users.get(userId).addStatusUpdate(statusUpdate);
        
        return statusId;
    }
    
    /**
     * View status update
     * 
     * @param statusId Status ID
     * @param viewerId Viewer user ID
     * @return True if successful
     */
    public boolean viewStatusUpdate(int statusId, int viewerId) {
        if (!users.containsKey(viewerId)) {
            return false;
        }
        
        for (User user : users.values()) {
            for (StatusUpdate status : user.statusUpdates) {
                if (status.statusId == statusId) {
                    if (status.isExpired()) {
                        return false;
                    }
                    status.addViewer(viewerId);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Get user's status updates
     * 
     * @param userId User ID
     * @return List of status updates
     */
    public List<StatusUpdate> getUserStatusUpdates(int userId) {
        if (!users.containsKey(userId)) {
            return new ArrayList<>();
        }
        
        List<StatusUpdate> result = new ArrayList<>();
        for (StatusUpdate status : users.get(userId).statusUpdates) {
            if (!status.isExpired()) {
                result.add(status);
            }
        }
        
        return result;
    }
    
    /**
     * Upload file
     * 
     * @param fileName File name
     * @param fileType File type
     * @param fileSize File size
     * @param filePath File path
     * @param uploadedBy User ID
     * @return File ID
     */
    public int uploadFile(String fileName, String fileType, long fileSize, String filePath, int uploadedBy) {
        if (!users.containsKey(uploadedBy)) {
            return -1;
        }
        
        int fileId = nextFileId++;
        FileShare file = new FileShare(fileId, fileName, fileType, fileSize, filePath, uploadedBy);
        files.put(fileId, file);
        
        return fileId;
    }
    
    /**
     * Share file in chat
     * 
     * @param chatId Chat ID
     * @param senderId Sender user ID
     * @param fileId File ID
     * @return Message ID
     */
    public int shareFile(int chatId, int senderId, int fileId) {
        if (!files.containsKey(fileId)) {
            return -1;
        }
        
        FileShare file = files.get(fileId);
        String content = "Shared file: " + file.fileName;
        
        return sendMessage(chatId, senderId, content, MessageType.DOCUMENT);
    }
    
    /**
     * Get user's chats
     * 
     * @param userId User ID
     * @return List of chat IDs
     */
    public List<Integer> getUserChats(int userId) {
        if (!userChats.containsKey(userId)) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(userChats.get(userId));
    }
    
    /**
     * Get chat details
     * 
     * @param chatId Chat ID
     * @return Chat details
     */
    public Map<String, Object> getChatDetails(int chatId) {
        Map<String, Object> details = new HashMap<>();
        
        if (!chats.containsKey(chatId)) {
            return details;
        }
        
        Chat chat = chats.get(chatId);
        details.put("chatId", chat.chatId);
        details.put("type", chat.type);
        details.put("name", chat.name);
        details.put("participants", new ArrayList<>(chat.participants));
        details.put("lastMessageId", chat.lastMessageId);
        details.put("lastActivity", chat.lastActivity);
        
        return details;
    }
    
    /**
     * Get message details
     * 
     * @param messageId Message ID
     * @return Message details
     */
    public Map<String, Object> getMessageDetails(int messageId) {
        Map<String, Object> details = new HashMap<>();
        
        if (!messages.containsKey(messageId)) {
            return details;
        }
        
        Message message = messages.get(messageId);
        details.put("messageId", message.messageId);
        details.put("chatId", message.chatId);
        details.put("senderId", message.senderId);
        details.put("content", message.content);
        details.put("type", message.type);
        details.put("timestamp", message.timestamp);
        details.put("status", message.status);
        
        return details;
    }
    
    /**
     * Set user online status
     * 
     * @param userId User ID
     * @param isOnline Online status
     * @return True if successful
     */
    public boolean setUserOnlineStatus(int userId, boolean isOnline) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        users.get(userId).setOnline(isOnline);
        return true;
    }
    
    /**
     * Get user's online status
     * 
     * @param userId User ID
     * @return Online status
     */
    public boolean getUserOnlineStatus(int userId) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        return users.get(userId).isOnline;
    }
    
    /**
     * Get user's last seen time
     * 
     * @param userId User ID
     * @return Last seen timestamp
     */
    public long getUserLastSeen(int userId) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        return users.get(userId).lastSeen;
    }
    
    /**
     * Get user statistics
     * 
     * @param userId User ID
     * @return User statistics
     */
    public Map<String, Object> getUserStats(int userId) {
        Map<String, Object> stats = new HashMap<>();
        
        if (!users.containsKey(userId)) {
            return stats;
        }
        
        User user = users.get(userId);
        stats.put("userId", user.userId);
        stats.put("name", user.name);
        stats.put("phoneNumber", user.phoneNumber);
        stats.put("isOnline", user.isOnline);
        stats.put("lastSeen", user.lastSeen);
        stats.put("contactsCount", user.contacts.size());
        stats.put("chatsCount", userChats.get(userId).size());
        stats.put("statusUpdatesCount", user.statusUpdates.size());
        
        return stats;
    }
    
    /**
     * Demonstration method showing WhatsApp functionality
     */
    public static void runExample() {
        System.out.println("=== Design WhatsApp Example ===");
        
        DesignWhatsApp whatsapp = new DesignWhatsApp();
        
        // Register users
        int alice = whatsapp.registerUser("Alice", "+1234567890");
        int bob = whatsapp.registerUser("Bob", "+0987654321");
        int charlie = whatsapp.registerUser("Charlie", "+1122334455");
        
        System.out.println("Registered users: Alice(" + alice + "), Bob(" + bob + "), Charlie(" + charlie + ")");
        
        // Add contacts
        whatsapp.addContact(alice, bob);
        whatsapp.addContact(alice, charlie);
        whatsapp.addContact(bob, charlie);
        
        System.out.println("Added contacts");
        
        // Create private chat
        int chat1 = whatsapp.createPrivateChat(alice, bob);
        System.out.println("Created private chat between Alice and Bob: " + chat1);
        
        // Send messages
        int msg1 = whatsapp.sendMessage(chat1, alice, "Hello Bob!", MessageType.TEXT);
        int msg2 = whatsapp.sendMessage(chat1, bob, "Hi Alice! How are you?", MessageType.TEXT);
        
        System.out.println("Sent messages: " + msg1 + ", " + msg2);
        
        // Mark message as read
        whatsapp.markMessageAsRead(msg1, bob);
        System.out.println("Bob read Alice's message");
        
        // Create group
        int group1 = whatsapp.createGroup("Friends", alice);
        System.out.println("Created group 'Friends' with Alice as admin: " + group1);
        
        // Add members to group
        whatsapp.addGroupMember(group1, alice, bob);
        whatsapp.addGroupMember(group1, alice, charlie);
        System.out.println("Added Bob and Charlie to group");
        
        // Send group message
        int groupMsg1 = whatsapp.sendMessage(chat1, alice, "Welcome to the group!", MessageType.TEXT);
        System.out.println("Sent group message: " + groupMsg1);
        
        // Create status update
        int status1 = whatsapp.createStatusUpdate(alice, "Having a great day!", StatusType.TEXT);
        System.out.println("Alice created status update: " + status1);
        
        // View status update
        whatsapp.viewStatusUpdate(status1, bob);
        whatsapp.viewStatusUpdate(status1, charlie);
        System.out.println("Bob and Charlie viewed Alice's status");
        
        // Upload file
        int file1 = whatsapp.uploadFile("document.pdf", "application/pdf", 1024000, "/files/document.pdf", alice);
        System.out.println("Alice uploaded file: " + file1);
        
        // Share file
        int fileMsg1 = whatsapp.shareFile(chat1, alice, file1);
        System.out.println("Alice shared file in chat: " + fileMsg1);
        
        // Set online status
        whatsapp.setUserOnlineStatus(alice, true);
        whatsapp.setUserOnlineStatus(bob, false);
        System.out.println("Set online statuses");
        
        // Get chat messages
        List<Integer> messages = whatsapp.getChatMessages(chat1, alice, 10);
        System.out.println("Messages in chat " + chat1 + ": " + messages);
        
        // Get user chats
        List<Integer> aliceChats = whatsapp.getUserChats(alice);
        System.out.println("Alice's chats: " + aliceChats);
        
        // Get chat details
        Map<String, Object> chatDetails = whatsapp.getChatDetails(chat1);
        System.out.println("Chat " + chat1 + " details: " + chatDetails);
        
        // Get message details
        Map<String, Object> messageDetails = whatsapp.getMessageDetails(msg1);
        System.out.println("Message " + msg1 + " details: " + messageDetails);
        
        // Get user statistics
        Map<String, Object> aliceStats = whatsapp.getUserStats(alice);
        System.out.println("Alice's statistics: " + aliceStats);
        
        // Get online status
        boolean aliceOnline = whatsapp.getUserOnlineStatus(alice);
        boolean bobOnline = whatsapp.getUserOnlineStatus(bob);
        System.out.println("Alice online: " + aliceOnline + ", Bob online: " + bobOnline);
        
        // Get last seen
        long aliceLastSeen = whatsapp.getUserLastSeen(alice);
        long bobLastSeen = whatsapp.getUserLastSeen(bob);
        System.out.println("Alice last seen: " + aliceLastSeen + ", Bob last seen: " + bobLastSeen);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
