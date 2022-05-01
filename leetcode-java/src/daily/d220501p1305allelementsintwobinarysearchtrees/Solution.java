package daily.d220501p1305allelementsintwobinarysearchtrees;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 1305. All Elements in Two Binary Search Trees
 *
 * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 *
 * Given two binary search trees root1 and root2, return a list containing all the
 * integers from both trees sorted in ascending order.
 */

public class Solution {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Queue<Integer> q1 = new ArrayDeque<>(), q2 = new ArrayDeque<>();
        inorder(root1, q1); inorder(root2, q2);

        List<Integer> ans = new ArrayList<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek() < q2.peek()) ans.add(q1.remove());
            else ans.add(q2.remove());
        }

        ans.addAll(q1);
        ans.addAll(q2);

        return ans;
    }

    private void inorder(TreeNode node, Queue<Integer> queue) {
        if (node == null) return;

        inorder(node.left, queue);
        queue.add(node.val);
        inorder(node.right, queue);
    }

    public static void main(String[] args) {
        assert new Solution().getAllElements(TreeNode.build(2,1,4), TreeNode.build(1,0,3)).equals(List.of(0,1,1,2,3,4));
        assert new Solution().getAllElements(TreeNode.build(1,null,8), TreeNode.build(8,1)).equals(List.of(1,1,8,8));
    }

}
