package masterDSA.topics.system_design.DesignTwitter;
import java.util.*;


/**
 * Design Twitter
 * 
 * Problem Statement:
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow other users,
 * and see the 10 most recent tweets in their news feed.
 * 
 * LeetCode Link: https://leetcode.com/problems/design-twitter/
 * Difficulty: Medium
 * Tags: Hash Table, Heap, Design
 * 
 * Approach Summary:
 * 1. Use HashMap to store user relationships and tweets
 * 2. Use PriorityQueue (Max Heap) to get most recent tweets
 * 3. Implement follow/unfollow operations
 * 4. Implement postTweet and getNewsFeed operations
 * 5. Handle edge cases and optimize for performance
 * 
 * Pseudocode:
 * 1. Create User class with userId and tweets list
 * 2. Create Tweet class with tweetId and timestamp
 * 3. Maintain follow relationships in HashMap
 * 4. For news feed, collect tweets from user and followers
 * 5. Use heap to get top 10 most recent tweets
 * 
 * Time Complexity: O(k log n) where k is number of tweets, n is number of users
 * Space Complexity: O(n + m) where n is users, m is tweets
 */
public class DesignTwitter {
    
    // Tweet class to represent a tweet
    static class Tweet {
        int tweetId;
        int timestamp;
        
        Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
    
    // User class to represent a user
    static class User {
        int userId;
        Set<Integer> following;
        List<Tweet> tweets;
        
        User(int userId) {
            this.userId = userId;
            this.following = new HashSet<>();
            this.tweets = new ArrayList<>();
            follow(userId); // User follows themselves
        }
        
        void follow(int followeeId) {
            following.add(followeeId);
        }
        
        void unfollow(int followeeId) {
            if (followeeId != userId) { // Cannot unfollow self
                following.remove(followeeId);
            }
        }
        
        void postTweet(int tweetId, int timestamp) {
            tweets.add(new Tweet(tweetId, timestamp));
        }
    }
    
    private Map<Integer, User> users;
    private int timestamp;
    
    public DesignTwitter() {
        users = new HashMap<>();
        timestamp = 0;
    }
    
    /**
     * Post a new tweet
     * 
     * @param userId User posting the tweet
     * @param tweetId ID of the tweet
     */
    public void postTweet(int userId, int tweetId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }
        
        users.get(userId).postTweet(tweetId, timestamp++);
    }
    
    /**
     * Get the 10 most recent tweets in user's news feed
     * 
     * @param userId User requesting news feed
     * @return List of tweet IDs
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
        
        // Add tweets from user and their followers
        for (int followeeId : user.following) {
            if (users.containsKey(followeeId)) {
                for (Tweet tweet : users.get(followeeId).tweets) {
                    maxHeap.offer(tweet);
                }
            }
        }
        
        // Get top 10 tweets
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            result.add(maxHeap.poll().tweetId);
            count++;
        }
        
        return result;
    }
    
    /**
     * Follow a user
     * 
     * @param followerId User doing the following
     * @param followeeId User being followed
     */
    public void follow(int followerId, int followeeId) {
        if (!users.containsKey(followerId)) {
            users.put(followerId, new User(followerId));
        }
        
        if (!users.containsKey(followeeId)) {
            users.put(followeeId, new User(followeeId));
        }
        
        users.get(followerId).follow(followeeId);
    }
    
    /**
     * Unfollow a user
     * 
     * @param followerId User doing the unfollowing
     * @param followeeId User being unfollowed
     */
    public void unfollow(int followerId, int followeeId) {
        if (users.containsKey(followerId)) {
            users.get(followerId).unfollow(followeeId);
        }
    }
    
    /**
     * Get user's own tweets
     * 
     * @param userId User ID
     * @return List of user's tweets
     */
    public List<Integer> getUserTweets(int userId) {
        List<Integer> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        List<Tweet> tweets = new ArrayList<>(user.tweets);
        
        // Sort by timestamp (most recent first)
        tweets.sort((a, b) -> b.timestamp - a.timestamp);
        
        for (Tweet tweet : tweets) {
            result.add(tweet.tweetId);
        }
        
        return result;
    }
    
    /**
     * Get user's followers
     * 
     * @param userId User ID
     * @return Set of follower IDs
     */
    public Set<Integer> getFollowers(int userId) {
        Set<Integer> followers = new HashSet<>();
        
        for (User user : users.values()) {
            if (user.following.contains(userId)) {
                followers.add(user.userId);
            }
        }
        
        return followers;
    }
    
    /**
     * Get user's following list
     * 
     * @param userId User ID
     * @return Set of following IDs
     */
    public Set<Integer> getFollowing(int userId) {
        if (!users.containsKey(userId)) {
            return new HashSet<>();
        }
        
        Set<Integer> following = new HashSet<>(users.get(userId).following);
        following.remove(userId); // Remove self from following list
        return following;
    }
    
    /**
     * Delete a tweet
     * 
     * @param userId User ID
     * @param tweetId Tweet ID
     * @return True if tweet was deleted, false otherwise
     */
    public boolean deleteTweet(int userId, int tweetId) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        User user = users.get(userId);
        Iterator<Tweet> iterator = user.tweets.iterator();
        
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            if (tweet.tweetId == tweetId) {
                iterator.remove();
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Get trending hashtags (simplified implementation)
     * 
     * @return List of trending hashtags
     */
    public List<String> getTrendingHashtags() {
        Map<String, Integer> hashtagCount = new HashMap<>();
        
        // Count hashtags from all tweets
        for (User user : users.values()) {
            for (Tweet tweet : user.tweets) {
                // Simple hashtag extraction (in real implementation, parse tweet content)
                String hashtag = "#trending" + (tweet.tweetId % 5); // Mock hashtag
                hashtagCount.put(hashtag, hashtagCount.getOrDefault(hashtag, 0) + 1);
            }
        }
        
        // Sort by count and return top hashtags
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(hashtagCount.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());
        
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(5, sorted.size()); i++) {
            result.add(sorted.get(i).getKey());
        }
        
        return result;
    }
    
    /**
     * Get user statistics
     * 
     * @param userId User ID
     * @return Map containing user statistics
     */
    public Map<String, Integer> getUserStats(int userId) {
        Map<String, Integer> stats = new HashMap<>();
        
        if (!users.containsKey(userId)) {
            return stats;
        }
        
        User user = users.get(userId);
        stats.put("tweets", user.tweets.size());
        stats.put("following", user.following.size() - 1); // Exclude self
        stats.put("followers", getFollowers(userId).size());
        
        return stats;
    }
    
    /**
     * Search tweets by keyword (simplified implementation)
     * 
     * @param keyword Search keyword
     * @return List of matching tweet IDs
     */
    public List<Integer> searchTweets(String keyword) {
        List<Integer> result = new ArrayList<>();
        
        // Simple search implementation
        for (User user : users.values()) {
            for (Tweet tweet : user.tweets) {
                // Mock search logic
                if (String.valueOf(tweet.tweetId).contains(keyword)) {
                    result.add(tweet.tweetId);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Demonstration method showing Twitter functionality
     */
    public static void runExample() {
        System.out.println("=== Design Twitter Example ===");
        
        DesignTwitter twitter = new DesignTwitter();
        
        // Post tweets
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(1, 13);
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 94);
        twitter.postTweet(1, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(1, 22);
        twitter.postTweet(1, 11);
        
        // Follow users
        twitter.follow(1, 2);
        twitter.follow(1, 3);
        
        // Post tweets from other users
        twitter.postTweet(2, 6);
        twitter.postTweet(3, 7);
        twitter.postTweet(2, 8);
        
        // Get news feed
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        System.out.println("News feed for user 1: " + newsFeed);
        
        // Get user tweets
        List<Integer> userTweets = twitter.getUserTweets(1);
        System.out.println("User 1's tweets: " + userTweets);
        
        // Get following list
        Set<Integer> following = twitter.getFollowing(1);
        System.out.println("User 1 following: " + following);
        
        // Get followers
        Set<Integer> followers = twitter.getFollowers(1);
        System.out.println("User 1 followers: " + followers);
        
        // Get user statistics
        Map<String, Integer> stats = twitter.getUserStats(1);
        System.out.println("User 1 statistics: " + stats);
        
        // Search tweets
        List<Integer> searchResults = twitter.searchTweets("1");
        System.out.println("Search results for '1': " + searchResults);
        
        // Get trending hashtags
        List<String> trending = twitter.getTrendingHashtags();
        System.out.println("Trending hashtags: " + trending);
        
        // Unfollow and get updated news feed
        twitter.unfollow(1, 2);
        List<Integer> updatedNewsFeed = twitter.getNewsFeed(1);
        System.out.println("Updated news feed after unfollow: " + updatedNewsFeed);
        
        // Delete a tweet
        boolean deleted = twitter.deleteTweet(1, 5);
        System.out.println("Tweet 5 deleted: " + deleted);
        
        // Get updated user tweets
        List<Integer> updatedTweets = twitter.getUserTweets(1);
        System.out.println("Updated user tweets: " + updatedTweets);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
