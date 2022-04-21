package problem.p255verifypreordersequenceinbinarysearchtree;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 255. Verify Preorder Sequence in Binary Search Tree
 *
 * https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/
 *
 * Given an array of unique integers preorder,
 * return true if it is the correct preorder traversal sequence of a binary search tree.
 */

public class Solution {

    @TODO
    public boolean verifyPreorder1(int[] preorder) {
        // 单调递减栈
        // 当访问左子树时，单调递减，将其压入栈
        // 当开始访问右子树时，栈顶的元素比接下来的小，将小于新进右子树节点都弹出栈，维护最后一个弹出栈的值maxPre
        // prev是弹出栈的节点最大值，但是接下来遍历节点的最小值
        // 异常时：若下一个遍历的节点值小于maxPre，则表示不是前序遍历
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, prev = Integer.MIN_VALUE; i < preorder.length; i++) {
            if (preorder[i] < prev) return false;
            while (!stack.isEmpty() && stack.peek() < preorder[i]) {
                prev = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }


    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length);
    }

    public boolean verify(int[] seq, int l, int r) {
        if (l == r) return true;

        int mid = l + 1;
        while (mid < r && seq[mid] < seq[l]) mid++;

        for (int i = mid; i < r; i++) {
            if (seq[i] <= seq[l]) return false;
        }

        return verify(seq, l + 1, mid) && verify(seq, mid, r);
    }

    public static void main(String[] args) {
        assert new Solution().verifyPreorder(new int[]{5,2,1,3,6});
        assert !new Solution().verifyPreorder(new int[]{5,2,6,1,3});

        assert new Solution().verifyPreorder1(new int[]{5,2,1,3,6});
        assert !new Solution().verifyPreorder1(new int[]{5,2,6,1,3});
    }

}
