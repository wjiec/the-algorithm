package daily.d210903smallestklcci;

import common.Checker;

import java.util.PriorityQueue;

/**
 * 面试题 17.14. Smallest K LCCI
 *
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 *
 * Design an algorithm to find the smallest K numbers in an array.
 */

public class Solution {

    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (var n : arr) {
            queue.add(n);
            if (queue.size() > k) queue.remove();
        }

        int[] ans = new int[k];
        for (k -= 1; k >= 0; k--) {
            ans[k] = queue.remove();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().smallestK(new int[]{1,3,5,7,2,4,6,8}, 4), new int[]{1,2,3,4});
    }

}
