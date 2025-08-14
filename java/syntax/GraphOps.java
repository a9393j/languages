package syntax;

import java.util.*;

public class GraphOps {

    public static void graphBasics() {
        System.out.println("------------------------------------------");
        System.out.println("GRAPH BASICS");
        System.out.println("---------------------");

        // Adjacency Matrix representation
        int[][] adjacencyMatrix = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };
        System.out.println("Adjacency Matrix:");
        printMatrix(adjacencyMatrix);

        // Adjacency List representation
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        adjacencyList.put(0, Arrays.asList(1, 2));
        adjacencyList.put(1, Arrays.asList(0, 2, 3));
        adjacencyList.put(2, Arrays.asList(0, 1, 3));
        adjacencyList.put(3, Arrays.asList(1, 2));
        System.out.println("Adjacency List: " + adjacencyList);

        // Edge List representation
        List<int[]> edgeList = Arrays.asList(
            new int[]{0, 1},
            new int[]{0, 2},
            new int[]{1, 2},
            new int[]{1, 3},
            new int[]{2, 3}
        );
        System.out.println("Edge List: " + Arrays.deepToString(edgeList.toArray()));
    }

    public static void graphTraversal() {
        System.out.println("------------------------------------------");
        System.out.println("GRAPH TRAVERSAL");
        System.out.println("---------------------");

        // Create a sample graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 2, 3));
        graph.put(2, Arrays.asList(0, 1, 3, 4));
        graph.put(3, Arrays.asList(1, 2, 4));
        graph.put(4, Arrays.asList(2, 3));

        System.out.println("Graph: " + graph);

        // BFS
        System.out.println("BFS starting from 0: " + bfs(graph, 0));

        // DFS
        System.out.println("DFS starting from 0: " + dfs(graph, 0));

        // DFS Recursive
        System.out.println("DFS Recursive starting from 0: " + dfsRecursive(graph, 0));
    }

    public static void graphAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("GRAPH ALGORITHMS");
        System.out.println("---------------------");

        // Create a weighted graph for shortest path
        Map<Integer, List<int[]>> weightedGraph = new HashMap<>();
        weightedGraph.put(0, Arrays.asList(new int[]{1, 4}, new int[]{2, 2}));
        weightedGraph.put(1, Arrays.asList(new int[]{2, 1}, new int[]{3, 5}));
        weightedGraph.put(2, Arrays.asList(new int[]{1, 1}, new int[]{3, 8}));
        weightedGraph.put(3, Arrays.asList(new int[]{0, 3}));

        System.out.println("Weighted Graph: " + weightedGraph);

        // Dijkstra's shortest path
        int[] distances = dijkstra(weightedGraph, 0, 4);
        System.out.println("Dijkstra distances from 0: " + Arrays.toString(distances));

        // Check if graph is connected
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 2));
        graph.put(2, Arrays.asList(0, 1));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList(3));

        System.out.println("Is graph connected? " + isConnected(graph));

        // Count connected components
        System.out.println("Number of connected components: " + countConnectedComponents(graph));
    }

    public static void treeAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("TREE ALGORITHMS");
        System.out.println("---------------------");

        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Binary Tree Traversals:");
        System.out.println("Inorder: " + inorderTraversal(root));
        System.out.println("Preorder: " + preorderTraversal(root));
        System.out.println("Postorder: " + postorderTraversal(root));
        System.out.println("Level order: " + levelOrderTraversal(root));

        // Tree height
        System.out.println("Tree height: " + getTreeHeight(root));

        // Check if balanced
        System.out.println("Is tree balanced? " + isBalanced(root));
    }

    public static void advancedGraphProblems() {
        System.out.println("------------------------------------------");
        System.out.println("ADVANCED GRAPH PROBLEMS");
        System.out.println("---------------------");

        // Topological Sort
        Map<Integer, List<Integer>> dag = new HashMap<>();
        dag.put(0, Arrays.asList(1, 2));
        dag.put(1, Arrays.asList(3));
        dag.put(2, Arrays.asList(3));
        dag.put(3, Arrays.asList(4));
        dag.put(4, new ArrayList<>());

        System.out.println("Topological Sort: " + topologicalSort(dag));

        // Cycle Detection
        Map<Integer, List<Integer>> graphWithCycle = new HashMap<>();
        graphWithCycle.put(0, Arrays.asList(1));
        graphWithCycle.put(1, Arrays.asList(2));
        graphWithCycle.put(2, Arrays.asList(0));

        System.out.println("Has cycle? " + hasCycle(graphWithCycle));

        // Bipartite Graph Check
        Map<Integer, List<Integer>> bipartiteGraph = new HashMap<>();
        bipartiteGraph.put(0, Arrays.asList(1, 3));
        bipartiteGraph.put(1, Arrays.asList(0, 2));
        bipartiteGraph.put(2, Arrays.asList(1, 3));
        bipartiteGraph.put(3, Arrays.asList(0, 2));

        System.out.println("Is bipartite? " + isBipartite(bipartiteGraph));
    }

    // Helper methods for Graph Traversal
    public static List<Integer> bfs(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }

    public static List<Integer> dfs(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(start);
        visited.add(start);
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
            result.add(current);
            
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
        
        return result;
    }

    public static List<Integer> dfsRecursive(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsRecursiveHelper(graph, start, visited, result);
        return result;
    }

    private static void dfsRecursiveHelper(Map<Integer, List<Integer>> graph, int node, 
                                         Set<Integer> visited, List<Integer> result) {
        visited.add(node);
        result.add(node);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsRecursiveHelper(graph, neighbor, visited, result);
            }
        }
    }

    // Helper methods for Graph Algorithms
    public static int[] dijkstra(Map<Integer, List<int[]>> graph, int start, int n) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];
            
            if (dist > distances[node]) continue;
            
            for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
                int neighbor = edge[0];
                int weight = edge[1];
                
                if (distances[node] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[node] + weight;
                    pq.offer(new int[]{neighbor, distances[neighbor]});
                }
            }
        }
        
        return distances;
    }

    public static boolean isConnected(Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) return true;
        
        Set<Integer> visited = new HashSet<>();
        int start = graph.keySet().iterator().next();
        dfsRecursiveHelper(graph, start, visited, new ArrayList<>());
        
        return visited.size() == graph.size();
    }

    public static int countConnectedComponents(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsRecursiveHelper(graph, node, visited, new ArrayList<>());
                components++;
            }
        }
        
        return components;
    }

    // Helper methods for Tree Algorithms
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private static void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.val);
    }

    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            
            result.add(level);
        }
        
        return result;
    }

    public static int getTreeHeight(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Helper methods for Advanced Graph Problems
    public static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();
        
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (topologicalSortHelper(graph, node, visited, recStack, result)) {
                    return new ArrayList<>(); // Cycle detected
                }
            }
        }
        
        Collections.reverse(result);
        return result;
    }

    private static boolean topologicalSortHelper(Map<Integer, List<Integer>> graph, int node,
                                               Set<Integer> visited, Set<Integer> recStack,
                                               List<Integer> result) {
        visited.add(node);
        recStack.add(node);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (topologicalSortHelper(graph, neighbor, visited, recStack, result)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true; // Cycle detected
            }
        }
        
        recStack.remove(node);
        result.add(node);
        return false;
    }

    public static boolean hasCycle(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();
        
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleHelper(graph, node, visited, recStack)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private static boolean hasCycleHelper(Map<Integer, List<Integer>> graph, int node,
                                        Set<Integer> visited, Set<Integer> recStack) {
        visited.add(node);
        recStack.add(node);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (hasCycleHelper(graph, neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true;
            }
        }
        
        recStack.remove(node);
        return false;
    }

    public static boolean isBipartite(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> colors = new HashMap<>();
        
        for (int node : graph.keySet()) {
            if (!colors.containsKey(node)) {
                if (!isBipartiteHelper(graph, node, colors, 0)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private static boolean isBipartiteHelper(Map<Integer, List<Integer>> graph, int node,
                                           Map<Integer, Integer> colors, int color) {
        if (colors.containsKey(node)) {
            return colors.get(node) == color;
        }
        
        colors.put(node, color);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!isBipartiteHelper(graph, neighbor, colors, 1 - color)) {
                return false;
            }
        }
        
        return true;
    }

    // Utility method
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // TreeNode class for binary tree
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        graphBasics();
        graphTraversal();
        graphAlgorithms();
        treeAlgorithms();
        advancedGraphProblems();
    }
} 