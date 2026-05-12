package weekly.w493.A;

/**
 * Q1. Count Commas in Range
 *
 * https://leetcode.cn/contest/weekly-contest-493/problems/count-commas-in-range/
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

    public int countCommas(int n) {
        int ans = 0;
        for (int i = 1000; i <= n; i++) {
            ans += (String.valueOf(i).length() - 1) / 3;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countCommas(100000) == 99001;
        assert new Solution().countCommas(1002) == 3;
    }

}
