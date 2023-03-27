package weekly.w337.C;

import java.util.Arrays;

/**
 * 2597. The Number of Beautiful Subsets
 *
 * https://leetcode.cn/contest/weekly-contest-337/problems/the-number-of-beautiful-subsets/
 *
 * You are given an array nums of positive integers and a positive integer k.
 *
 * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
 *
 * Return the number of non-empty beautiful subsets of the array nums.
 *
 * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums.
 * Two subsets are different if and only if the chosen indices to delete are different.
 */

public class Solution {

    public int beautifulSubsets(int[] nums, int k) {
        int ans = 0, e = 1 << nums.length;
        for (int i = 1; i < e; i++) {
            if (!collision(nums, i, k)) {
                ans++;
            }
        }
        return ans;
    }

    private final int N = 1000;
    private final byte[] marks = new byte[N + 1];

    private boolean collision(int[] nums, int state, int k) {
        Arrays.fill(marks, (byte) 0);
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if ((state & (1 << i)) != 0) {
                if (marks[curr] == 1) return true;
                if (curr - k >= 0 && marks[curr - k] == 2) return true;
                if (curr + k <= N && marks[curr + k] == 2) return true;

                marks[curr] = 2;
                if (curr - k >= 0) marks[curr - k] = 1;
                if (curr + k <= N) marks[curr + k] = 1;
            }
        }
        return false;
    }

    private static class Backtrack {
        public int beautifulSubsets(int[] nums, int k) {
            dfs(nums, 0, new int[1001 + 2 * k], k);
            return ans;
        }

        private int ans = -1;

        private void dfs(int[] nums, int i, int[] marks, int k) {
            if (i == nums.length) { ans++; return; }

            dfs(nums, i + 1, marks, k); // 不选择当前元素

            int curr = nums[i] + k;
            // 可以选择当前元素
            if (marks[curr - k] == 0 && marks[curr + k] == 0) {
                marks[curr]++;
                dfs(nums, i + 1, marks, k);
                marks[curr]--;
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().beautifulSubsets(new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        }, 1) == 1048575;
        assert new Solution().beautifulSubsets(new int[]{2, 4, 6}, 2) == 4;
        assert new Solution().beautifulSubsets(new int[]{1}, 1) == 1;

        assert new Backtrack().beautifulSubsets(new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        }, 1) == 1048575;
        assert new Backtrack().beautifulSubsets(new int[]{2, 4, 6}, 2) == 4;
        assert new Backtrack().beautifulSubsets(new int[]{1}, 1) == 1;
    }

}
