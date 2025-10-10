package masterDSA.topics.trees.TreesTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Trees topic problems
 */
public class TreesTests {
    
    @Test
    public void testBinaryTreeLevelOrderTraversal() {
        BinaryTreeLevelOrderTraversal.TreeNode root = new BinaryTreeLevelOrderTraversal.TreeNode(3);
        root.left = new BinaryTreeLevelOrderTraversal.TreeNode(9);
        root.right = new BinaryTreeLevelOrderTraversal.TreeNode(20);
        root.right.left = new BinaryTreeLevelOrderTraversal.TreeNode(15);
        root.right.right = new BinaryTreeLevelOrderTraversal.TreeNode(7);
        
        List<List<Integer>> result = BinaryTreeLevelOrderTraversal.levelOrder_bfs(root);
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(2, result.get(1).size());
        assertEquals(2, result.get(2).size());
    }
    
    @Test
    public void testValidateBinarySearchTree() {
        ValidateBinarySearchTree.TreeNode root = new ValidateBinarySearchTree.TreeNode(2);
        root.left = new ValidateBinarySearchTree.TreeNode(1);
        root.right = new ValidateBinarySearchTree.TreeNode(3);
        
        boolean result = ValidateBinarySearchTree.isValidBST_range(root);
        assertTrue(result);
    }
    
    @Test
    public void testInvertBinaryTree() {
        InvertBinaryTree.TreeNode root = new InvertBinaryTree.TreeNode(4);
        root.left = new InvertBinaryTree.TreeNode(2);
        root.right = new InvertBinaryTree.TreeNode(7);
        root.left.left = new InvertBinaryTree.TreeNode(1);
        root.left.right = new InvertBinaryTree.TreeNode(3);
        root.right.left = new InvertBinaryTree.TreeNode(6);
        root.right.right = new InvertBinaryTree.TreeNode(9);
        
        InvertBinaryTree.TreeNode result = InvertBinaryTree.invertTree_recursive(root);
        assertEquals(4, result.val);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
    }
    
    @Test
    public void testLowestCommonAncestorBinaryTree() {
        LowestCommonAncestorBinaryTree.TreeNode root = new LowestCommonAncestorBinaryTree.TreeNode(3);
        root.left = new LowestCommonAncestorBinaryTree.TreeNode(5);
        root.right = new LowestCommonAncestorBinaryTree.TreeNode(1);
        root.left.left = new LowestCommonAncestorBinaryTree.TreeNode(6);
        root.left.right = new LowestCommonAncestorBinaryTree.TreeNode(2);
        root.right.left = new LowestCommonAncestorBinaryTree.TreeNode(0);
        root.right.right = new LowestCommonAncestorBinaryTree.TreeNode(8);
        
        LowestCommonAncestorBinaryTree.TreeNode p = root.left;
        LowestCommonAncestorBinaryTree.TreeNode q = root.right;
        
        LowestCommonAncestorBinaryTree.TreeNode result = LowestCommonAncestorBinaryTree.lowestCommonAncestor_dfs(root, p, q);
        assertEquals(3, result.val);
    }
}