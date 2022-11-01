package lcp.p64;

import common.TODO;
import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LCP 64. 二叉树灯饰
 *
 * https://leetcode.cn/problems/U7WvvU/
 *
 * 「力扣嘉年华」的中心广场放置了一个巨型的二叉树形状的装饰树。每个节点上均有一盏灯和三个开关。
 * 节点值为 0 表示灯处于「关闭」状态，节点值为 1 表示灯处于「开启」状态。每个节点上的三个开关各自功能如下：
 *
 * 开关 1：切换当前节点的灯的状态；
 * 开关 2：切换 以当前节点为根 的子树中，所有节点上的灯的状态，；
 * 开关 3：切换 当前节点及其左右子节点（若存在的话） 上的灯的状态；
 *
 * 给定该装饰的初始状态 root，请返回最少需要操作多少次开关，可以关闭所有节点的灯。
 */

public class Solution {

    @TODO
    public int closeLampInTree(TreeNode root) {
        return dfs(root, false, false);
    }

    private final Map<TreeNode, Map<Integer, Integer>> memos = new HashMap<>();

    private int dfs(TreeNode node, boolean sw2, boolean sw3) {
        if (node == null) return 0;

        int key = (sw2 ? 2 : 0) | (sw3 ? 1 : 0), ans;
        Map<Integer, Integer> memo = memos.computeIfAbsent(node, v -> new HashMap<>());
        if (memo.containsKey(key)) return memo.get(key);

        boolean lampOpen = node.val == 1, swOpen = sw2 == sw3;
        if (lampOpen == swOpen) { // 当前节点是打开状态
            int op1 = dfs(node.left, sw2, false) + dfs(node.right, sw2, false) + 1;
            int op2 = dfs(node.left, !sw2, false) + dfs(node.right, !sw2, false) + 1;
            int op3 = dfs(node.left, sw2, true) + dfs(node.right, sw2, true) + 1;
            int op123 = dfs(node.left, !sw2, true) + dfs(node.right, !sw2, true) + 3;

            ans = Math.min(op1, Math.min(op2, Math.min(op3, op123)));
        } else { // 当前节点是关闭状态
            int op0 = dfs(node.left, sw2, false) + dfs(node.right, sw2, false);
            int op12 = dfs(node.left, !sw2, false) + dfs(node.right, !sw2, false) + 2;
            int op13 = dfs(node.left, sw2, true) + dfs(node.right, sw2, true) + 2;
            int op23 = dfs(node.left, !sw2, true) + dfs(node.right, !sw2, true) + 2;

            ans = Math.min(op0, Math.min(op12, Math.min(op13, op23)));
        }

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().closeLampInTree(TreeNode.build(1,null,1,0,0,1,null,null,null,0)) == 2;

        assert new Solution().closeLampInTree(TreeNode.build(1,1,0,null,null,null,1)) == 2;
        assert new Solution().closeLampInTree(TreeNode.build(1,1,1,1,null,null,1)) == 1;
        assert new Solution().closeLampInTree(TreeNode.build(0,null,0)) == 0;
    }

}
