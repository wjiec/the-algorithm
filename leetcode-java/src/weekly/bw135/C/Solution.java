package weekly.bw135.C;

/**
 * 3224. Minimum Array Changes to Make Differences Equal
 *
 * https://leetcode.cn/contest/biweekly-contest-135/problems/minimum-array-changes-to-make-differences-equal/
 *
 * You are given an integer array nums of size n where n is even, and an integer k.
 *
 * You can perform some changes on the array, where in one change you can replace any element
 * in the array with any integer in the range from 0 to k.
 *
 * You need to perform some changes (possibly none) such that the final array satisfies the following condition:
 *
 * There exists an integer X such that abs(a[i] - a[n - i - 1]) = X for all (0 <= i < n).
 * Return the minimum number of changes required to satisfy the above condition.
 */

public class Solution {

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[k + 2];
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - i - 1];
            if (a > b) { int t = a; a = b; b = t; }

            int d = b - a, x = Math.max(b, k - a);
            diff[0]++; diff[d]--;
            diff[d + 1]++; diff[x + 1]--;
            diff[x + 1] += 2;
        }

        int ans = n, curr = 0;
        for (var x : diff) {
            curr += x;
            if (curr < ans) ans = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minChanges(new int[]{7,0,3,7,3,10,2,11,7,8,7,5}, 14) == 5;
    }

}
