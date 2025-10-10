package masterDSA.topics.arrays.TournamentWinner;
import java.util.*;


/**
 * Tournament Winner
 * 
 * Given an array of pairs representing the results of competitions, where each pair 
 * contains [homeTeam, awayTeam] and the results array contains 1 for home team win 
 * and 0 for away team win, return the team with the highest score.
 * 
 * LeetCode: 
 * Difficulty: Easy
 * Tags: hashmap, score-tracking
 * 
 * Approach:
 * - Use HashMap to track team scores
 * - Iterate through competitions and results
 * - Award 3 points for win, 0 for loss
 * - Keep track of highest scoring team
 * 
 * Pseudocode:
 * 1. Initialize HashMap to store team -> score
 * 2. For each competition and result:
 *    a. Determine winning team based on result
 *    b. Add 3 points to winning team's score
 *    c. Update highest scoring team if necessary
 * 3. Return highest scoring team
 */
public class TournamentWinner {
    
    /**
     * HashMap approach - O(n) time, O(k) space where k is number of teams
     * Use HashMap to track team scores and find winner
     */
    public static String tournamentWinner_hashMap(String[][] competitions, int[] results) {
        java.util.Map<String, Integer> teamScores = new java.util.HashMap<>();
        String currentBestTeam = "";
        int highestScore = 0;
        
        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            int result = results[i];
            
            // Determine winning team (1 = home team wins, 0 = away team wins)
            String winningTeam = (result == 1) ? homeTeam : awayTeam;
            
            // Update team score
            teamScores.put(winningTeam, teamScores.getOrDefault(winningTeam, 0) + 3);
            
            // Update current best team
            int currentScore = teamScores.get(winningTeam);
            if (currentScore > highestScore) {
                highestScore = currentScore;
                currentBestTeam = winningTeam;
            }
        }
        
        return currentBestTeam;
    }
    
    /**
     * HashMap approach with detailed tracking - O(n) time, O(k) space
     * Track all team scores and return detailed information
     */
    public static java.util.Map<String, Object> tournamentWinner_detailed(String[][] competitions, int[] results) {
        java.util.Map<String, Integer> teamScores = new java.util.HashMap<>();
        java.util.Map<String, Integer> teamWins = new java.util.HashMap<>();
        java.util.Map<String, Integer> teamLosses = new java.util.HashMap<>();
        
        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            int result = results[i];
            
            String winningTeam = (result == 1) ? homeTeam : awayTeam;
            String losingTeam = (result == 1) ? awayTeam : homeTeam;
            
            // Update scores (3 points for win, 0 for loss)
            teamScores.put(winningTeam, teamScores.getOrDefault(winningTeam, 0) + 3);
            teamScores.put(losingTeam, teamScores.getOrDefault(losingTeam, 0) + 0);
            
            // Update win/loss counts
            teamWins.put(winningTeam, teamWins.getOrDefault(winningTeam, 0) + 1);
            teamLosses.put(losingTeam, teamLosses.getOrDefault(losingTeam, 0) + 1);
        }
        
        // Find winner
        String winner = "";
        int highestScore = 0;
        for (java.util.Map.Entry<String, Integer> entry : teamScores.entrySet()) {
            if (entry.getValue() > highestScore) {
                highestScore = entry.getValue();
                winner = entry.getKey();
            }
        }
        
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("winner", winner);
        result.put("score", highestScore);
        result.put("teamScores", teamScores);
        result.put("teamWins", teamWins);
        result.put("teamLosses", teamLosses);
        
        return result;
    }
    
    /**
     * Alternative scoring system - O(n) time, O(k) space
     * Award different points based on competition type
     */
    public static String tournamentWinner_alternativeScoring(String[][] competitions, int[] results) {
        java.util.Map<String, Integer> teamScores = new java.util.HashMap<>();
        String currentBestTeam = "";
        int highestScore = 0;
        
        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            int result = results[i];
            
            String winningTeam = (result == 1) ? homeTeam : awayTeam;
            String losingTeam = (result == 1) ? awayTeam : homeTeam;
            
            // Alternative scoring: 3 points for win, 1 point for loss
            teamScores.put(winningTeam, teamScores.getOrDefault(winningTeam, 0) + 3);
            teamScores.put(losingTeam, teamScores.getOrDefault(losingTeam, 0) + 1);
            
            // Update current best team
            int currentScore = teamScores.get(winningTeam);
            if (currentScore > highestScore) {
                highestScore = currentScore;
                currentBestTeam = winningTeam;
            }
        }
        
        return currentBestTeam;
    }
    
    /**
     * Brute force approach - O(n²) time, O(k) space
     * Count wins for each team and find team with most wins
     */
    public static String tournamentWinner_bruteForce(String[][] competitions, int[] results) {
        java.util.Map<String, Integer> teamWins = new java.util.HashMap<>();
        
        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            int result = results[i];
            
            String winningTeam = (result == 1) ? homeTeam : awayTeam;
            teamWins.put(winningTeam, teamWins.getOrDefault(winningTeam, 0) + 1);
        }
        
        String winner = "";
        int maxWins = 0;
        for (java.util.Map.Entry<String, Integer> entry : teamWins.entrySet()) {
            if (entry.getValue() > maxWins) {
                maxWins = entry.getValue();
                winner = entry.getKey();
            }
        }
        
        return winner;
    }
    
    /**
     * Helper method to print tournament results
     */
    public static void printTournamentResults(String[][] competitions, int[] results) {
        System.out.println("Tournament Results:");
        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            int result = results[i];
            String winner = (result == 1) ? homeTeam : awayTeam;
            System.out.println(homeTeam + " vs " + awayTeam + " -> " + winner + " wins");
        }
    }
    
    public static void main(String[] args) {
        // Test case: competitions = [["HTML", "C#"], ["C#", "Python"], ["Python", "HTML"]]
        // results = [0, 0, 1]
        String[][] competitions = {{"HTML", "C#"}, {"C#", "Python"}, {"Python", "HTML"}};
        int[] results = {0, 0, 1};
        
        System.out.println("Testing Tournament Winner:");
        printTournamentResults(competitions, results);
        
        String result1 = tournamentWinner_hashMap(competitions, results);
        System.out.println("HashMap result: " + result1);
        
        java.util.Map<String, Object> detailedResult = tournamentWinner_detailed(competitions, results);
        System.out.println("Detailed result: " + detailedResult);
        
        String result2 = tournamentWinner_alternativeScoring(competitions, results);
        System.out.println("Alternative scoring result: " + result2);
        
        String result3 = tournamentWinner_bruteForce(competitions, results);
        System.out.println("Brute force result: " + result3);
        
        // Expected: "Python" (2 wins, 6 points)
        
        System.out.println();
        
        // Test case: competitions = [["A", "B"], ["B", "C"], ["C", "A"]]
        // results = [1, 1, 1]
        String[][] competitions2 = {{"A", "B"}, {"B", "C"}, {"C", "A"}};
        int[] results2 = {1, 1, 1};
        
        System.out.println("Second test case:");
        printTournamentResults(competitions2, results2);
        
        String result4 = tournamentWinner_hashMap(competitions2, results2);
        System.out.println("HashMap result: " + result4);
        
        // Expected: "A" (1 win, 3 points) - tiebreaker by first occurrence
    }
}
