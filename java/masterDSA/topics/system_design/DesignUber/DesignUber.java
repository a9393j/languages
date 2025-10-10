package masterDSA.topics.system_design.DesignUber;
import java.util.*;


/**
 * Design Uber
 * 
 * Problem Statement:
 * Design a simplified version of Uber with features like user registration,
 * driver registration, ride booking, ride matching, and payment processing.
 * 
 * LeetCode Link: N/A (System Design Problem)
 * Difficulty: Hard
 * Tags: System Design, Hash Table, Graph, Priority Queue
 * 
 * Approach Summary:
 * 1. Use HashMap to store users, drivers, and rides
 * 2. Use Graph structure for location and routing
 * 3. Use PriorityQueue for driver matching based on distance
 * 4. Implement real-time location tracking
 * 5. Handle ride states and transitions
 * 
 * Pseudocode:
 * 1. Create User and Driver classes
 * 2. Create Ride class with states
 * 3. Implement location-based matching
 * 4. Handle ride lifecycle (requested, matched, started, completed)
 * 5. Implement payment and rating systems
 * 
 * Time Complexity: O(n log n) for driver matching
 * Space Complexity: O(n + m) where n is users/drivers, m is rides
 */
public class DesignUber {
    
    // User class
    static class User {
        int userId;
        String name;
        String email;
        Location currentLocation;
        List<Ride> rideHistory;
        double rating;
        int totalRides;
        
        User(int userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.currentLocation = new Location(0, 0);
            this.rideHistory = new ArrayList<>();
            this.rating = 5.0;
            this.totalRides = 0;
        }
        
        void updateLocation(double latitude, double longitude) {
            this.currentLocation = new Location(latitude, longitude);
        }
        
        void addRide(Ride ride) {
            rideHistory.add(ride);
            totalRides++;
        }
        
        void updateRating(double newRating) {
            this.rating = (this.rating * (totalRides - 1) + newRating) / totalRides;
        }
    }
    
    // Driver class
    static class Driver {
        int driverId;
        String name;
        String email;
        Location currentLocation;
        boolean isAvailable;
        Vehicle vehicle;
        List<Ride> rideHistory;
        double rating;
        int totalRides;
        
        Driver(int driverId, String name, String email, Vehicle vehicle) {
            this.driverId = driverId;
            this.name = name;
            this.email = email;
            this.currentLocation = new Location(0, 0);
            this.isAvailable = true;
            this.vehicle = vehicle;
            this.rideHistory = new ArrayList<>();
            this.rating = 5.0;
            this.totalRides = 0;
        }
        
        void updateLocation(double latitude, double longitude) {
            this.currentLocation = new Location(latitude, longitude);
        }
        
        void addRide(Ride ride) {
            rideHistory.add(ride);
            totalRides++;
        }
        
        void updateRating(double newRating) {
            this.rating = (this.rating * (totalRides - 1) + newRating) / totalRides;
        }
        
        double getDistanceTo(Location location) {
            return currentLocation.distanceTo(location);
        }
    }
    
    // Vehicle class
    static class Vehicle {
        String licensePlate;
        String make;
        String model;
        int year;
        VehicleType type;
        
        Vehicle(String licensePlate, String make, String model, int year, VehicleType type) {
            this.licensePlate = licensePlate;
            this.make = make;
            this.model = model;
            this.year = year;
            this.type = type;
        }
    }
    
    // Location class
    static class Location {
        double latitude;
        double longitude;
        
        Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
        
        double distanceTo(Location other) {
            // Simple Euclidean distance calculation
            double latDiff = this.latitude - other.latitude;
            double lonDiff = this.longitude - other.longitude;
            return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
        }
    }
    
    // Ride class
    static class Ride {
        int rideId;
        int userId;
        int driverId;
        Location pickupLocation;
        Location dropoffLocation;
        RideStatus status;
        double fare;
        long startTime;
        long endTime;
        Payment payment;
        Rating userRating;
        Rating driverRating;
        
        Ride(int rideId, int userId, Location pickupLocation, Location dropoffLocation) {
            this.rideId = rideId;
            this.userId = userId;
            this.pickupLocation = pickupLocation;
            this.dropoffLocation = dropoffLocation;
            this.status = RideStatus.REQUESTED;
            this.fare = calculateFare(pickupLocation, dropoffLocation);
            this.startTime = 0;
            this.endTime = 0;
        }
        
        private double calculateFare(Location pickup, Location dropoff) {
            double distance = pickup.distanceTo(dropoff);
            return distance * 2.0; // $2 per unit distance
        }
        
        void startRide(int driverId) {
            this.driverId = driverId;
            this.status = RideStatus.STARTED;
            this.startTime = System.currentTimeMillis();
        }
        
        void completeRide() {
            this.status = RideStatus.COMPLETED;
            this.endTime = System.currentTimeMillis();
        }
        
        void cancelRide() {
            this.status = RideStatus.CANCELLED;
            this.endTime = System.currentTimeMillis();
        }
    }
    
    // Payment class
    static class Payment {
        int paymentId;
        int rideId;
        double amount;
        PaymentMethod method;
        PaymentStatus status;
        long timestamp;
        
        Payment(int paymentId, int rideId, double amount, PaymentMethod method) {
            this.paymentId = paymentId;
            this.rideId = rideId;
            this.amount = amount;
            this.method = method;
            this.status = PaymentStatus.PENDING;
            this.timestamp = System.currentTimeMillis();
        }
        
        void processPayment() {
            this.status = PaymentStatus.COMPLETED;
        }
        
        void failPayment() {
            this.status = PaymentStatus.FAILED;
        }
    }
    
    // Rating class
    static class Rating {
        int rideId;
        int fromUserId;
        int toUserId;
        double rating;
        String comment;
        long timestamp;
        
        Rating(int rideId, int fromUserId, int toUserId, double rating, String comment) {
            this.rideId = rideId;
            this.fromUserId = fromUserId;
            this.toUserId = toUserId;
            this.rating = rating;
            this.comment = comment;
            this.timestamp = System.currentTimeMillis();
        }
    }
    
    // Enums
    enum RideStatus {
        REQUESTED, MATCHED, STARTED, COMPLETED, CANCELLED
    }
    
    enum VehicleType {
        SEDAN, SUV, LUXURY, SHARED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, PAYPAL, CASH
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
    
    private Map<Integer, User> users;
    private Map<Integer, Driver> drivers;
    private Map<Integer, Ride> rides;
    private Map<Integer, Payment> payments;
    private int nextUserId;
    private int nextDriverId;
    private int nextRideId;
    private int nextPaymentId;
    
    public DesignUber() {
        users = new HashMap<>();
        drivers = new HashMap<>();
        rides = new HashMap<>();
        payments = new HashMap<>();
        nextUserId = 1;
        nextDriverId = 1;
        nextRideId = 1;
        nextPaymentId = 1;
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
     * Register a new driver
     * 
     * @param name Driver's name
     * @param email Driver's email
     * @param vehicle Driver's vehicle
     * @return Driver ID
     */
    public int registerDriver(String name, String email, Vehicle vehicle) {
        int driverId = nextDriverId++;
        Driver driver = new Driver(driverId, name, email, vehicle);
        drivers.put(driverId, driver);
        return driverId;
    }
    
    /**
     * Update user location
     * 
     * @param userId User ID
     * @param latitude Latitude
     * @param longitude Longitude
     * @return True if successful
     */
    public boolean updateUserLocation(int userId, double latitude, double longitude) {
        if (!users.containsKey(userId)) {
            return false;
        }
        
        users.get(userId).updateLocation(latitude, longitude);
        return true;
    }
    
    /**
     * Update driver location
     * 
     * @param driverId Driver ID
     * @param latitude Latitude
     * @param longitude Longitude
     * @return True if successful
     */
    public boolean updateDriverLocation(int driverId, double latitude, double longitude) {
        if (!drivers.containsKey(driverId)) {
            return false;
        }
        
        drivers.get(driverId).updateLocation(latitude, longitude);
        return true;
    }
    
    /**
     * Request a ride
     * 
     * @param userId User ID
     * @param pickupLatitude Pickup latitude
     * @param pickupLongitude Pickup longitude
     * @param dropoffLatitude Dropoff latitude
     * @param dropoffLongitude Dropoff longitude
     * @return Ride ID
     */
    public int requestRide(int userId, double pickupLatitude, double pickupLongitude,
                          double dropoffLatitude, double dropoffLongitude) {
        if (!users.containsKey(userId)) {
            return -1;
        }
        
        int rideId = nextRideId++;
        Location pickupLocation = new Location(pickupLatitude, pickupLongitude);
        Location dropoffLocation = new Location(dropoffLatitude, dropoffLongitude);
        
        Ride ride = new Ride(rideId, userId, pickupLocation, dropoffLocation);
        rides.put(rideId, ride);
        
        return rideId;
    }
    
    /**
     * Find available drivers near pickup location
     * 
     * @param pickupLocation Pickup location
     * @param maxDistance Maximum distance to search
     * @return List of available driver IDs
     */
    public List<Integer> findAvailableDrivers(Location pickupLocation, double maxDistance) {
        List<Integer> availableDrivers = new ArrayList<>();
        
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable && driver.getDistanceTo(pickupLocation) <= maxDistance) {
                availableDrivers.add(driver.driverId);
            }
        }
        
        // Sort by distance
        availableDrivers.sort((a, b) -> {
            double distanceA = drivers.get(a).getDistanceTo(pickupLocation);
            double distanceB = drivers.get(b).getDistanceTo(pickupLocation);
            return Double.compare(distanceA, distanceB);
        });
        
        return availableDrivers;
    }
    
    /**
     * Match a ride with a driver
     * 
     * @param rideId Ride ID
     * @param driverId Driver ID
     * @return True if successful
     */
    public boolean matchRide(int rideId, int driverId) {
        if (!rides.containsKey(rideId) || !drivers.containsKey(driverId)) {
            return false;
        }
        
        Ride ride = rides.get(rideId);
        Driver driver = drivers.get(driverId);
        
        if (ride.status != RideStatus.REQUESTED || !driver.isAvailable) {
            return false;
        }
        
        ride.status = RideStatus.MATCHED;
        driver.isAvailable = false;
        
        return true;
    }
    
    /**
     * Start a ride
     * 
     * @param rideId Ride ID
     * @param driverId Driver ID
     * @return True if successful
     */
    public boolean startRide(int rideId, int driverId) {
        if (!rides.containsKey(rideId) || !drivers.containsKey(driverId)) {
            return false;
        }
        
        Ride ride = rides.get(rideId);
        Driver driver = drivers.get(driverId);
        
        if (ride.status != RideStatus.MATCHED || ride.driverId != driverId) {
            return false;
        }
        
        ride.startRide(driverId);
        return true;
    }
    
    /**
     * Complete a ride
     * 
     * @param rideId Ride ID
     * @return True if successful
     */
    public boolean completeRide(int rideId) {
        if (!rides.containsKey(rideId)) {
            return false;
        }
        
        Ride ride = rides.get(rideId);
        
        if (ride.status != RideStatus.STARTED) {
            return false;
        }
        
        ride.completeRide();
        
        // Make driver available again
        if (drivers.containsKey(ride.driverId)) {
            drivers.get(ride.driverId).isAvailable = true;
        }
        
        // Add ride to user and driver history
        if (users.containsKey(ride.userId)) {
            users.get(ride.userId).addRide(ride);
        }
        
        if (drivers.containsKey(ride.driverId)) {
            drivers.get(ride.driverId).addRide(ride);
        }
        
        return true;
    }
    
    /**
     * Cancel a ride
     * 
     * @param rideId Ride ID
     * @return True if successful
     */
    public boolean cancelRide(int rideId) {
        if (!rides.containsKey(rideId)) {
            return false;
        }
        
        Ride ride = rides.get(rideId);
        
        if (ride.status == RideStatus.COMPLETED) {
            return false;
        }
        
        ride.cancelRide();
        
        // Make driver available again if ride was matched
        if (ride.status == RideStatus.MATCHED && drivers.containsKey(ride.driverId)) {
            drivers.get(ride.driverId).isAvailable = true;
        }
        
        return true;
    }
    
    /**
     * Process payment for a ride
     * 
     * @param rideId Ride ID
     * @param paymentMethod Payment method
     * @return Payment ID
     */
    public int processPayment(int rideId, PaymentMethod paymentMethod) {
        if (!rides.containsKey(rideId)) {
            return -1;
        }
        
        Ride ride = rides.get(rideId);
        
        if (ride.status != RideStatus.COMPLETED) {
            return -1;
        }
        
        int paymentId = nextPaymentId++;
        Payment payment = new Payment(paymentId, rideId, ride.fare, paymentMethod);
        payments.put(paymentId, payment);
        
        // Simulate payment processing
        payment.processPayment();
        ride.payment = payment;
        
        return paymentId;
    }
    
    /**
     * Rate a ride
     * 
     * @param rideId Ride ID
     * @param fromUserId User giving the rating
     * @param toUserId User receiving the rating
     * @param rating Rating value (1-5)
     * @param comment Optional comment
     * @return True if successful
     */
    public boolean rateRide(int rideId, int fromUserId, int toUserId, double rating, String comment) {
        if (!rides.containsKey(rideId)) {
            return false;
        }
        
        Ride ride = rides.get(rideId);
        
        if (ride.status != RideStatus.COMPLETED) {
            return false;
        }
        
        if (rating < 1.0 || rating > 5.0) {
            return false;
        }
        
        Rating rideRating = new Rating(rideId, fromUserId, toUserId, rating, comment);
        
        if (fromUserId == ride.userId) {
            ride.userRating = rideRating;
            if (drivers.containsKey(toUserId)) {
                drivers.get(toUserId).updateRating(rating);
            }
        } else if (fromUserId == ride.driverId) {
            ride.driverRating = rideRating;
            if (users.containsKey(toUserId)) {
                users.get(toUserId).updateRating(rating);
            }
        } else {
            return false;
        }
        
        return true;
    }
    
    /**
     * Get ride details
     * 
     * @param rideId Ride ID
     * @return Ride details
     */
    public Map<String, Object> getRideDetails(int rideId) {
        Map<String, Object> details = new HashMap<>();
        
        if (!rides.containsKey(rideId)) {
            return details;
        }
        
        Ride ride = rides.get(rideId);
        details.put("rideId", ride.rideId);
        details.put("userId", ride.userId);
        details.put("driverId", ride.driverId);
        details.put("status", ride.status);
        details.put("fare", ride.fare);
        details.put("startTime", ride.startTime);
        details.put("endTime", ride.endTime);
        
        return details;
    }
    
    /**
     * Get user's ride history
     * 
     * @param userId User ID
     * @return List of ride IDs
     */
    public List<Integer> getUserRideHistory(int userId) {
        List<Integer> history = new ArrayList<>();
        
        if (!users.containsKey(userId)) {
            return history;
        }
        
        User user = users.get(userId);
        for (Ride ride : user.rideHistory) {
            history.add(ride.rideId);
        }
        
        return history;
    }
    
    /**
     * Get driver's ride history
     * 
     * @param driverId Driver ID
     * @return List of ride IDs
     */
    public List<Integer> getDriverRideHistory(int driverId) {
        List<Integer> history = new ArrayList<>();
        
        if (!drivers.containsKey(driverId)) {
            return history;
        }
        
        Driver driver = drivers.get(driverId);
        for (Ride ride : driver.rideHistory) {
            history.add(ride.rideId);
        }
        
        return history;
    }
    
    /**
     * Get available drivers count
     * 
     * @return Number of available drivers
     */
    public int getAvailableDriversCount() {
        int count = 0;
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable) {
                count++;
            }
        }
        return count;
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
        stats.put("totalRides", user.totalRides);
        stats.put("rating", user.rating);
        stats.put("currentLocation", user.currentLocation);
        
        return stats;
    }
    
    /**
     * Get driver statistics
     * 
     * @param driverId Driver ID
     * @return Driver statistics
     */
    public Map<String, Object> getDriverStats(int driverId) {
        Map<String, Object> stats = new HashMap<>();
        
        if (!drivers.containsKey(driverId)) {
            return stats;
        }
        
        Driver driver = drivers.get(driverId);
        stats.put("driverId", driver.driverId);
        stats.put("name", driver.name);
        stats.put("totalRides", driver.totalRides);
        stats.put("rating", driver.rating);
        stats.put("isAvailable", driver.isAvailable);
        stats.put("vehicle", driver.vehicle);
        stats.put("currentLocation", driver.currentLocation);
        
        return stats;
    }
    
    /**
     * Demonstration method showing Uber functionality
     */
    public static void runExample() {
        System.out.println("=== Design Uber Example ===");
        
        DesignUber uber = new DesignUber();
        
        // Register users
        int alice = uber.registerUser("Alice", "alice@example.com");
        int bob = uber.registerUser("Bob", "bob@example.com");
        
        // Register drivers
        Vehicle sedan = new Vehicle("ABC123", "Toyota", "Camry", 2020, VehicleType.SEDAN);
        Vehicle suv = new Vehicle("XYZ789", "Honda", "CR-V", 2019, VehicleType.SUV);
        
        int driver1 = uber.registerDriver("John", "john@example.com", sedan);
        int driver2 = uber.registerDriver("Jane", "jane@example.com", suv);
        
        System.out.println("Registered users: Alice(" + alice + "), Bob(" + bob + ")");
        System.out.println("Registered drivers: John(" + driver1 + "), Jane(" + driver2 + ")");
        
        // Update locations
        uber.updateUserLocation(alice, 40.7128, -74.0060); // NYC
        uber.updateDriverLocation(driver1, 40.7589, -73.9851); // Near NYC
        uber.updateDriverLocation(driver2, 40.6892, -74.0445); // Near NYC
        
        System.out.println("Updated user and driver locations");
        
        // Request a ride
        int ride1 = uber.requestRide(alice, 40.7128, -74.0060, 40.7589, -73.9851);
        System.out.println("Alice requested ride: " + ride1);
        
        // Find available drivers
        Location pickup = new Location(40.7128, -74.0060);
        List<Integer> availableDrivers = uber.findAvailableDrivers(pickup, 10.0);
        System.out.println("Available drivers near pickup: " + availableDrivers);
        
        // Match ride with driver
        if (!availableDrivers.isEmpty()) {
            int matchedDriver = availableDrivers.get(0);
            uber.matchRide(ride1, matchedDriver);
            System.out.println("Matched ride " + ride1 + " with driver " + matchedDriver);
        }
        
        // Start ride
        uber.startRide(ride1, driver1);
        System.out.println("Started ride " + ride1);
        
        // Complete ride
        uber.completeRide(ride1);
        System.out.println("Completed ride " + ride1);
        
        // Process payment
        int payment1 = uber.processPayment(ride1, PaymentMethod.CREDIT_CARD);
        System.out.println("Processed payment " + payment1 + " for ride " + ride1);
        
        // Rate the ride
        uber.rateRide(ride1, alice, driver1, 5.0, "Great ride!");
        uber.rateRide(ride1, driver1, alice, 5.0, "Nice passenger!");
        System.out.println("Rated ride " + ride1);
        
        // Get ride details
        Map<String, Object> rideDetails = uber.getRideDetails(ride1);
        System.out.println("Ride " + ride1 + " details: " + rideDetails);
        
        // Get user statistics
        Map<String, Object> aliceStats = uber.getUserStats(alice);
        System.out.println("Alice's statistics: " + aliceStats);
        
        // Get driver statistics
        Map<String, Object> driver1Stats = uber.getDriverStats(driver1);
        System.out.println("Driver 1's statistics: " + driver1Stats);
        
        // Get ride history
        List<Integer> aliceHistory = uber.getUserRideHistory(alice);
        System.out.println("Alice's ride history: " + aliceHistory);
        
        // Get available drivers count
        int availableCount = uber.getAvailableDriversCount();
        System.out.println("Available drivers count: " + availableCount);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
