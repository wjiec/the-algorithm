package offer.p32congshangdaoxiadayinerchashuiiilcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 *
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 *
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 */

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return List.of();

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0, l = queue.size(); i < l; i++) {
                var node = queue.remove();
                numbers.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (ans.size() % 2 == 1) Collections.reverse(numbers);
            ans.add(numbers);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().levelOrder(TreeNode.build(3,9,20,null,null,15,7)));
    }

}
