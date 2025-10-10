package masterDSA.topics.graphs;
import java.util.*;

import masterDSA.topics.graphs.NumberOfIslands.NumberOfIslands;
import masterDSA.topics.graphs.CourseSchedule.CourseSchedule;
import masterDSA.topics.graphs.CloneGraph.CloneGraph;
import masterDSA.topics.graphs.WordLadder.WordLadder;
import masterDSA.topics.graphs.AlienDictionary.AlienDictionary;
import masterDSA.topics.graphs.GraphValidTree.GraphValidTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for graph problems
 * Tests representative methods from each graph problem
 */
public class GraphsTests {
    
    @Test
    public void testNumberOfIslands() {
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        int result = NumberOfIslands.numIslands_dfs(grid);
        assertEquals(1, result);
    }
    
    @Test
    public void testCourseSchedule() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        boolean result = CourseSchedule.canFinish_dfs(numCourses, prerequisites);
        assertTrue(result);
    }
    
    @Test
    public void testCloneGraph() {
        CloneGraph.Node node1 = new CloneGraph.Node(1);
        CloneGraph.Node node2 = new CloneGraph.Node(2);
        CloneGraph.Node node3 = new CloneGraph.Node(3);
        CloneGraph.Node node4 = new CloneGraph.Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        CloneGraph.Node cloned = CloneGraph.cloneGraph_dfs(node1);
        assertNotNull(cloned);
        assertEquals(1, cloned.val);
        assertEquals(2, cloned.neighbors.size());
    }
    
    @Test
    public void testWordLadder() {
        String beginWord = "hit";
        String endWord = "cog";
        java.util.List<String> wordList = java.util.Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = WordLadder.ladderLength_bfs(beginWord, endWord, wordList);
        assertEquals(5, result);
    }
    
    @Test
    public void testAlienDictionary() {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String result = AlienDictionary.alienOrder_topological(words);
        assertFalse(result.isEmpty());
        assertEquals(5, result.length());
    }
    
    @Test
    public void testGraphValidTree() {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        boolean result = GraphValidTree.validTree_unionFind(n, edges);
        assertTrue(result);
    }
}
