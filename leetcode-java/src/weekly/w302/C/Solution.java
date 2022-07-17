package weekly.w302.C;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 6121. Query Kth Smallest Trimmed Number
 *
 * https://leetcode.cn/contest/weekly-contest-302/problems/query-kth-smallest-trimmed-number/
 *
 * You are given a 0-indexed array of strings nums, where each string is of equal
 * length and consists of only digits.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi].
 * For each queries[i], you need to:
 *
 * Trim each number in nums to its rightmost trimi digits.
 * Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are
 * equal, the number with the lower index is considered to be smaller.
 * Reset each number in nums to its original length.
 * Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.
 *
 * Note:
 *
 * To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
 * Strings in nums may contain leading zeros.
 */

public class Solution {

    private record Number(BigInteger n, int index) {}

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums[0].length();
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int k = queries[i][0], trim = queries[i][1];
            List<Number> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                list.add(new Number(new BigInteger(nums[j].substring(n - trim)), j));
            }
            list.sort((a, b) -> {
                int c = a.n.compareTo(b.n);
                return c == 0 ? a.index - b.index : c;
            });
            ans[i] = list.get(k - 1).index;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().smallestTrimmedNumbers(new String[]{
            "102", "473", "251", "814"
        }, new int[][]{
            {1, 1}, {2, 3}, {4, 2}, {1, 2}
        })));
    }

}
