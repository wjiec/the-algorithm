package weekly.w283.p1appendkintegerswithminimalsum;

import java.util.Arrays;

/**
 * 6017. Append K Integers With Minimal Sum
 *
 * https://leetcode-cn.com/contest/weekly-contest-283/problems/append-k-integers-with-minimal-sum/
 *
 * You are given an integer array nums and an integer k. Append k unique positive integers
 * that do not appear in nums to nums such that the resulting total sum is minimum.
 *
 * Return the sum of the k integers appended to nums.
 */

public class Solution {

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        long ans = 0;
        if (nums[0] != 1) {
            long start = 1, end = Math.min(nums[0] - 1, start + k - 1);
            ans += (end - start + 1) * (start + end) / 2;
            k -= end - start + 1;
            System.out.printf("add %d - %d, sum = %d, remains = %d\n", start, end, ans, k);
        }

        for (int i = 1; i < nums.length && k > 0; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                long start = nums[i - 1] + 1, end = Math.min(nums[i] - 1, start + k - 1);
                if (start == end) {
                    ans += start;
                    k -= 1;
                } else {
                    ans += (end - start + 1) * (start + end) / 2;
                    k -= end - start + 1;
                }
                System.out.printf("add %d - %d, sum = %d, remains = %d\n", start, end, ans, k);
            }
        }

        if (k > 0) {
            long start = nums[nums.length - 1] + 1, end = start + k - 1;
            ans += (end - start + 1) * (start + end) / 2;
            System.out.printf("add %d - %d, sum = %d, remains = %d\n", start, end, ans, k);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimalKSum(new int[]{96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84}, 35) == 794;
        assert new Solution().minimalKSum(new int[]{53,41,90,33,84,26,50,32,63,47,66,43,29,88,71,28,83}, 76) == 3444;
        assert new Solution().minimalKSum(new int[]{1,4,25,10,25}, 2) == 5;
        assert new Solution().minimalKSum(new int[]{5,6}, 6) == 25;
    }

}
