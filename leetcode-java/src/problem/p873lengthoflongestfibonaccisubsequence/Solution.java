package problem.p873lengthoflongestfibonaccisubsequence;

import java.util.HashSet;
import java.util.Set;

/**
 * 873. Length of Longest Fibonacci Subsequence
 *
 * https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/
 *
 * A sequence x1, x2, ..., xn is Fibonacci-like if:
 *
 * n >= 3
 * xi + xi+1 == xi+2 for all i + 2 <= n
 * Given a strictly increasing array arr of positive integers forming a sequence,
 * return the length of the longest Fibonacci-like subsequence of arr.
 * If one does not exist, return 0.
 *
 * A subsequence is derived from another sequence arr by deleting any number of elements
 * (including none) from arr, without changing the order of the remaining elements.
 * For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].
 */

public class Solution {

    public int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int v : arr) set.add(v);

        int ans = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int curr = arr[j], next = arr[i] + arr[j];
                for (int len = 2; set.contains(next); ) {
                    int temp = curr + next;
                    curr = next;
                    next = temp;
                    ans = Math.max(ans, ++len);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}) == 5;
        assert new Solution().lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}) == 3;
    }

}
