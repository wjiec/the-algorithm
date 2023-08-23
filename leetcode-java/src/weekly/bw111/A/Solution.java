package weekly.bw111.A;

import java.util.List;

/**
 * 6954. Count Pairs Whose Sum is Less than Target
 *
 * https://leetcode.cn/contest/biweekly-contest-111/problems/count-pairs-whose-sum-is-less-than-target/
 *
 * Given a 0-indexed integer array nums of length n and an integer target, return
 * the number of pairs (i, j) where 0 <= i < j < n and nums[i] + nums[j] < target.s
 */

public class Solution {

    public int countPairs(List<Integer> nums, int target) {
        int ans = 0, n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
