package offer.p32congshangdaoxiadayinerchashuiilcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 *
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 *
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 */

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return List.of();

        Queue<TreeNode> curr = new ArrayDeque<>(), next = new ArrayDeque<>();
        curr.add(root);

        List<List<Integer>> ans = new ArrayList<>();
        do {
            List<Integer> level = new ArrayList<>();
            while (!curr.isEmpty()) {
                var node = curr.remove();
                level.add(node.val);

                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }

            ans.add(level);
            curr = next;
            next = new ArrayDeque<>();
        } while (!curr.isEmpty());

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().levelOrder(TreeNode.build(3,9,20,null,null,15,7)));
    }

}
