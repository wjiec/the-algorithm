package daily.d211105p1218longestarithmeticsubsequenceofgivendifference;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 *
 * https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/
 *
 * Given an integer array arr and an integer difference, return the length of the longest subsequence
 * in arr which is an arithmetic sequence such that the difference between adjacent
 * elements in the subsequence equals difference.
 *
 * A subsequence is a sequence that can be derived from arr by deleting some or
 * no elements without changing the order of the remaining elements.
 */

public class Solution {

    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) {
            map.put(n, map.getOrDefault(n - difference, 0) + 1);
            ans = Math.max(ans, map.get(n));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubsequence(new int[]{1,2,3,4}, 1) == 4;
        assert new Solution().longestSubsequence(new int[]{1,3,5,7}, 1) == 1;
        assert new Solution().longestSubsequence(new int[]{1,5,7,8,5,3,4,2,1}, -2) == 4;
    }

}
