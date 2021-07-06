package offer.p32congshangdaoxiadayinerchashulcof;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 *
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 */

public class Solution {

    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            var node = queue.remove();
            list.add(node.val);

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        int[] ans = new int[list.size()];
        for (int i = 0, l = list.size(); i < l; i++) ans[i] = list.get(i);
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().levelOrder(TreeNode.build(3,9,20,null,null,15,7)), new int[]{3,9,20,15,7});
    }

}
