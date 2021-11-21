package daily.d211121p559maximumdepthofnarytree;

import common.NTreeNode;

/**
 * 559. Maximum Depth of N-ary Tree
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 *
 * Given a n-ary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 */

public class Solution {

    private int max = 0;

    private static class Node extends NTreeNode {
        public Node(int val) { super(val); }
    }

    public int maxDepth(Node root) {
        if (root == null) return 0;

        dfs(root, 1);
        return max;
    }

    private void dfs(Node root, int depth) {
        max = Math.max(max, depth);
        if (root.children != null) {
            for (var node : root.children) {
                dfs((Node) node, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxDepth(Node.build(Node.class, 1,null,3,2,4,null,5,6)) == 3;
        assert new Solution().maxDepth(Node.build(Node.class,
            1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14)) == 5;
    }

}
