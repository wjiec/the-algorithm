package offer.p37xuliehuaerchashulcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * 剑指 Offer 37. 序列化二叉树
 *
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
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
