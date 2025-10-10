package masterDSA.topics.system_design.DesignInstagram;
import java.util.*;


/**
 * Design Instagram
 * 
 * Problem Statement:
 * Design a simplified version of Instagram with features like user registration,
 * photo sharing, following/followers, likes, comments, and stories.
 * 
 * LeetCode Link: N/A (System Design Problem)
 * Difficulty: Hard
 * Tags: System Design, Hash Table, Graph, Social Media
 * 
 * Approach Summary:
 * 1. Use HashMap to store users, posts, and relationships
 * 2. Implement feed generation using graph traversal
 * 3. Use PriorityQueue for feed ordering
 * 4. Handle media upload and storage
 * 5. Implement social features (likes, comments, stories)
 * 
 * Pseudocode:
 * 1. Create User, Post, Story, and Comment classes
 * 2. Implement follow/unfollow operations
 * 3. Build feed generation algorithm
 * 4. Handle media upload and processing
 * 5. Implement social interactions
 * 
 * Time Complexity: O(n log n) for feed generation
 * Space Complexity: O(n + m) where n is users, m is posts
 */
public class DesignInstagram {
    
    // User class
    static class User {
        int userId;
        String username;
        String email;
        String fullName;
        String bio;
        String profilePicture;
        boolean isPrivate;
        Set<Integer> following;
        Set<Integer> followers;
        List<Post> posts;
        List<Story> stories;
        
        User(int userId, String username, String email, String fullName) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.fullName = fullName;
            this.bio = "";
            this.profilePicture = "";
            this.isPrivate = false;
            this.following = new HashSet<>();
            this.followers = new HashSet<>();
            this.posts = new ArrayList<>();
            this.stories = new ArrayList<>();
        }
        
        void follow(int userId) {
            following.add(userId);
        }
        
        void unfollow(int userId) {
            following.remove(userId);
        }
        
        void addFollower(int userId) {
            followers.add(userId);
        }
        
        void removeFollower(int userId) {
            followers.remove(userId);
        }
        
        void addPost(Post post) {
            posts.add(post);
        }
        
        void addStory(Story story) {
            stories.add(story);
        }
        
        boolean canViewPosts(int viewerId) {
            return !isPrivate || followers.contains(viewerId) || viewerId == userId;
        }
    }
    
    // Post class
    static class Post {
        int postId;
        int userId;
        String caption;
        List<String> imageUrls;
        List<String> hashtags;
        List<String> mentions;
        long timestamp;
        Set<Integer> likes;
        List<Comment> comments;
        Map<String, Object> metadata;
        
        Post(int postId, int userId, String caption) {
            this.postId = postId;
            this.userId = userId;
            this.caption = caption;
            this.imageUrls = new ArrayList<>();
            this.hashtags = new ArrayList<>();
            this.mentions = new ArrayList<>();
            this.timestamp = System.currentTimeMillis();
            this.likes = new HashSet<>();
            this.comments = new ArrayList<>();
            this.metadata = new HashMap<>();
        }
        
        void addImage(String imageUrl) {
            imageUrls.add(imageUrl);
        }
        
        void addHashtag(String hashtag) {
            hashtags.add(hashtag);
        }
        
        void addMention(String mention) {
            mentions.add(mention);
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
    
    // Story class
    static class Story {
        int storyId;
        int userId;
        String imageUrl;
        String text;
        long timestamp;
        long expiresAt;
        Set<Integer> viewers;
        Map<String, Object> metadata;
        
        Story(int storyId, int userId, String imageUrl) {
            this.storyId = storyId;
            this.userId = userId;
            this.imageUrl = imageUrl;
            this.text = "";
            this.timestamp = System.currentTimeMillis();
            this.expiresAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000); // 24 hours
            this.viewers = new HashSet<>();
            this.metadata = new HashMap<>();
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
        
        void addViewer(int userId) {
            viewers.add(userId);
        }
    }
    
    // Comment class
    static class Comment {
        int commentId;
        int postId;
        int userId;
        String content;
        long timestamp;
        List<Comment> replies;
        
        Comment(int commentId, int postId, int userId, String content) {
            this.commentId = commentId;
            this.postId = postId;
            this.userId = userId;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
            this.replies = new ArrayList<>();
        }
        
        void addReply(Comment reply) {
            replies.add(reply);
        }
    }
    
    // Hashtag class
    static class Hashtag {
        String tag;
        List<Integer> posts;
        int followers;
        
        Hashtag(String tag) {
            this.tag = tag;
            this.posts = new ArrayList<>();
            this.followers = 0;
        }
        
        void addPost(int postId) {
            posts.add(postId);
        }
        
        void removePost(int postId) {
            posts.remove(Integer.valueOf(postId));
        }
        
        void addFollower() {
            followers++;
        }
        
        void removeFollower() {
            followers--;
        }
    }
    
    private Map<Integer, User> users;
    private Map<Integer, Post> posts;
    private Map<Integer, Story> stories;
    private Map<Integer, Comment> comments;
    private Map<String, Hashtag> hashtags;
    private Map<String, Integer> usernameToUserId;
    private int nextUserId;
    private int nextPostId;
    private int nextStoryId;
    private int nextCommentId;
    
    public DesignInstagram() {
        users = new HashMap<>();
        posts = new HashMap<>();
        stories = new HashMap<>();
        comments = new HashMap<>();
        hashtags = new HashMap<>();
        usernameToUserId = new HashMap<>();
        nextUserId = 1;
        nextPostId = 1;
        nextStoryId = 1;
        nextCommentId = 1;
    }
    
    /**
     * Register a new user
     * 
     * @param username Username
     * @param email Email
     * @param fullName Full name
     * @return User ID
     */
    public int registerUser(String username, String email, String fullName) {
        if (usernameToUserId.containsKey(username)) {
            return -1; // Username already exists
        }
        
        int userId = nextUserId++;
        User user = new User(userId, username, email, fullName);
        users.put(userId, user);
        usernameToUserId.put(username, userId);
        return userId;
    }
    
    /**
     * Follow a user
     * 
     * @param followerId Follower user ID
     * @param followeeId User being followed
     * @return True if successful
     */
    public boolean followUser(int followerId, int followeeId) {
        if (!users.containsKey(followerId) || !users.containsKey(followeeId)) {
            return false;
        }
        
        if (followerId == followeeId) {
            return false; // Cannot follow self
        }
        
        users.get(followerId).follow(followeeId);
        users.get(followeeId).addFollower(followerId);
        return true;
    }
    
    /**
     * Unfollow a user
     * 
     * @param followerId Follower user ID
     * @param followeeId User being unfollowed
     * @return True if successful
     */
    public boolean unfollowUser(int followerId, int followeeId) {
        if (!users.containsKey(followerId) || !users.containsKey(followeeId)) {
            return false;
        }
        
        users.get(followerId).unfollow(followeeId);
        users.get(followeeId).removeFollower(followerId);
        return true;
    }
    
    /**
     * Create a new post
     * 
     * @param userId User ID
     * @param caption Post caption
     * @param imageUrls List of image URLs
     * @return Post ID
     */
    public int createPost(int userId, String caption, List<String> imageUrls) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        int postId = nextPostId++;
        Post post = new Post(postId, userId, caption);
        
        for (String imageUrl : imageUrls) {
            post.addImage(imageUrl);
        }
        
        // Extract hashtags and mentions
        extractHashtagsAndMentions(post, caption);
        
        posts.put(postId, post);
        users.get(userId).addPost(post);
        
        // Update hashtag posts
        for (String hashtag : post.hashtags) {
            if (!hashtags.containsKey(hashtag)) {
                hashtags.put(hashtag, new Hashtag(hashtag));
            }
            hashtags.get(hashtag).addPost(postId);
        }
        
        return postId;
    }
    
    /**
     * Like a post
     * 
     * @param postId Post ID
     * @param userId User ID
     * @return True if successful
     */
    public boolean likePost(int postId, int userId) {
        if (!posts.containsKey(postId) || !users.containsKey(userId)) {
            return false;
        }
        
        Post post = posts.get(postId);
        User user = users.get(userId);
        
        if (!user.canViewPosts(userId)) {
            return false;
        }
        
        post.addLike(userId);
        return true;
    }
    
    /**
     * Unlike a post
     * 
     * @param postId Post ID
     * @param userId User ID
     * @return True if successful
     */
    public boolean unlikePost(int postId, int userId) {
        if (!posts.containsKey(postId) || !users.containsKey(userId)) {
            return false;
        }
        
        posts.get(postId).removeLike(userId);
        return true;
    }
    
    /**
     * Add a comment to a post
     * 
     * @param postId Post ID
     * @param userId User ID
     * @param content Comment content
     * @return Comment ID
     */
    public int addComment(int postId, int userId, String content) {
        if (!posts.containsKey(postId) || !users.containsKey(userId)) {
            return -1;
        }
        
        Post post = posts.get(postId);
        User user = users.get(userId);
        
        if (!user.canViewPosts(userId)) {
            return -1;
        }
        
        int commentId = nextCommentId++;
        Comment comment = new Comment(commentId, postId, userId, content);
        comments.put(commentId, comment);
        post.addComment(comment);
        
        return commentId;
    }
    
    /**
     * Reply to a comment
     * 
     * @param commentId Parent comment ID
     * @param userId User ID
     * @param content Reply content
     * @return Reply comment ID
     */
    public int replyToComment(int commentId, int userId, String content) {
        if (!comments.containsKey(commentId) || !users.containsKey(userId)) {
            return -1;
        }
        
        Comment parentComment = comments.get(commentId);
        int replyId = nextCommentId++;
        Comment reply = new Comment(replyId, parentComment.postId, userId, content);
        comments.put(replyId, reply);
        parentComment.addReply(reply);
        
        return replyId;
    }
    
    /**
     * Create a story
     * 
     * @param userId User ID
     * @param imageUrl Story image URL
     * @param text Optional text overlay
     * @return Story ID
     */
    public int createStory(int userId, String imageUrl, String text) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        int storyId = nextStoryId++;
        Story story = new Story(storyId, userId, imageUrl);
        story.text = text;
        
        stories.put(storyId, story);
        users.get(userId).addStory(story);
        
        return storyId;
    }
    
    /**
     * View a story
     * 
     * @param storyId Story ID
     * @param viewerId Viewer user ID
     * @return True if successful
     */
    public boolean viewStory(int storyId, int viewerId) {
        if (!stories.containsKey(storyId) || !users.containsKey(viewerId)) {
            return false;
        }
        
        Story story = stories.get(storyId);
        if (story.isExpired()) {
            return false;
        }
        
        User storyOwner = users.get(story.userId);
        User viewer = users.get(viewerId);
        
        if (!storyOwner.canViewPosts(viewerId)) {
            return false;
        }
        
        story.addViewer(viewerId);
        return true;
    }
    
    /**
     * Get user's feed
     * 
     * @param userId User ID
     * @param limit Number of posts to return
     * @return List of post IDs
     */
    public List<Integer> getFeed(int userId, int limit) {
        List<Integer> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        PriorityQueue<Post> feedQueue = new PriorityQueue<>((a, b) -> Long.compare(b.timestamp, a.timestamp));
        
        // Add user's own posts
        for (Post post : user.posts) {
            feedQueue.offer(post);
        }
        
        // Add posts from followed users
        for (int followeeId : user.following) {
            if (users.containsKey(followeeId)) {
                User followee = users.get(followeeId);
                for (Post post : followee.posts) {
                    if (followee.canViewPosts(userId)) {
                        feedQueue.offer(post);
                    }
                }
            }
        }
        
        // Get top posts
        int count = 0;
        while (!feedQueue.isEmpty() && count < limit) {
            result.add(feedQueue.poll().postId);
            count++;
        }
        
        return result;
    }
    
    /**
     * Get user's stories
     * 
     * @param userId User ID
     * @return List of story IDs
     */
    public List<Integer> getUserStories(int userId) {
        List<Integer> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        for (Story story : user.stories) {
            if (!story.isExpired()) {
                result.add(story.storyId);
            }
        }
        
        return result;
    }
    
    /**
     * Get stories from followed users
     * 
     * @param userId User ID
     * @return Map of user ID to list of story IDs
     */
    public Map<Integer, List<Integer>> getFollowedStories(int userId) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        for (int followeeId : user.following) {
            if (users.containsKey(followeeId)) {
                User followee = users.get(followeeId);
                List<Integer> stories = new ArrayList<>();
                for (Story story : followee.stories) {
                    if (!story.isExpired() && followee.canViewPosts(userId)) {
                        stories.add(story.storyId);
                    }
                }
                if (!stories.isEmpty()) {
                    result.put(followeeId, stories);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Search users by username
     * 
     * @param query Search query
     * @return List of user IDs
     */
    public List<Integer> searchUsers(String query) {
        List<Integer> result = new ArrayList<>();
        
        if (query == null || query.trim().isEmpty()) {
            return result;
        }
        
        String lowerQuery = query.toLowerCase();
        for (User user : users.values()) {
            if (user.username.toLowerCase().contains(lowerQuery) ||
                user.fullName.toLowerCase().contains(lowerQuery)) {
                result.add(user.userId);
            }
        }
        
        return result;
    }
    
    /**
     * Search posts by hashtag
     * 
     * @param hashtag Hashtag to search
     * @return List of post IDs
     */
    public List<Integer> searchPostsByHashtag(String hashtag) {
        List<Integer> result = new ArrayList<>();
        
        if (hashtags.containsKey(hashtag)) {
            result.addAll(hashtags.get(hashtag).posts);
        }
        
        return result;
    }
    
    /**
     * Get trending hashtags
     * 
     * @param limit Number of hashtags to return
     * @return List of trending hashtags
     */
    public List<String> getTrendingHashtags(int limit) {
        List<String> result = new ArrayList<>();
        
        List<Hashtag> sortedHashtags = new ArrayList<>(hashtags.values());
        sortedHashtags.sort((a, b) -> Integer.compare(b.posts.size(), a.posts.size()));
        
        for (int i = 0; i < Math.min(limit, sortedHashtags.size()); i++) {
            result.add(sortedHashtags.get(i).tag);
        }
        
        return result;
    }
    
    /**
     * Get user profile
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
        boolean canView = user.canViewPosts(viewerId);
        
        profile.put("userId", user.userId);
        profile.put("username", user.username);
        profile.put("fullName", user.fullName);
        profile.put("bio", user.bio);
        profile.put("profilePicture", user.profilePicture);
        profile.put("isPrivate", user.isPrivate);
        profile.put("followersCount", user.followers.size());
        profile.put("followingCount", user.following.size());
        profile.put("postsCount", user.posts.size());
        
        if (canView) {
            profile.put("posts", user.posts.stream().map(p -> p.postId).toArray());
        } else {
            profile.put("message", "This account is private");
        }
        
        return profile;
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
        User postOwner = users.get(post.userId);
        
        if (!postOwner.canViewPosts(viewerId)) {
            details.put("message", "This post is not accessible");
            return details;
        }
        
        details.put("postId", post.postId);
        details.put("userId", post.userId);
        details.put("caption", post.caption);
        details.put("imageUrls", post.imageUrls);
        details.put("hashtags", post.hashtags);
        details.put("mentions", post.mentions);
        details.put("timestamp", post.timestamp);
        details.put("likesCount", post.likes.size());
        details.put("commentsCount", post.comments.size());
        details.put("likes", new ArrayList<>(post.likes));
        details.put("comments", post.comments.stream().map(c -> c.commentId).toArray());
        
        return details;
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
        stats.put("username", user.username);
        stats.put("followers", user.followers.size());
        stats.put("following", user.following.size());
        stats.put("posts", user.posts.size());
        stats.put("stories", user.stories.size());
        
        // Calculate total likes received
        int totalLikes = 0;
        for (Post post : user.posts) {
            totalLikes += post.likes.size();
        }
        stats.put("totalLikes", totalLikes);
        
        // Calculate total comments received
        int totalComments = 0;
        for (Post post : user.posts) {
            totalComments += post.comments.size();
        }
        stats.put("totalComments", totalComments);
        
        return stats;
    }
    
    /**
     * Extract hashtags and mentions from text
     */
    private void extractHashtagsAndMentions(Post post, String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.startsWith("#")) {
                post.addHashtag(word.substring(1));
            } else if (word.startsWith("@")) {
                post.addMention(word.substring(1));
            }
        }
    }
    
    /**
     * Demonstration method showing Instagram functionality
     */
    public static void runExample() {
        System.out.println("=== Design Instagram Example ===");
        
        DesignInstagram instagram = new DesignInstagram();
        
        // Register users
        int alice = instagram.registerUser("alice", "alice@example.com", "Alice Smith");
        int bob = instagram.registerUser("bob", "bob@example.com", "Bob Johnson");
        int charlie = instagram.registerUser("charlie", "charlie@example.com", "Charlie Brown");
        
        System.out.println("Registered users: Alice(" + alice + "), Bob(" + bob + "), Charlie(" + charlie + ")");
        
        // Follow users
        instagram.followUser(alice, bob);
        instagram.followUser(alice, charlie);
        instagram.followUser(bob, alice);
        instagram.followUser(charlie, alice);
        
        System.out.println("Users followed each other");
        
        // Create posts
        int post1 = instagram.createPost(alice, "Beautiful sunset! #sunset #nature", 
                                        Arrays.asList("sunset1.jpg", "sunset2.jpg"));
        int post2 = instagram.createPost(bob, "Coffee time ☕ #coffee #morning", 
                                        Arrays.asList("coffee.jpg"));
        int post3 = instagram.createPost(charlie, "Working hard 💪 #work #motivation", 
                                        Arrays.asList("work.jpg"));
        
        System.out.println("Created posts: " + post1 + ", " + post2 + ", " + post3);
        
        // Like posts
        instagram.likePost(post1, bob);
        instagram.likePost(post1, charlie);
        instagram.likePost(post2, alice);
        instagram.likePost(post3, alice);
        
        System.out.println("Users liked posts");
        
        // Add comments
        int comment1 = instagram.addComment(post1, bob, "Amazing shot! 📸");
        int comment2 = instagram.addComment(post1, charlie, "Love this!");
        int reply1 = instagram.replyToComment(comment1, alice, "Thank you! 😊");
        
        System.out.println("Users added comments and replies");
        
        // Create stories
        int story1 = instagram.createStory(alice, "story1.jpg", "Good morning!");
        int story2 = instagram.createStory(bob, "story2.jpg", "Coffee break");
        
        System.out.println("Created stories: " + story1 + ", " + story2);
        
        // View stories
        instagram.viewStory(story1, bob);
        instagram.viewStory(story1, charlie);
        instagram.viewStory(story2, alice);
        
        System.out.println("Users viewed stories");
        
        // Get feed
        List<Integer> aliceFeed = instagram.getFeed(alice, 10);
        System.out.println("Alice's feed: " + aliceFeed);
        
        // Get user stories
        List<Integer> aliceStories = instagram.getUserStories(alice);
        System.out.println("Alice's stories: " + aliceStories);
        
        // Get followed stories
        Map<Integer, List<Integer>> followedStories = instagram.getFollowedStories(alice);
        System.out.println("Stories from followed users: " + followedStories);
        
        // Search users
        List<Integer> searchResults = instagram.searchUsers("alice");
        System.out.println("Search results for 'alice': " + searchResults);
        
        // Search posts by hashtag
        List<Integer> sunsetPosts = instagram.searchPostsByHashtag("sunset");
        System.out.println("Posts with #sunset: " + sunsetPosts);
        
        // Get trending hashtags
        List<String> trending = instagram.getTrendingHashtags(5);
        System.out.println("Trending hashtags: " + trending);
        
        // Get user profile
        Map<String, Object> aliceProfile = instagram.getUserProfile(alice, bob);
        System.out.println("Alice's profile (viewed by Bob): " + aliceProfile);
        
        // Get post details
        Map<String, Object> postDetails = instagram.getPostDetails(post1, bob);
        System.out.println("Post " + post1 + " details: " + postDetails);
        
        // Get user statistics
        Map<String, Object> aliceStats = instagram.getUserStats(alice);
        System.out.println("Alice's statistics: " + aliceStats);
        
        // Unfollow user
        instagram.unfollowUser(alice, charlie);
        System.out.println("Alice unfollowed Charlie");
        
        // Get updated feed
        List<Integer> updatedFeed = instagram.getFeed(alice, 10);
        System.out.println("Alice's updated feed: " + updatedFeed);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
