package daily.p210427.rangesumofbst;

/**
 * 938. Range Sum of BST
 *
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 *
 * Given the root node of a binary search tree, return the
 * sum of values of all nodes with a value in the range [low, high].
 */

public class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        int rs = root.val >= low && root.val <= high ? root.val : 0;
        if (root.val > low && root.left != null) {
            rs += rangeSumBST(root.left, low, high);
        }
        if (root.val < high && root.right != null) {
            rs += rangeSumBST(root.right, low, high);
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(buildTree0());
        System.out.println(new Solution().rangeSumBST(buildTree0(), 7, 15));
        System.out.println(new Solution().rangeSumBST(null, 7, 15));
    }

    private static TreeNode buildTree0() {
        TreeNode root = new TreeNode(10);
        root.insert(5);
        root.insert(3);
        root.insert(7);
        root.insert(15);
        root.insert(18);

        return root;
    }

}
