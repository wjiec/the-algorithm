package problem.p988smalleststringstartingfromleaf;

import common.TreeNode;

/**
 * 988. Smallest String Starting From Leaf
 *
 * https://leetcode.cn/problems/smallest-string-starting-from-leaf/
 *
 * You are given the root of a binary tree where each node has a value in the range [0, 25]
 * representing the letters 'a' to 'z'.
 *
 * Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * As a reminder, any shorter prefix of a string is lexicographically smaller.
 *
 * For example, "ab" is lexicographically smaller than "aba".
 * A leaf of a node is a node that has no children.
 */

public class Solution {

    private String ans = "";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode curr, StringBuilder sb) {
        sb.append((char) (curr.val + 'a'));
        if (curr.left == null && curr.right == null) {
            sb.reverse();
            String s = sb.toString();
            sb.reverse();

            if (ans.length() == 0 || s.compareTo(ans) < 0) {
                ans = s;
            }
        }

        if (curr.left != null) dfs(curr.left, sb);
        if (curr.right != null) dfs(curr.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        assert new Solution().smallestFromLeaf(TreeNode.build(0,1,2,3,4,3,4)).equals("dba");
        assert new Solution().smallestFromLeaf(TreeNode.build(25,1,3,1,3,0,2)).equals("adz");
        assert new Solution().smallestFromLeaf(TreeNode.build(2,2,1,null,1,0,null,0)).equals("abc");
    }

}
