package problem.p1145binarytreecoloringgame;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1145. Binary Tree Coloring Game
 *
 * https://leetcode.cn/problems/binary-tree-coloring-game/
 *
 * Two players play a turn based game on a binary tree. We are given the root of this binary tree, and
 * the number of nodes n in the tree. n is odd, and each node has a distinct value from 1 to n.
 *
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y
 * with 1 <= y <= n and y != x. The first player colors the node with value x red, and the second
 * player colors the node with value y blue.
 *
 * Then, the players take turns starting with the first player. In each turn, that player chooses a node
 * of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen
 * node (either the left child, right child, or parent of the chosen node.)
 *
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.
 * If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 *
 * You are the second player. If it is possible to choose such a y to ensure you win the game, return true.
 * If it is not possible, return false.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode curr = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            for (; curr != null; curr = curr.left) {
                stack.push(curr);
            }

            curr = stack.pop();
            if (curr.val == x) break;
            curr = curr.right;
        }

        int l = count(curr.left);
        int r = count(curr.right);
        int p = n - l - r - 1; // skip x node

        int[] sorted = new int[]{l, r, p};
        Arrays.sort(sorted);
        return sorted[2] > sorted[0] + sorted[1] + 1;
    }

    private int count(TreeNode node) {
        int ans = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            for (; node != null; node = node.left) {
                stack.push(node);
            }
            ans++; node = stack.pop().right;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert !new Solution().btreeGameWinningMove(TreeNode.build(1,2,3,4,5), 5, 2);
        assert new Solution().btreeGameWinningMove(TreeNode.build(6,3,null,7,4,null,null,null,2,null,1,null,5), 7, 3);
        assert new Solution().btreeGameWinningMove(TreeNode.build(1,2,3,4,5), 5, 1);

        assert new Solution().btreeGameWinningMove(TreeNode.build(1,2,3,4,5,6,7,8,9,10,11), 11, 3);
        assert !new Solution().btreeGameWinningMove(TreeNode.build(1,2,3), 3, 1);
    }

}
