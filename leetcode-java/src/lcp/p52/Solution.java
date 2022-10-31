package lcp.p52;

import common.TODO;
import common.TreeNode;

/**
 * LCP 52. 二叉搜索树染色
 *
 * https://leetcode.cn/problems/QO5KpG/
 *
 * 欢迎各位勇者来到力扣城，本次试炼主题为「二叉搜索树染色」。
 *
 * 每位勇士面前设有一个二叉搜索树的模型，模型的根节点为 root，树上的各个节点值均不重复。初始时，所有节点均为蓝色。现在按顺序对这棵二叉树进行若干次操作， ops[i] = [type, x, y] 表示第 i 次操作为：
 *
 * type 等于 0 时，将节点值范围在 [x, y] 的节点均染蓝
 * type 等于 1 时，将节点值范围在 [x, y] 的节点均染红
 * 请返回完成所有染色后，该二叉树中红色节点的数量。
 *
 * 注意：
 *
 * 题目保证对于每个操作的 x、y 值定出现在二叉搜索树节点中
 */

public class Solution {

    private int ans = 0;

    @TODO
    public int getNumber(TreeNode root, int[][] ops) {
        dfs(root, ops);
        return ans;
    }

    private void dfs(TreeNode node, int[][] ops) {
        if (node == null) return;

        dfs(node.left, ops);

        for (int i = ops.length - 1; i >= 0; i--) {
            if (ops[i][1] <= node.val && node.val <= ops[i][2]) {
                if (ops[i][0] == 1) ans++;
                break;
            }
        }

        dfs(node.right, ops);
    }

    public static void main(String[] args) {
        assert new Solution().getNumber(TreeNode.build(1,null,2,null,3,null,4,null,5),
            new int[][]{{1,2,4}, {1,1,3}, {0,3,5}}) == 2;
        assert new Solution().getNumber(TreeNode.build(4,2,7,1,null,5,null,null,null,null,6),
            new int[][]{{0,2,2},{1,1,5},{0,4,5},{1,5,7}}) == 5;
    }

}
