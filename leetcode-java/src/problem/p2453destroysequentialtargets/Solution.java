package problem.p2453destroysequentialtargets;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2453. Destroy Sequential Targets
 *
 * https://leetcode.cn/problems/destroy-sequential-targets/
 *
 * You are given a 0-indexed array nums consisting of positive integers, representing
 * targets on a number line. You are also given an integer space.
 *
 * You have a machine which can destroy targets. Seeding the machine with some nums[i]
 * allows it to destroy all targets with values that can be represented
 * as nums[i] + c * space, where c is any non-negative integer.
 *
 * You want to destroy the maximum number of targets in nums.
 *
 * Return the minimum value of nums[i] you can seed the machine with to
 * destroy the maximum number of targets.
 */

public class Solution {

    public int destroyTargets(int[] nums, int space) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (var v : nums) map.merge(v % space, 1, Integer::sum);

        int maxCount = 0;
        HashSet<Integer> mods = new HashSet<>();
        for (var kv : map.entrySet()) {
            if (kv.getValue() >= maxCount) {
                if (kv.getValue() > maxCount) {
                    mods.clear();
                }
                mods.add(kv.getKey());
                maxCount = kv.getValue();
            }
        }

        int ans = Integer.MAX_VALUE;
        for (var v : nums) {
            if (mods.contains(v % space)) {
                ans = Math.min(ans, v);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().destroyTargets(new int[]{5, 2}, 4) == 2;

        assert new Solution().destroyTargets(new int[]{3,7,8,1,1,5}, 2) == 1;
        assert new Solution().destroyTargets(new int[]{1,3,5,2,4,6}, 2) == 1;
        assert new Solution().destroyTargets(new int[]{6,2,5}, 100) == 2;
    }

}
