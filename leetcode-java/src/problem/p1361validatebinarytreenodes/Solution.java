package problem.p1361validatebinarytreenodes;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1361. Validate Binary Tree Nodes
 *
 * https://leetcode.cn/problems/validate-binary-tree-nodes/
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children
 * leftChild[i] and rightChild[i], return true if and only if all the given nodes form
 * exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 */

public class Solution {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] edges = new int[n];
        for (var c : leftChild) if (c != -1) edges[c]++;
        for (var c : rightChild) if (c != -1) edges[c]++;

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (edges[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) return false;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[root] = true; queue.add(root);
        while (!queue.isEmpty()) {
            int top = queue.remove();
            if (leftChild[top] != -1) {
                if (visited[leftChild[top]]) return false;
                visited[leftChild[top]] = true;
                queue.add(leftChild[top]);
            }
            if (rightChild[top] != -1) {
                if (visited[rightChild[top]]) return false;
                visited[rightChild[top]] = true;
                queue.add(rightChild[top]);
            }
        }

        for (var b : visited) if (!b) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().validateBinaryTreeNodes(4, new int[]{1,-1,3,-1}, new int[]{2,-1,-1,-1});
        assert !new Solution().validateBinaryTreeNodes(4, new int[]{1,-1,3,-1}, new int[]{2,3,-1,-1});
        assert !new Solution().validateBinaryTreeNodes(2, new int[]{1,0}, new int[]{-1,-1});
        assert !new Solution().validateBinaryTreeNodes(6, new int[]{1,-1,-1,4,-1,-1}, new int[]{2,-1,-1,5,-1,-1});
    }

}
