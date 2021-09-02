package problem.p1304findnuniqueintegerssumuptozero;

import java.util.Arrays;

/**
 * 1304. Find N Unique Integers Sum up to Zero
 *
 * https://leetcode-cn.com/problems/find-n-unique-integers-sum-up-to-zero/
 *
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 */

public class Solution {

    public int[] sumZero(int n) {
        if (n == 2) return new int[]{-1, 1};

        int sum = 0;
        int[] ans = new int[n];
        for (int i = 1, e = n - 1; i < e; i++) {
            ans[i] = i; sum += i;
        }
        ans[n - 1] = -sum;

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sumZero(1)));
        System.out.println(Arrays.toString(new Solution().sumZero(2)));
        System.out.println(Arrays.toString(new Solution().sumZero(3)));
        System.out.println(Arrays.toString(new Solution().sumZero(4)));
        System.out.println(Arrays.toString(new Solution().sumZero(5)));
        System.out.println(Arrays.toString(new Solution().sumZero(6)));
    }

}
