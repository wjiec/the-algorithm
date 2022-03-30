package problem.p449serializeanddeserializebst;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 449. Serialize and Deserialize BST
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-bst/
 *
 * Serialization is converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 */

public class Solution {

    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            if (root.left != null) sb.append(" ").append(serialize(root.left));
            if (root.right != null) sb.append(" ").append(serialize(root.right));

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) return null;
            Queue<Integer> nums = new ArrayDeque<>();
            for (var s : data.split(" ")) {
                nums.add(Integer.valueOf(s, 10));
            }
            return deserialize(nums, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode deserialize(Queue<Integer> nums, int l, int r) {
            if (nums.isEmpty()) return null;
            if (nums.peek() < l || nums.peek() > r) return null;

            TreeNode node = new TreeNode(nums.remove());
            node.left = deserialize(nums, l, node.val);
            node.right = deserialize(nums, node.val, r);

            return node;
        }
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.build(5,3,6,2,4,null,8,1,null,null,null,7,9);
        Codec codec = new Codec();
        String s = codec.serialize(tree);
        System.out.println(s);
        assert Checker.check(tree, codec.deserialize(s));
    }

}
