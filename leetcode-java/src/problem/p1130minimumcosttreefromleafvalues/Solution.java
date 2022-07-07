package problem.p1130minimumcosttreefromleafvalues;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1130. Minimum Cost Tree From Leaf Values
 *
 * https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/
 *
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * The value of each non-leaf node is equal to the product of the largest leaf value
 * in its left and right subtree, respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the
 * values of each non-leaf node. It is guaranteed this sum fits into a 32-bit integer.
 *
 * A node is a leaf if and only if it has zero children.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    @TODO(url = "https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/solution/wei-shi-yao-dan-diao-di-jian-zhan-de-suan-fa-ke-xi/")
    public int mctFromLeafValues(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.MAX_VALUE);

        int ans = 0;
        for (int val : arr) {
            while (val >= stack.peek()) {
                ans += stack.pop() * Math.min(stack.peek(), val);
            }
            stack.push(val);
        }
        while (stack.size() > 2) {
            ans += stack.pop() * stack.peek();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().mctFromLeafValues(new int[]{7,12,8,10}) == 284;

        assert new Solution().mctFromLeafValues(new int[]{6,2,4}) == 32;
        assert new Solution().mctFromLeafValues(new int[]{4,11}) == 44;
    }

}
