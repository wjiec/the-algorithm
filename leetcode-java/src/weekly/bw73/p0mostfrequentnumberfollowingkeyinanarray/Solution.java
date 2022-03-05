package weekly.bw73.p0mostfrequentnumberfollowingkeyinanarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 6024. Most Frequent Number Following Key In an Array
 *
 * https://leetcode-cn.com/contest/biweekly-contest-73/problems/most-frequent-number-following-key-in-an-array/
 *
 * You are given a 0-indexed integer array nums. You are also given an integer key, which is present in nums.
 *
 * For every unique integer target in nums, count the number of times target immediately
 * follows an occurrence of key in nums. In other words, count the number of indices i such that:
 *
 * 0 <= i <= n - 2,
 * nums[i] == key and,
 * nums[i + 1] == target.
 *
 * Return the target with the maximum count. The test cases will be generated
 * such that the target with maximum count is unique.
 */

public class Solution {

    public int mostFrequent(int[] nums, int key) {
        int ans = nums[0], count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i -1] == key) {
                map.merge(nums[i], 1, Integer::sum);
                if (map.get(nums[i]) > count) {
                    ans = nums[i];
                    count = map.get(nums[i]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().mostFrequent(new int[]{1,100,200,1,100}, 1) == 100;
        assert new Solution().mostFrequent(new int[]{2,2,2,2,3}, 2) == 2;
    }

}
