package masterDSA.topics.patterns_misc.TreeTraversalPatterns;
import java.util.*;


/**
 * Tree Traversal Patterns
 * 
 * Problem Statement:
 * Common patterns for tree traversal including DFS, BFS, and various
 * specialized traversal techniques for different tree problems.
 * 
 * LeetCode Link: https://leetcode.com/tag/tree/
 * Difficulty: Easy to Hard
 * Tags: Tree, DFS, BFS, Recursion
 * 
 * Approach Summary:
 * 1. DFS Traversals: Preorder, Inorder, Postorder
 * 2. BFS Traversal: Level order traversal
 * 3. Path Finding: Root to leaf paths
 * 4. Tree Construction: From traversal sequences
 * 5. Tree Validation: BST validation, balance check
 * 
 * Pseudocode:
 * 1. Handle base case (null node)
 * 2. Process current node (preorder)
 * 3. Recursively traverse left subtree
 * 4. Process current node (inorder)
 * 5. Recursively traverse right subtree
 * 6. Process current node (postorder)
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - height of tree for recursion stack
 */
public class TreeTraversalPatterns {
    
    // TreeNode definition
    public static class TreeNode {
        int val;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * Preorder traversal (Root -> Left -> Right)
     * 
     * @param root Root of binary tree
     * @return List of values in preorder
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    
    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        result.add(node.val); // Process root
        preorderHelper(node.left, result); // Traverse left
        preorderHelper(node.right, result); // Traverse right
    }
    
    /**
     * Inorder traversal (Left -> Root -> Right)
     * 
     * @param root Root of binary tree
     * @return List of values in inorder
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    
    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        inorderHelper(node.left, result); // Traverse left
        result.add(node.val); // Process root
        inorderHelper(node.right, result); // Traverse right
    }
    
    /**
     * Postorder traversal (Left -> Right -> Root)
     * 
     * @param root Root of binary tree
     * @return List of values in postorder
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private static void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        postorderHelper(node.left, result); // Traverse left
        postorderHelper(node.right, result); // Traverse right
        result.add(node.val); // Process root
    }
    
    /**
     * Level order traversal (BFS)
     * 
     * @param root Root of binary tree
     * @return List of lists, each containing values at that level
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    /**
     * Zigzag level order traversal
     * 
     * @param root Root of binary tree
     * @return List of lists with zigzag pattern
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
    
    /**
     * Find all root-to-leaf paths
     * 
     * @param root Root of binary tree
     * @return List of all root-to-leaf paths
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        dfsPaths(root, "", result);
        return result;
    }
    
    private static void dfsPaths(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }
        
        // Add current node to path
        if (path.isEmpty()) {
            path = String.valueOf(node.val);
        } else {
            path += "->" + node.val;
        }
        
        // If leaf node, add path to result
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }
        
        // Continue DFS
        dfsPaths(node.left, path, result);
        dfsPaths(node.right, path, result);
    }
    
    /**
     * Maximum depth of binary tree
     * 
     * @param root Root of binary tree
     * @return Maximum depth
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * Check if binary tree is balanced
     * 
     * @param root Root of binary tree
     * @return True if balanced, false otherwise
     */
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private static int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Validate binary search tree
     * 
     * @param root Root of binary tree
     * @return True if valid BST, false otherwise
     */
    public static boolean isValidBST(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        return validateBST(node.left, min, node.val) && 
               validateBST(node.right, node.val, max);
    }
    
    /**
     * Construct binary tree from preorder and inorder traversal
     * 
     * @param preorder Preorder traversal sequence
     * @param inorder Inorder traversal sequence
     * @return Root of constructed tree
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, preorder.length - 1, 
                              inorder, 0, inorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                          int[] inorder, int inStart, int inEnd,
                                          Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        int rootIndex = inorderMap.get(rootVal);
        int leftSubtreeSize = rootIndex - inStart;
        
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSubtreeSize,
                                  inorder, inStart, rootIndex - 1, inorderMap);
        root.right = buildTreeHelper(preorder, preStart + leftSubtreeSize + 1, preEnd,
                                   inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
    
    /**
     * Lowest common ancestor of two nodes
     * 
     * @param root Root of binary tree
     * @param p First node
     * @param q Second node
     * @return Lowest common ancestor
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        
        return left != null ? left : right;
    }
    
    /**
     * Serialize binary tree to string
     * 
     * @param root Root of binary tree
     * @return Serialized string representation
     */
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private static void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    
    /**
     * Deserialize string to binary tree
     * 
     * @param data Serialized string
     * @return Root of deserialized tree
     */
    public static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        
        String val = queue.poll();
        if ("null".equals(val)) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        
        return node;
    }
    
    /**
     * Helper method to create binary tree from array
     */
    private static TreeNode createTree(Integer[] nums) {
        if (nums == null || nums.length == 0 || nums[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        for (int i = 1; i < nums.length; i += 2) {
            TreeNode current = queue.poll();
            
            if (i < nums.length && nums[i] != null) {
                current.left = new TreeNode(nums[i]);
                queue.offer(current.left);
            }
            
            if (i + 1 < nums.length && nums[i + 1] != null) {
                current.right = new TreeNode(nums[i + 1]);
                queue.offer(current.right);
            }
        }
        
        return root;
    }
    
    /**
     * Demonstration method showing various tree traversal patterns
     */
    public static void runExample() {
        System.out.println("=== Tree Traversal Patterns Examples ===");
        
        // Create test tree
        TreeNode root = createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        
        // Preorder traversal
        List<Integer> preorder = preorderTraversal(root);
        System.out.println("Preorder traversal: " + preorder);
        
        // Inorder traversal
        List<Integer> inorder = inorderTraversal(root);
        System.out.println("Inorder traversal: " + inorder);
        
        // Postorder traversal
        List<Integer> postorder = postorderTraversal(root);
        System.out.println("Postorder traversal: " + postorder);
        
        // Level order traversal
        List<List<Integer>> levelOrder = levelOrder(root);
        System.out.println("Level order traversal: " + levelOrder);
        
        // Zigzag level order
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        System.out.println("Zigzag level order: " + zigzag);
        
        // Binary tree paths
        List<String> paths = binaryTreePaths(root);
        System.out.println("Root-to-leaf paths: " + paths);
        
        // Maximum depth
        int maxDepth = maxDepth(root);
        System.out.println("Maximum depth: " + maxDepth);
        
        // Check if balanced
        boolean isBalanced = isBalanced(root);
        System.out.println("Is balanced: " + isBalanced);
        
        // Validate BST
        TreeNode bstRoot = createTree(new Integer[]{5, 3, 7, 2, 4, 6, 8});
        boolean isValidBST = isValidBST(bstRoot);
        System.out.println("Is valid BST: " + isValidBST);
        
        // Build tree from traversals
        int[] preorderArr = {3, 9, 20, 15, 7};
        int[] inorderArr = {9, 3, 15, 20, 7};
        TreeNode builtTree = buildTree(preorderArr, inorderArr);
        System.out.println("Built tree from traversals: " + levelOrder(builtTree));
        
        // Serialize and deserialize
        String serialized = serialize(root);
        System.out.println("Serialized tree: " + serialized);
        TreeNode deserialized = deserialize(serialized);
        System.out.println("Deserialized tree level order: " + levelOrder(deserialized));
    }
    
    public static void main(String[] args) {
        runExample();
    }
}
