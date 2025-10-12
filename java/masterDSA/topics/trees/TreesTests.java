package masterDSA.topics.trees;
import java.util.*;

import masterDSA.topics.trees.BinaryTreeInorderPreorderPostorderTraversal.BinaryTreeInorderPreorderPostorderTraversal;
import masterDSA.topics.trees.BinaryTreeLevelOrderTraversal.BinaryTreeLevelOrderTraversal;
import masterDSA.topics.trees.BinaryTreeMaximumPathSum.BinaryTreeMaximumPathSum;
import masterDSA.topics.trees.LowestCommonAncestorBinaryTree.LowestCommonAncestorBinaryTree;
import masterDSA.topics.trees.LowestCommonAncestorBST.LowestCommonAncestorBST;
import masterDSA.topics.trees.ValidateBinarySearchTree.ValidateBinarySearchTree;
import masterDSA.topics.trees.SerializeAndDeserializeBinaryTree.SerializeAndDeserializeBinaryTree;
import masterDSA.topics.trees.InvertBinaryTree.InvertBinaryTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for tree problems
 * Tests representative methods from each tree problem
 */
public class TreesTests {
    
    @Test
    public void testBinaryTreeTraversals() {
        Integer[] values = {1, null, 2, 3};
        BinaryTreeInorderPreorderPostorderTraversal.TreeNode root = BinaryTreeInorderPreorderPostorderTraversal.createBinaryTree(values);
        List<Integer> inorder = BinaryTreeInorderPreorderPostorderTraversal.inorderTraversal_recursive(root);
        List<Integer> expected = Arrays.asList(1, 3, 2);
        assertEquals(expected, inorder);
    }
    
    @Test
    public void testBinaryTreeLevelOrderTraversal() {
        Integer[] values = {3, 9, 20, null, null, 15, 7};
        BinaryTreeLevelOrderTraversal.TreeNode root = BinaryTreeLevelOrderTraversal.createBinaryTree(values);
        List<List<Integer>> result = BinaryTreeLevelOrderTraversal.levelOrder_bfs(root);
        assertEquals(3, result.size());
        assertEquals(Arrays.asList(3), result.get(0));
        assertEquals(Arrays.asList(9, 20), result.get(1));
        assertEquals(Arrays.asList(15, 7), result.get(2));
    }
    
    @Test
    public void testBinaryTreeMaximumPathSum() {
        Integer[] values = {1, 2, 3};
        BinaryTreeMaximumPathSum.TreeNode root = BinaryTreeMaximumPathSum.createBinaryTree(values);
        int result = BinaryTreeMaximumPathSum.maxPathSum_dfs(root);
        assertEquals(6, result);
    }
    
    @Test
    public void testLowestCommonAncestorBinaryTree() {
        Integer[] values = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        LowestCommonAncestorBinaryTree.TreeNode root = LowestCommonAncestorBinaryTree.createBinaryTree(values);
        LowestCommonAncestorBinaryTree.TreeNode p = root.left; // 5
        LowestCommonAncestorBinaryTree.TreeNode q = root.right; // 1
        LowestCommonAncestorBinaryTree.TreeNode result = LowestCommonAncestorBinaryTree.lowestCommonAncestor_dfs(root, p, q);
        assertEquals(3, result.val);
    }
    
    @Test
    public void testLowestCommonAncestorBST() {
        int[] values = {6, 2, 8, 0, 4, 7, 9, 3, 5};
        LowestCommonAncestorBST.TreeNode root = LowestCommonAncestorBST.createBST(values);
        LowestCommonAncestorBST.TreeNode p = root.left; // 2
        LowestCommonAncestorBST.TreeNode q = root.right; // 8
        LowestCommonAncestorBST.TreeNode result = LowestCommonAncestorBST.lowestCommonAncestor_bst(root, p, q);
        assertEquals(6, result.val);
    }
    
    @Test
    public void testValidateBinarySearchTree() {
        Integer[] values = {2, 1, 3};
        ValidateBinarySearchTree.TreeNode root = ValidateBinarySearchTree.createBinaryTree(values);
        boolean result = ValidateBinarySearchTree.isValidBST_range(root);
        assertTrue(result);
    }
    
    @Test
    public void testSerializeAndDeserializeBinaryTree() {
        Integer[] values = {1, 2, 3, null, null, 4, 5};
        SerializeAndDeserializeBinaryTree.TreeNode root = SerializeAndDeserializeBinaryTree.createBinaryTree(values);
        String serialized = SerializeAndDeserializeBinaryTree.serialize_preorder(root);
        SerializeAndDeserializeBinaryTree.TreeNode deserialized = SerializeAndDeserializeBinaryTree.deserialize_preorder(serialized);
        assertEquals(root.val, deserialized.val);
    }
    
    @Test
    public void testInvertBinaryTree() {
        Integer[] values = {4, 2, 7, 1, 3, 6, 9};
        InvertBinaryTree.TreeNode root = InvertBinaryTree.createBinaryTree(values);
        InvertBinaryTree.TreeNode inverted = InvertBinaryTree.invertTree_dfs(root);
        assertEquals(4, inverted.val);
        assertEquals(7, inverted.left.val);
        assertEquals(2, inverted.right.val);
    }
}
