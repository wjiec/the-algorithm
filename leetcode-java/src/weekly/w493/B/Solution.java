package weekly.w493.B;

/**
 * Q2. Count Commas in Range II
 *
 * https://leetcode.cn/contest/weekly-contest-493/problems/count-commas-in-range-ii/
 *
 * You are given an integer n.
 *
 * Return the total number of commas used when writing all integers
 * from [1, n] (inclusive) in standard number formatting.
 *
 * In standard formatting:
 *
 * A comma is inserted after every three digits from the right.
 * Numbers with fewer than 4 digits contain no commas.
 */

public class Solution {

    public long countCommas(long n) {
        long ans = 0;
        for (long v = 1000, c = 3; v <= n; v *= 10, c++) {
            // 从 [v, v * 10] 一共 v * 10 - v 个数
            long count = Math.min(v * 10 - v, n - v + 1);
            ans += count * (c / 3);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countCommas(100000) == 99001;
        assert new Solution().countCommas(1002) == 3;
    }

}
