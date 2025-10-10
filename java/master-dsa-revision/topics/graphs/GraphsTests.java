import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Graphs topic problems
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
        
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = CourseSchedule.canFinish_dfs(numCourses, prerequisites2);
        assertFalse(result2);
    }
    
    @Test
    public void testCourseScheduleII() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        int[] result = CourseScheduleII.findOrder_bfs(numCourses, prerequisites);
        assertEquals(2, result.length);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }
    
    @Test
    public void testRedundantConnection() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] result = RedundantConnection.findRedundantConnection_unionFind(edges);
        assertEquals(2, result.length);
        assertEquals(2, result[0]);
        assertEquals(3, result[1]);
    }
}