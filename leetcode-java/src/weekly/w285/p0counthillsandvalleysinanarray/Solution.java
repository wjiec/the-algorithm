package weekly.w285.p0counthillsandvalleysinanarray;

import java.util.ArrayList;
import java.util.List;

/**
 * 6027. Count Hills and Valleys in an Array
 *
 * https://leetcode-cn.com/contest/weekly-contest-285/problems/count-hills-and-valleys-in-an-array/
 *
 * You are given a 0-indexed integer array nums. An index i is part of a hill in nums if
 * the closest non-equal neighbors of i are smaller than nums[i].
 * Similarly, an index i is part of a valley in nums if the closest non-equal neighbors of i are larger than nums[i].
 * Adjacent indices i and j are part of the same hill or valley if nums[i] == nums[j].
 *
 * Note that for an index to be part of a hill or valley, it must have a non-equal neighbor
 * on both the left and right of the index.
 *
 * Return the number of hills and valleys in nums.
 */

public class Solution {

    public int countHillValley(int[] nums) {
        List<Integer> uniques = new ArrayList<>();
        for (var n : nums) if (uniques.isEmpty() || n != uniques.get(uniques.size() - 1)) uniques.add(n);

        int ans = 0;
        for (int i = 1, n = uniques.size(); i < n - 1; i++) {
            if (uniques.get(i) > uniques.get(i - 1) && uniques.get(i) > uniques.get(i + 1)) {
                ans++;
            }

            if (uniques.get(i) < uniques.get(i - 1) && uniques.get(i) < uniques.get(i + 1)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countHillValley(new int[]{2,4,1,1,6,5}) == 3;
        assert new Solution().countHillValley(new int[]{6,6,5,5,4,1}) == 0;
    }

}
