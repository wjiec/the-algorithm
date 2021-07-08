package offer.p33erchasousuoshudehouxubianlixulielcof;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 */

public class Solution {

    // [left, right, root]
    //  - left < root
    //  - right > root
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length);
    }

    private boolean verify(int[] seq, int start, int end) {
        if (end - start <= 1) return true;

        int rootValue = seq[end - 1], sepIndex = -1;
        for (int i = start; i < end; i++) {
            if (sepIndex != -1 && seq[i] < rootValue) return false;
            else if (seq[i] > rootValue && sepIndex == -1) sepIndex = i;
        }

        if (sepIndex == -1) return verify(seq, start, end - 1);
        return verify(seq, start, sepIndex) && verify(seq, sepIndex, end - 1);
    }

    public static void main(String[] args) {
        assert !new Solution().verifyPostorder(new int[]{5, 2, -17, -11, 25, 76, 62, 98, 92, 61});
        assert !new Solution().verifyPostorder(new int[]{3,10,6,9,2});
        assert new Solution().verifyPostorder(new int[]{4,8,6,12,16,14,10});

        assert !new Solution().verifyPostorder(new int[]{1,6,3,2,5});
        assert new Solution().verifyPostorder(new int[]{1,3,2,6,5});
    }

}
