package problem.p297serializeanddeserializebinarytree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * 297. Serialize and Deserialize Binary Tree
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on
 * how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string
 * and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 */

public class Solution {

    public static class Codec {
        private static class Holder {
            private final TreeNode node;
            public Holder(TreeNode node) { this.node = node; }
        }
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringJoiner sj = new StringJoiner(",");

            Queue<Holder> queue = new ArrayDeque<>();
            queue.add(new Holder(root));

            while (!queue.isEmpty()) {
                var holder = queue.remove();
                sj.add(holder.node == null ? "null" : String.valueOf(holder.node.val));
                if (holder.node != null) {
                    queue.add(new Holder(holder.node.left));
                    queue.add(new Holder(holder.node.right));
                }
            }

            return sj.toString();
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) return null;

            String[] segments = data.split(",");
            Integer[] values = new Integer[segments.length];
            for (int i = 0; i < segments.length; i++) {
                if (!segments[i].equals("null")) values[i] = Integer.valueOf(segments[i], 10);
            }

            TreeNode root = new TreeNode(values[0]);
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);

            for (int idx = 1; !queue.isEmpty(); ) {
                var node = queue.remove();
                node.left = values[idx] == null ? null : new TreeNode(values[idx]);
                idx++;
                node.right = values[idx] == null ? null : new TreeNode(values[idx]);
                idx++;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            return root;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        var serialized = codec.serialize(TreeNode.build(1,2,3,null,null,4,5));
        System.out.println(serialized);
        System.out.println(codec.deserialize(serialized));

        serialized = codec.serialize(TreeNode.build(1,2,3,4,5,6,7));
        System.out.println(serialized);
        System.out.println(codec.deserialize(serialized));
    }

}
