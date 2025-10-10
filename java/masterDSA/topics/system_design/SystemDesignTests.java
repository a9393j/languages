package masterDSA.topics.system_design;
import java.util.*;

import masterDSA.topics.system_design.DesignTwitter.DesignTwitter;
import masterDSA.topics.system_design.DesignFacebook.DesignFacebook;
import masterDSA.topics.system_design.DesignUber.DesignUber;
import masterDSA.topics.system_design.DesignNetflix.DesignNetflix;
import masterDSA.topics.system_design.DesignWhatsApp.DesignWhatsApp;
import masterDSA.topics.system_design.DesignInstagram.DesignInstagram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for system design problems
 * Tests representative methods from each system design implementation
 */
public class SystemDesignTests {
    
    @Test
    public void testDesignTwitter() {
        DesignTwitter twitter = new DesignTwitter();
        
        // Register users
        int user1 = twitter.registerUser("Alice", "alice@example.com");
        int user2 = twitter.registerUser("Bob", "bob@example.com");
        
        assertTrue(user1 > 0);
        assertTrue(user2 > 0);
        
        // Post tweets
        twitter.postTweet(user1, 1);
        twitter.postTweet(user1, 2);
        twitter.postTweet(user2, 3);
        
        // Follow users
        twitter.follow(user1, user2);
        
        // Get news feed
        List<Integer> feed = twitter.getNewsFeed(user1, 10);
        assertTrue(feed.size() > 0);
        
        // Get user tweets
        List<Integer> userTweets = twitter.getUserTweets(user1);
        assertEquals(2, userTweets.size());
    }
    
    @Test
    public void testDesignFacebook() {
        DesignFacebook facebook = new DesignFacebook();
        
        // Register users
        int alice = facebook.registerUser("Alice", "alice@example.com");
        int bob = facebook.registerUser("Bob", "bob@example.com");
        
        assertTrue(alice > 0);
        assertTrue(bob > 0);
        
        // Add friends
        boolean added = facebook.addFriend(alice, bob);
        assertTrue(added);
        
        // Create posts
        int post1 = facebook.createPost(alice, "Hello world!", DesignFacebook.PostType.TEXT);
        int post2 = facebook.createPost(bob, "Great day!", DesignFacebook.PostType.TEXT);
        
        assertTrue(post1 > 0);
        assertTrue(post2 > 0);
        
        // Get news feed
        List<Integer> feed = facebook.getNewsFeed(alice, 10);
        assertTrue(feed.size() > 0);
        
        // Like post
        boolean liked = facebook.likePost(bob, post1);
        assertTrue(liked);
        
        // Add comment
        int comment = facebook.addComment(bob, post1, "Nice post!");
        assertTrue(comment > 0);
    }
    
    @Test
    public void testDesignUber() {
        DesignUber uber = new DesignUber();
        
        // Register users
        int alice = uber.registerUser("Alice", "alice@example.com");
        int bob = uber.registerUser("Bob", "bob@example.com");
        
        assertTrue(alice > 0);
        assertTrue(bob > 0);
        
        // Register drivers
        DesignUber.Vehicle sedan = new DesignUber.Vehicle("ABC123", "Toyota", "Camry", 2020, DesignUber.VehicleType.SEDAN);
        int driver1 = uber.registerDriver("John", "john@example.com", sedan);
        
        assertTrue(driver1 > 0);
        
        // Update locations
        uber.updateUserLocation(alice, 40.7128, -74.0060);
        uber.updateDriverLocation(driver1, 40.7589, -73.9851);
        
        // Request ride
        int ride1 = uber.requestRide(alice, 40.7128, -74.0060, 40.7589, -73.9851);
        assertTrue(ride1 > 0);
        
        // Find available drivers
        DesignUber.Location pickup = new DesignUber.Location(40.7128, -74.0060);
        List<Integer> availableDrivers = uber.findAvailableDrivers(pickup, 10.0);
        assertTrue(availableDrivers.size() > 0);
        
        // Match ride
        boolean matched = uber.matchRide(ride1, driver1);
        assertTrue(matched);
        
        // Start ride
        boolean started = uber.startRide(ride1, driver1);
        assertTrue(started);
        
        // Complete ride
        boolean completed = uber.completeRide(ride1);
        assertTrue(completed);
    }
    
    @Test
    public void testDesignNetflix() {
        DesignNetflix netflix = new DesignNetflix();
        
        // Register users
        int alice = netflix.registerUser("Alice", "alice@example.com");
        int bob = netflix.registerUser("Bob", "bob@example.com");
        
        assertTrue(alice > 0);
        assertTrue(bob > 0);
        
        // Add content
        int movie1 = netflix.addContent("The Matrix", "A computer hacker learns about reality", DesignNetflix.ContentType.MOVIE);
        int movie2 = netflix.addContent("Inception", "A thief who steals corporate secrets", DesignNetflix.ContentType.MOVIE);
        
        assertTrue(movie1 > 0);
        assertTrue(movie2 > 0);
        
        // Update content details
        boolean updated = netflix.updateContentDetails(movie1, Arrays.asList("Action", "Sci-Fi"), 
                                                    Arrays.asList("Keanu Reeves"), "The Wachowskis", 
                                                    1999, 136, "R");
        assertTrue(updated);
        
        // Start streaming
        int viewing1 = netflix.startStreaming(alice, movie1);
        assertTrue(viewing1 > 0);
        
        // End streaming
        boolean ended = netflix.endStreaming(alice, movie1, true);
        assertTrue(ended);
        
        // Rate content
        boolean rated = netflix.rateContent(alice, movie1, 5.0);
        assertTrue(rated);
        
        // Add to watchlist
        boolean added = netflix.addToWatchlist(alice, movie2);
        assertTrue(added);
        
        // Get recommendations
        List<DesignNetflix.Recommendation> recommendations = netflix.getRecommendations(alice, 5);
        assertTrue(recommendations.size() >= 0);
    }
    
    @Test
    public void testDesignWhatsApp() {
        DesignWhatsApp whatsapp = new DesignWhatsApp();
        
        // Register users
        int alice = whatsapp.registerUser("Alice", "+1234567890");
        int bob = whatsapp.registerUser("Bob", "+0987654321");
        
        assertTrue(alice > 0);
        assertTrue(bob > 0);
        
        // Add contacts
        boolean added = whatsapp.addContact(alice, bob);
        assertTrue(added);
        
        // Create private chat
        int chat1 = whatsapp.createPrivateChat(alice, bob);
        assertTrue(chat1 > 0);
        
        // Send messages
        int msg1 = whatsapp.sendMessage(chat1, alice, "Hello Bob!", DesignWhatsApp.MessageType.TEXT);
        int msg2 = whatsapp.sendMessage(chat1, bob, "Hi Alice!", DesignWhatsApp.MessageType.TEXT);
        
        assertTrue(msg1 > 0);
        assertTrue(msg2 > 0);
        
        // Mark message as read
        boolean read = whatsapp.markMessageAsRead(msg1, bob);
        assertTrue(read);
        
        // Create group
        int group1 = whatsapp.createGroup("Friends", alice);
        assertTrue(group1 > 0);
        
        // Add group member
        boolean memberAdded = whatsapp.addGroupMember(group1, alice, bob);
        assertTrue(memberAdded);
        
        // Create status update
        int status1 = whatsapp.createStatusUpdate(alice, "Having a great day!", DesignWhatsApp.StatusType.TEXT);
        assertTrue(status1 > 0);
        
        // View status update
        boolean viewed = whatsapp.viewStatusUpdate(status1, bob);
        assertTrue(viewed);
        
        // Upload file
        int file1 = whatsapp.uploadFile("document.pdf", "application/pdf", 1024000, "/files/document.pdf", alice);
        assertTrue(file1 > 0);
        
        // Share file
        int fileMsg1 = whatsapp.shareFile(chat1, alice, file1);
        assertTrue(fileMsg1 > 0);
    }
    
    @Test
    public void testDesignInstagram() {
        DesignInstagram instagram = new DesignInstagram();
        
        // Register users
        int alice = instagram.registerUser("alice", "alice@example.com", "Alice Smith");
        int bob = instagram.registerUser("bob", "bob@example.com", "Bob Johnson");
        
        assertTrue(alice > 0);
        assertTrue(bob > 0);
        
        // Follow users
        boolean followed = instagram.followUser(alice, bob);
        assertTrue(followed);
        
        // Create posts
        int post1 = instagram.createPost(alice, "Beautiful sunset! #sunset #nature", 
                                       Arrays.asList("sunset1.jpg", "sunset2.jpg"));
        int post2 = instagram.createPost(bob, "Coffee time ☕ #coffee #morning", 
                                       Arrays.asList("coffee.jpg"));
        
        assertTrue(post1 > 0);
        assertTrue(post2 > 0);
        
        // Like posts
        boolean liked = instagram.likePost(post1, bob);
        assertTrue(liked);
        
        // Add comments
        int comment1 = instagram.addComment(post1, bob, "Amazing shot! 📸");
        assertTrue(comment1 > 0);
        
        // Reply to comment
        int reply1 = instagram.replyToComment(comment1, alice, "Thank you! 😊");
        assertTrue(reply1 > 0);
        
        // Create stories
        int story1 = instagram.createStory(alice, "story1.jpg", "Good morning!");
        assertTrue(story1 > 0);
        
        // View stories
        boolean viewed = instagram.viewStory(story1, bob);
        assertTrue(viewed);
        
        // Get feed
        List<Integer> feed = instagram.getFeed(alice, 10);
        assertTrue(feed.size() > 0);
        
        // Search users
        List<Integer> searchResults = instagram.searchUsers("alice");
        assertTrue(searchResults.size() > 0);
        
        // Search posts by hashtag
        List<Integer> sunsetPosts = instagram.searchPostsByHashtag("sunset");
        assertTrue(sunsetPosts.size() > 0);
        
        // Get trending hashtags
        List<String> trending = instagram.getTrendingHashtags(5);
        assertTrue(trending.size() > 0);
    }
}
