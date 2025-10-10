package masterDSA.topics.system_design.DesignFacebook;
import java.util.*;


/**
 * Design Facebook
 * 
 * Problem Statement:
 * Design a simplified version of Facebook with features like user registration,
 * posting status updates, adding friends, and viewing news feed.
 * 
 * LeetCode Link: N/A (System Design Problem)
 * Difficulty: Hard
 * Tags: System Design, Hash Table, Graph, Heap
 * 
 * Approach Summary:
 * 1. Use HashMap to store user profiles and relationships
 * 2. Use Graph structure for friend relationships
 * 3. Use PriorityQueue for news feed generation
 * 4. Implement privacy settings and post visibility
 * 5. Handle scalability and performance optimization
 * 
 * Pseudocode:
 * 1. Create User class with profile information
 * 2. Create Post class with content and metadata
 * 3. Maintain friend relationships in adjacency list
 * 4. Generate news feed using graph traversal
 * 5. Implement privacy controls and post filtering
 * 
 * Time Complexity: O(n log n) for news feed generation
 * Space Complexity: O(n + m) where n is users, m is posts
 */
public class DesignFacebook {
    
    // User class to represent a Facebook user
    static class User {
        int userId;
        String name;
        String email;
        Set<Integer> friends;
        List<Post> posts;
        Map<String, String> profile;
        PrivacySettings privacy;
        
        User(int userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.friends = new HashSet<>();
            this.posts = new ArrayList<>();
            this.profile = new HashMap<>();
            this.privacy = new PrivacySettings();
        }
        
        void addFriend(int friendId) {
            friends.add(friendId);
        }
        
        void removeFriend(int friendId) {
            friends.remove(friendId);
        }
        
        void addPost(Post post) {
            posts.add(post);
        }
        
        boolean isFriend(int userId) {
            return friends.contains(userId);
        }
    }
    
    // Post class to represent a Facebook post
    static class Post {
        int postId;
        int userId;
        String content;
        long timestamp;
        PostType type;
        Set<Integer> likes;
        List<Comment> comments;
        PrivacyLevel privacy;
        
        Post(int postId, int userId, String content, PostType type) {
            this.postId = postId;
            this.userId = userId;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
            this.type = type;
            this.likes = new HashSet<>();
            this.comments = new ArrayList<>();
            this.privacy = PrivacyLevel.PUBLIC;
        }
        
        void addLike(int userId) {
            likes.add(userId);
        }
        
        void removeLike(int userId) {
            likes.remove(userId);
        }
        
        void addComment(Comment comment) {
            comments.add(comment);
        }
    }
    
    // Comment class for post comments
    static class Comment {
        int commentId;
        int userId;
        String content;
        long timestamp;
        
        Comment(int commentId, int userId, String content) {
            this.commentId = commentId;
            this.userId = userId;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
        }
    }
    
    // Privacy settings for users
    static class PrivacySettings {
        boolean profilePublic;
        boolean postsPublic;
        boolean friendsListPublic;
        
        PrivacySettings() {
            this.profilePublic = true;
            this.postsPublic = true;
            this.friendsListPublic = true;
        }
    }
    
    // Post types
    enum PostType {
        TEXT, IMAGE, VIDEO, LINK
    }
    
    // Privacy levels
    enum PrivacyLevel {
        PUBLIC, FRIENDS, FRIENDS_OF_FRIENDS, PRIVATE
    }
    
    private Map<Integer, User> users;
    private Map<Integer, Post> posts;
    private int nextUserId;
    private int nextPostId;
    private int nextCommentId;
    
    public DesignFacebook() {
        users = new HashMap<>();
        posts = new HashMap<>();
        nextUserId = 1;
        nextPostId = 1;
        nextCommentId = 1;
    }
    
    /**
     * Register a new user
     * 
     * @param name User's name
     * @param email User's email
     * @return User ID
     */
    public int registerUser(String name, String email) {
        int userId = nextUserId++;
        User user = new User(userId, name, email);
        users.put(userId, user);
        return userId;
    }
    
    /**
     * Add a friend
     * 
     * @param userId User ID
     * @param friendId Friend's user ID
     * @return True if successful
     */
    public boolean addFriend(int userId, int friendId) {
        if (!users.containsKey(userId) || !users.containsKey(friendId)) {
            return false;
        }
        
        users.get(userId).addFriend(friendId);
        users.get(friendId).addFriend(userId);
        return true;
    }
    
    /**
     * Remove a friend
     * 
     * @param userId User ID
     * @param friendId Friend's user ID
     * @return True if successful
     */
    public boolean removeFriend(int userId, int friendId) {
        if (!users.containsKey(userId) || !users.containsKey(friendId)) {
            return false;
        }
        
        users.get(userId).removeFriend(friendId);
        users.get(friendId).removeFriend(userId);
        return true;
    }
    
    /**
     * Create a new post
     * 
     * @param userId User ID
     * @param content Post content
     * @param type Post type
     * @return Post ID
     */
    public int createPost(int userId, String content, PostType type) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        int postId = nextPostId++;
        Post post = new Post(postId, userId, content, type);
        posts.put(postId, post);
        users.get(userId).addPost(post);
        return postId;
    }
    
    /**
     * Get news feed for a user
     * 
     * @param userId User ID
     * @param limit Number of posts to return
     * @return List of post IDs
     */
    public List<Integer> getNewsFeed(int userId, int limit) {
        List<Integer> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        PriorityQueue<Post> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b.timestamp, a.timestamp));
        
        // Add user's own posts
        for (Post post : user.posts) {
            if (canViewPost(userId, post)) {
                maxHeap.offer(post);
            }
        }
        
        // Add friends' posts
        for (int friendId : user.friends) {
            if (users.containsKey(friendId)) {
                User friend = users.get(friendId);
                for (Post post : friend.posts) {
                    if (canViewPost(userId, post)) {
                        maxHeap.offer(post);
                    }
                }
            }
        }
        
        // Get top posts
        int count = 0;
        while (!maxHeap.isEmpty() && count < limit) {
            result.add(maxHeap.poll().postId);
            count++;
        }
        
        return result;
    }
    
    /**
     * Like a post
     * 
     * @param userId User ID
     * @param postId Post ID
     * @return True if successful
     */
    public boolean likePost(int userId, int postId) {
        if (!users.containsKey(userId) || !posts.containsKey(postId)) {
            return false;
        }
        
        Post post = posts.get(postId);
        if (canViewPost(userId, post)) {
            post.addLike(userId);
            return true;
        }
        
        return false;
    }
    
    /**
     * Unlike a post
     * 
     * @param userId User ID
     * @param postId Post ID
     * @return True if successful
     */
    public boolean unlikePost(int userId, int postId) {
        if (!users.containsKey(userId) || !posts.containsKey(postId)) {
            return false;
        }
        
        Post post = posts.get(postId);
        post.removeLike(userId);
        return true;
    }
    
    /**
     * Add a comment to a post
     * 
     * @param userId User ID
     * @param postId Post ID
     * @param content Comment content
     * @return Comment ID
     */
    public int addComment(int userId, int postId, String content) {
        if (!users.containsKey(userId) || !posts.containsKey(postId)) {
            return -1;
        }
        
        Post post = posts.get(postId);
        if (!canViewPost(userId, post)) {
            return -1;
        }
        
        int commentId = nextCommentId++;
        Comment comment = new Comment(commentId, userId, content);
        post.addComment(comment);
        return commentId;
    }
    
    /**
     * Get user's profile
     * 
     * @param userId User ID
     * @param viewerId ID of user viewing the profile
     * @return User profile information
     */
    public Map<String, Object> getUserProfile(int userId, int viewerId) {
        Map<String, Object> profile = new HashMap<>();
        
        if (!users.containsKey(userId)) {
            return profile;
        }
        
        User user = users.get(userId);
        boolean canViewProfile = canViewProfile(userId, viewerId);
        
        if (canViewProfile) {
            profile.put("userId", user.userId);
            profile.put("name", user.name);
            profile.put("email", user.email);
            profile.put("profile", user.profile);
            profile.put("friendsCount", user.friends.size());
            profile.put("postsCount", user.posts.size());
        } else {
            profile.put("userId", user.userId);
            profile.put("name", user.name);
            profile.put("message", "Profile is private");
        }
        
        return profile;
    }
    
    /**
     * Get user's friends list
     * 
     * @param userId User ID
     * @param viewerId ID of user viewing the friends list
     * @return List of friend IDs
     */
    public List<Integer> getFriendsList(int userId, int viewerId) {
        List<Integer> friends = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return friends;
        }
        
        User user = users.get(userId);
        boolean canViewFriends = canViewFriendsList(userId, viewerId);
        
        if (canViewFriends) {
            friends.addAll(user.friends);
        }
        
        return friends;
    }
    
    /**
     * Search for users
     * 
     * @param query Search query
     * @return List of matching user IDs
     */
    public List<Integer> searchUsers(String query) {
        List<Integer> result = new ArrayList<>();
        
        for (User user : users.values()) {
            if (user.name.toLowerCase().contains(query.toLowerCase()) ||
                user.email.toLowerCase().contains(query.toLowerCase())) {
                result.add(user.userId);
            }
        }
        
        return result;
    }
    
    /**
     * Get post details
     * 
     * @param postId Post ID
     * @param viewerId ID of user viewing the post
     * @return Post details
     */
    public Map<String, Object> getPostDetails(int postId, int viewerId) {
        Map<String, Object> details = new HashMap<>();
        
        if (!posts.containsKey(postId)) {
            return details;
        }
        
        Post post = posts.get(postId);
        if (!canViewPost(viewerId, post)) {
            details.put("message", "Post is not accessible");
            return details;
        }
        
        details.put("postId", post.postId);
        details.put("userId", post.userId);
        details.put("content", post.content);
        details.put("timestamp", post.timestamp);
        details.put("type", post.type);
        details.put("likesCount", post.likes.size());
        details.put("commentsCount", post.comments.size());
        details.put("likes", new ArrayList<>(post.likes));
        details.put("comments", post.comments);
        
        return details;
    }
    
    /**
     * Check if user can view a post
     */
    private boolean canViewPost(int viewerId, Post post) {
        if (post.privacy == PrivacyLevel.PUBLIC) {
            return true;
        }
        
        if (post.userId == viewerId) {
            return true;
        }
        
        if (post.privacy == PrivacyLevel.FRIENDS) {
            return users.get(post.userId).isFriend(viewerId);
        }
        
        if (post.privacy == PrivacyLevel.FRIENDS_OF_FRIENDS) {
            User postOwner = users.get(post.userId);
            User viewer = users.get(viewerId);
            return postOwner.isFriend(viewerId) || 
                   (viewer != null && hasMutualFriend(postOwner, viewer));
        }
        
        return false;
    }
    
    /**
     * Check if user can view a profile
     */
    private boolean canViewProfile(int userId, int viewerId) {
        if (userId == viewerId) {
            return true;
        }
        
        User user = users.get(userId);
        return user.privacy.profilePublic;
    }
    
    /**
     * Check if user can view friends list
     */
    private boolean canViewFriendsList(int userId, int viewerId) {
        if (userId == viewerId) {
            return true;
        }
        
        User user = users.get(userId);
        return user.privacy.friendsListPublic;
    }
    
    /**
     * Check if two users have mutual friends
     */
    private boolean hasMutualFriend(User user1, User user2) {
        for (int friendId : user1.friends) {
            if (user2.friends.contains(friendId)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get user statistics
     * 
     * @param userId User ID
     * @return User statistics
     */
    public Map<String, Integer> getUserStats(int userId) {
        Map<String, Integer> stats = new HashMap<>();
        
        if (!users.containsKey(userId)) {
            return stats;
        }
        
        User user = users.get(userId);
        stats.put("friends", user.friends.size());
        stats.put("posts", user.posts.size());
        
        int totalLikes = 0;
        int totalComments = 0;
        
        for (Post post : user.posts) {
            totalLikes += post.likes.size();
            totalComments += post.comments.size();
        }
        
        stats.put("totalLikes", totalLikes);
        stats.put("totalComments", totalComments);
        
        return stats;
    }
    
    /**
     * Demonstration method showing Facebook functionality
     */
    public static void runExample() {
        System.out.println("=== Design Facebook Example ===");
        
        DesignFacebook facebook = new DesignFacebook();
        
        // Register users
        int alice = facebook.registerUser("Alice", "alice@example.com");
        int bob = facebook.registerUser("Bob", "bob@example.com");
        int charlie = facebook.registerUser("Charlie", "charlie@example.com");
        
        System.out.println("Registered users: Alice(" + alice + "), Bob(" + bob + "), Charlie(" + charlie + ")");
        
        // Add friends
        facebook.addFriend(alice, bob);
        facebook.addFriend(bob, charlie);
        
        System.out.println("Added friends: Alice-Bob, Bob-Charlie");
        
        // Create posts
        int post1 = facebook.createPost(alice, "Hello world!", PostType.TEXT);
        int post2 = facebook.createPost(bob, "Great day today!", PostType.TEXT);
        int post3 = facebook.createPost(charlie, "Check out this photo", PostType.IMAGE);
        
        System.out.println("Created posts: " + post1 + ", " + post2 + ", " + post3);
        
        // Get news feed
        List<Integer> aliceFeed = facebook.getNewsFeed(alice, 10);
        System.out.println("Alice's news feed: " + aliceFeed);
        
        List<Integer> bobFeed = facebook.getNewsFeed(bob, 10);
        System.out.println("Bob's news feed: " + bobFeed);
        
        // Like posts
        facebook.likePost(bob, post1);
        facebook.likePost(charlie, post1);
        facebook.likePost(alice, post2);
        
        System.out.println("Users liked posts");
        
        // Add comments
        facebook.addComment(bob, post1, "Nice post!");
        facebook.addComment(charlie, post1, "I agree!");
        
        System.out.println("Users added comments");
        
        // Get post details
        Map<String, Object> postDetails = facebook.getPostDetails(post1, bob);
        System.out.println("Post " + post1 + " details: " + postDetails);
        
        // Get user profile
        Map<String, Object> aliceProfile = facebook.getUserProfile(alice, bob);
        System.out.println("Alice's profile (viewed by Bob): " + aliceProfile);
        
        // Get friends list
        List<Integer> aliceFriends = facebook.getFriendsList(alice, bob);
        System.out.println("Alice's friends (viewed by Bob): " + aliceFriends);
        
        // Search users
        List<Integer> searchResults = facebook.searchUsers("alice");
        System.out.println("Search results for 'alice': " + searchResults);
        
        // Get user statistics
        Map<String, Integer> aliceStats = facebook.getUserStats(alice);
        System.out.println("Alice's statistics: " + aliceStats);
        
        // Remove friend
        facebook.removeFriend(alice, bob);
        System.out.println("Alice removed Bob as friend");
        
        // Get updated news feed
        List<Integer> updatedFeed = facebook.getNewsFeed(alice, 10);
        System.out.println("Alice's updated news feed: " + updatedFeed);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
