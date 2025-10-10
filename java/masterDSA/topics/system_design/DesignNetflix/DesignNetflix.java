package masterDSA.topics.system_design.DesignNetflix;
import java.util.*;


/**
 * Design Netflix
 * 
 * Problem Statement:
 * Design a simplified version of Netflix with features like user registration,
 * content catalog, recommendations, streaming, and user profiles.
 * 
 * LeetCode Link: N/A (System Design Problem)
 * Difficulty: Hard
 * Tags: System Design, Hash Table, Graph, Recommendation System
 * 
 * Approach Summary:
 * 1. Use HashMap to store users, content, and viewing history
 * 2. Implement recommendation system using collaborative filtering
 * 3. Use Graph structure for content relationships
 * 4. Implement content streaming simulation
 * 5. Handle user profiles and preferences
 * 
 * Pseudocode:
 * 1. Create User, Content, and Profile classes
 * 2. Implement content catalog management
 * 3. Build recommendation engine
 * 4. Handle streaming and viewing history
 * 5. Implement user preferences and ratings
 * 
 * Time Complexity: O(n log n) for recommendations
 * Space Complexity: O(n + m) where n is users, m is content
 */
public class DesignNetflix {
    
    // User class
    static class User {
        int userId;
        String name;
        String email;
        UserProfile profile;
        List<ViewingHistory> viewingHistory;
        Map<Integer, Double> ratings;
        Set<Integer> watchlist;
        
        User(int userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.profile = new UserProfile();
            this.viewingHistory = new ArrayList<>();
            this.ratings = new HashMap<>();
            this.watchlist = new HashSet<>();
        }
        
        void addToWatchlist(int contentId) {
            watchlist.add(contentId);
        }
        
        void removeFromWatchlist(int contentId) {
            watchlist.remove(contentId);
        }
        
        void rateContent(int contentId, double rating) {
            if (rating >= 1.0 && rating <= 5.0) {
                ratings.put(contentId, rating);
            }
        }
        
        void addViewingHistory(ViewingHistory history) {
            viewingHistory.add(history);
        }
    }
    
    // User profile class
    static class UserProfile {
        String avatar;
        List<String> favoriteGenres;
        List<String> languages;
        String maturityRating;
        boolean autoPlay;
        boolean subtitles;
        
        UserProfile() {
            this.avatar = "default";
            this.favoriteGenres = new ArrayList<>();
            this.languages = new ArrayList<>();
            this.maturityRating = "PG-13";
            this.autoPlay = true;
            this.subtitles = false;
        }
    }
    
    // Content class
    static class Content {
        int contentId;
        String title;
        String description;
        ContentType type;
        List<String> genres;
        List<String> cast;
        String director;
        int releaseYear;
        int duration; // in minutes
        String maturityRating;
        double averageRating;
        int totalRatings;
        Map<String, String> metadata;
        
        Content(int contentId, String title, String description, ContentType type) {
            this.contentId = contentId;
            this.title = title;
            this.description = description;
            this.type = type;
            this.genres = new ArrayList<>();
            this.cast = new ArrayList<>();
            this.director = "";
            this.releaseYear = 0;
            this.duration = 0;
            this.maturityRating = "PG-13";
            this.averageRating = 0.0;
            this.totalRatings = 0;
            this.metadata = new HashMap<>();
        }
        
        void updateRating(double newRating) {
            double totalRating = averageRating * totalRatings + newRating;
            totalRatings++;
            averageRating = totalRating / totalRatings;
        }
    }
    
    // Viewing history class
    static class ViewingHistory {
        int userId;
        int contentId;
        long startTime;
        long endTime;
        int watchDuration; // in minutes
        boolean completed;
        
        ViewingHistory(int userId, int contentId, long startTime) {
            this.userId = userId;
            this.contentId = contentId;
            this.startTime = startTime;
            this.endTime = 0;
            this.watchDuration = 0;
            this.completed = false;
        }
        
        void endViewing(long endTime) {
            this.endTime = endTime;
            this.watchDuration = (int) ((endTime - startTime) / (1000 * 60)); // Convert to minutes
        }
        
        void markCompleted() {
            this.completed = true;
        }
    }
    
    // Recommendation class
    static class Recommendation {
        int contentId;
        double score;
        String reason;
        
        Recommendation(int contentId, double score, String reason) {
            this.contentId = contentId;
            this.score = score;
            this.reason = reason;
        }
    }
    
    // Enums
    enum ContentType {
        MOVIE, TV_SHOW, DOCUMENTARY, STANDUP, ANIME
    }
    
    enum StreamingQuality {
        SD, HD, UHD
    }
    
    private Map<Integer, User> users;
    private Map<Integer, Content> content;
    private Map<Integer, List<Recommendation>> recommendations;
    private int nextUserId;
    private int nextContentId;
    
    public DesignNetflix() {
        users = new HashMap<>();
        content = new HashMap<>();
        recommendations = new HashMap<>();
        nextUserId = 1;
        nextContentId = 1;
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
     * Add content to catalog
     * 
     * @param title Content title
     * @param description Content description
     * @param type Content type
     * @return Content ID
     */
    public int addContent(String title, String description, ContentType type) {
        int contentId = nextContentId++;
        Content newContent = new Content(contentId, title, description, type);
        content.put(contentId, newContent);
        return contentId;
    }
    
    /**
     * Update content details
     * 
     * @param contentId Content ID
     * @param genres List of genres
     * @param cast List of cast members
     * @param director Director name
     * @param releaseYear Release year
     * @param duration Duration in minutes
     * @param maturityRating Maturity rating
     * @return True if successful
     */
    public boolean updateContentDetails(int contentId, List<String> genres, List<String> cast,
                                      String director, int releaseYear, int duration, String maturityRating) {
        if (!content.containsKey(contentId)) {
            return false;
        }
        
        Content contentItem = content.get(contentId);
        contentItem.genres = genres;
        contentItem.cast = cast;
        contentItem.director = director;
        contentItem.releaseYear = releaseYear;
        contentItem.duration = duration;
        contentItem.maturityRating = maturityRating;
        
        return true;
    }
    
    /**
     * Start streaming content
     * 
     * @param userId User ID
     * @param contentId Content ID
     * @return Viewing history ID
     */
    public int startStreaming(int userId, int contentId) {
        if (!users.containsKey(userId) || !content.containsKey(contentId)) {
            return -1;
        }
        
        long startTime = System.currentTimeMillis();
        ViewingHistory history = new ViewingHistory(userId, contentId, startTime);
        users.get(userId).addViewingHistory(history);
        
        return history.hashCode(); // Simple ID generation
    }
    
    /**
     * End streaming session
     * 
     * @param userId User ID
     * @param contentId Content ID
     * @param completed Whether content was completed
     * @return True if successful
     */
    public boolean endStreaming(int userId, int contentId, boolean completed) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        User user = users.get(userId);
        for (ViewingHistory history : user.viewingHistory) {
            if (history.contentId == contentId && history.endTime == 0) {
                history.endViewing(System.currentTimeMillis());
                if (completed) {
                    history.markCompleted();
                }
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Rate content
     * 
     * @param userId User ID
     * @param contentId Content ID
     * @param rating Rating (1-5)
     * @return True if successful
     */
    public boolean rateContent(int userId, int contentId, double rating) {
        if (!users.containsKey(userId) || !content.containsKey(contentId)) {
            return false;
        }
        
        if (rating < 1.0 || rating > 5.0) {
            return false;
        }
        
        users.get(userId).rateContent(contentId, rating);
        content.get(contentId).updateRating(rating);
        
        return true;
    }
    
    /**
     * Add content to watchlist
     * 
     * @param userId User ID
     * @param contentId Content ID
     * @return True if successful
     */
    public boolean addToWatchlist(int userId, int contentId) {
        if (!users.containsKey(userId) || !content.containsKey(contentId)) {
            return false;
        }
        
        users.get(userId).addToWatchlist(contentId);
        return true;
    }
    
    /**
     * Remove content from watchlist
     * 
     * @param userId User ID
     * @param contentId Content ID
     * @return True if successful
     */
    public boolean removeFromWatchlist(int userId, int contentId) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        users.get(userId).removeFromWatchlist(contentId);
        return true;
    }
    
    /**
     * Get user's watchlist
     * 
     * @param userId User ID
     * @return List of content IDs
     */
    public List<Integer> getWatchlist(int userId) {
        if (!users.containsKey(userId)) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(users.get(userId).watchlist);
    }
    
    /**
     * Get content recommendations for user
     * 
     * @param userId User ID
     * @param limit Number of recommendations
     * @return List of recommendations
     */
    public List<Recommendation> getRecommendations(int userId, int limit) {
        List<Recommendation> result = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return result;
        }
        
        User user = users.get(userId);
        
        // Simple recommendation algorithm based on genres and ratings
        Map<String, Integer> genreCount = new HashMap<>();
        Map<String, Double> genreRatings = new HashMap<>();
        
        // Analyze user's viewing history
        for (ViewingHistory history : user.viewingHistory) {
            if (content.containsKey(history.contentId)) {
                Content contentItem = content.get(history.contentId);
                for (String genre : contentItem.genres) {
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                    double currentRating = user.ratings.getOrDefault(history.contentId, 3.0);
                    genreRatings.put(genre, genreRatings.getOrDefault(genre, 0.0) + currentRating);
                }
            }
        }
        
        // Calculate average ratings per genre
        for (String genre : genreRatings.keySet()) {
            genreRatings.put(genre, genreRatings.get(genre) / genreCount.get(genre));
        }
        
        // Find content with high ratings in preferred genres
        for (Content contentItem : content.values()) {
            if (user.watchlist.contains(contentItem.contentId)) {
                continue; // Skip already in watchlist
            }
            
            double score = 0.0;
            String reason = "";
            
            // Score based on genre preferences
            for (String genre : contentItem.genres) {
                if (genreRatings.containsKey(genre)) {
                    score += genreRatings.get(genre);
                    reason += genre + " ";
                }
            }
            
            // Boost score for high-rated content
            if (contentItem.averageRating > 4.0) {
                score += 1.0;
                reason += "highly-rated ";
            }
            
            // Boost score for recent content
            if (contentItem.releaseYear >= 2020) {
                score += 0.5;
                reason += "recent ";
            }
            
            if (score > 0) {
                result.add(new Recommendation(contentItem.contentId, score, reason.trim()));
            }
        }
        
        // Sort by score and return top recommendations
        result.sort((a, b) -> Double.compare(b.score, a.score));
        
        return result.subList(0, Math.min(limit, result.size()));
    }
    
    /**
     * Search content
     * 
     * @param query Search query
     * @return List of matching content IDs
     */
    public List<Integer> searchContent(String query) {
        List<Integer> result = new ArrayList<>();
        
        if (query == null || query.trim().isEmpty()) {
            return result;
        }
        
        String lowerQuery = query.toLowerCase();
        
        for (Content contentItem : content.values()) {
            if (contentItem.title.toLowerCase().contains(lowerQuery) ||
                contentItem.description.toLowerCase().contains(lowerQuery) ||
                contentItem.director.toLowerCase().contains(lowerQuery) ||
                contentItem.cast.stream().anyMatch(actor -> actor.toLowerCase().contains(lowerQuery))) {
                result.add(contentItem.contentId);
            }
        }
        
        return result;
    }
    
    /**
     * Get content by genre
     * 
     * @param genre Genre name
     * @return List of content IDs
     */
    public List<Integer> getContentByGenre(String genre) {
        List<Integer> result = new ArrayList<>();
        
        for (Content contentItem : content.values()) {
            if (contentItem.genres.contains(genre)) {
                result.add(contentItem.contentId);
            }
        }
        
        return result;
    }
    
    /**
     * Get trending content
     * 
     * @param limit Number of trending items
     * @return List of content IDs
     */
    public List<Integer> getTrendingContent(int limit) {
        List<Integer> result = new ArrayList<>();
        
        // Simple trending algorithm based on recent views and ratings
        List<Content> sortedContent = new ArrayList<>(content.values());
        sortedContent.sort((a, b) -> {
            // Sort by average rating first, then by total ratings
            int ratingCompare = Double.compare(b.averageRating, a.averageRating);
            if (ratingCompare != 0) {
                return ratingCompare;
            }
            return Integer.compare(b.totalRatings, a.totalRatings);
        });
        
        for (int i = 0; i < Math.min(limit, sortedContent.size()); i++) {
            result.add(sortedContent.get(i).contentId);
        }
        
        return result;
    }
    
    /**
     * Get user's viewing history
     * 
     * @param userId User ID
     * @return List of viewing history
     */
    public List<ViewingHistory> getViewingHistory(int userId) {
        if (!users.containsKey(userId)) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(users.get(userId).viewingHistory);
    }
    
    /**
     * Get content details
     * 
     * @param contentId Content ID
     * @return Content details
     */
    public Map<String, Object> getContentDetails(int contentId) {
        Map<String, Object> details = new HashMap<>();
        
        if (!content.containsKey(contentId)) {
            return details;
        }
        
        Content contentItem = content.get(contentId);
        details.put("contentId", contentItem.contentId);
        details.put("title", contentItem.title);
        details.put("description", contentItem.description);
        details.put("type", contentItem.type);
        details.put("genres", contentItem.genres);
        details.put("cast", contentItem.cast);
        details.put("director", contentItem.director);
        details.put("releaseYear", contentItem.releaseYear);
        details.put("duration", contentItem.duration);
        details.put("maturityRating", contentItem.maturityRating);
        details.put("averageRating", contentItem.averageRating);
        details.put("totalRatings", contentItem.totalRatings);
        
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
        stats.put("name", user.name);
        stats.put("totalViews", user.viewingHistory.size());
        stats.put("watchlistSize", user.watchlist.size());
        stats.put("totalRatings", user.ratings.size());
        
        // Calculate average rating given by user
        double totalRating = 0.0;
        for (double rating : user.ratings.values()) {
            totalRating += rating;
        }
        stats.put("averageRatingGiven", user.ratings.isEmpty() ? 0.0 : totalRating / user.ratings.size());
        
        return stats;
    }
    
    /**
     * Get content statistics
     * 
     * @param contentId Content ID
     * @return Content statistics
     */
    public Map<String, Object> getContentStats(int contentId) {
        Map<String, Object> stats = new HashMap<>();
        
        if (!content.containsKey(contentId)) {
            return stats;
        }
        
        Content contentItem = content.get(contentId);
        stats.put("contentId", contentItem.contentId);
        stats.put("title", contentItem.title);
        stats.put("averageRating", contentItem.averageRating);
        stats.put("totalRatings", contentItem.totalRatings);
        
        // Count total views
        int totalViews = 0;
        for (User user : users.values()) {
            for (ViewingHistory history : user.viewingHistory) {
                if (history.contentId == contentId) {
                    totalViews++;
                }
            }
        }
        stats.put("totalViews", totalViews);
        
        return stats;
    }
    
    /**
     * Demonstration method showing Netflix functionality
     */
    public static void runExample() {
        System.out.println("=== Design Netflix Example ===");
        
        DesignNetflix netflix = new DesignNetflix();
        
        // Register users
        int alice = netflix.registerUser("Alice", "alice@example.com");
        int bob = netflix.registerUser("Bob", "bob@example.com");
        
        System.out.println("Registered users: Alice(" + alice + "), Bob(" + bob + ")");
        
        // Add content
        int movie1 = netflix.addContent("The Matrix", "A computer hacker learns about the true nature of reality", ContentType.MOVIE);
        int movie2 = netflix.addContent("Inception", "A thief who steals corporate secrets through dream-sharing technology", ContentType.MOVIE);
        int show1 = netflix.addContent("Stranger Things", "Supernatural events in a small town", ContentType.TV_SHOW);
        
        System.out.println("Added content: The Matrix(" + movie1 + "), Inception(" + movie2 + "), Stranger Things(" + show1 + ")");
        
        // Update content details
        netflix.updateContentDetails(movie1, Arrays.asList("Action", "Sci-Fi"), 
                                   Arrays.asList("Keanu Reeves", "Laurence Fishburne"), 
                                   "The Wachowskis", 1999, 136, "R");
        
        netflix.updateContentDetails(movie2, Arrays.asList("Action", "Sci-Fi", "Thriller"), 
                                   Arrays.asList("Leonardo DiCaprio", "Marion Cotillard"), 
                                   "Christopher Nolan", 2010, 148, "PG-13");
        
        System.out.println("Updated content details");
        
        // Start streaming
        int viewing1 = netflix.startStreaming(alice, movie1);
        System.out.println("Alice started streaming The Matrix");
        
        // End streaming
        netflix.endStreaming(alice, movie1, true);
        System.out.println("Alice completed The Matrix");
        
        // Rate content
        netflix.rateContent(alice, movie1, 5.0);
        netflix.rateContent(bob, movie1, 4.5);
        System.out.println("Users rated The Matrix");
        
        // Add to watchlist
        netflix.addToWatchlist(alice, movie2);
        netflix.addToWatchlist(alice, show1);
        System.out.println("Alice added content to watchlist");
        
        // Get watchlist
        List<Integer> watchlist = netflix.getWatchlist(alice);
        System.out.println("Alice's watchlist: " + watchlist);
        
        // Get recommendations
        List<Recommendation> recommendations = netflix.getRecommendations(alice, 5);
        System.out.println("Recommendations for Alice:");
        for (Recommendation rec : recommendations) {
            System.out.println("  Content " + rec.contentId + " (score: " + rec.score + ", reason: " + rec.reason + ")");
        }
        
        // Search content
        List<Integer> searchResults = netflix.searchContent("matrix");
        System.out.println("Search results for 'matrix': " + searchResults);
        
        // Get content by genre
        List<Integer> actionContent = netflix.getContentByGenre("Action");
        System.out.println("Action content: " + actionContent);
        
        // Get trending content
        List<Integer> trending = netflix.getTrendingContent(3);
        System.out.println("Trending content: " + trending);
        
        // Get viewing history
        List<ViewingHistory> history = netflix.getViewingHistory(alice);
        System.out.println("Alice's viewing history: " + history.size() + " items");
        
        // Get content details
        Map<String, Object> movieDetails = netflix.getContentDetails(movie1);
        System.out.println("The Matrix details: " + movieDetails);
        
        // Get user statistics
        Map<String, Object> aliceStats = netflix.getUserStats(alice);
        System.out.println("Alice's statistics: " + aliceStats);
        
        // Get content statistics
        Map<String, Object> movieStats = netflix.getContentStats(movie1);
        System.out.println("The Matrix statistics: " + movieStats);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
