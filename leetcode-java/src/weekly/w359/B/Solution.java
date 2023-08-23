package weekly.w359.B;

/**
 * 2829. Determine the Minimum Sum of a k-avoiding Array
 *
 * https://leetcode.cn/contest/weekly-contest-359/problems/determine-the-minimum-sum-of-a-k-avoiding-array/
 *
 * You are given two integers, n and k.
 *
 * An array of distinct positive integers is called a k-avoiding array if there does not exist
 * any pair of distinct elements that sum to k.
 *
 * Return the minimum possible sum of a k-avoiding array of length n.
 */

public class Solution {

    public int minimumSum(int n, int k) {
        int ans = 0;
        for (int i = 1; i <= k / 2 && n > 0; i++, n--) ans += i;
        for (int i = k; n > 0; i++, n--) ans += i;
        return ans;
    }

    public static void main(String[] args) {
    }

}
