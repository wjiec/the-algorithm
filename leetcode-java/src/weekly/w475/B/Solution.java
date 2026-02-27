package weekly.w475.B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q2. Minimum Distance Between Three Equal Elements II
 *
 * https://leetcode.cn/contest/weekly-contest-475/problems/minimum-distance-between-three-equal-elements-ii/
 *
 * You are given an integer array nums.
 *
 * A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
 *
 * The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
 *
 * Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
 */

public class Solution {

    public int minimumDistance(int[] nums) {
        int ans = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            var l = m.computeIfAbsent(nums[i], k -> new ArrayList<>()); l.add(i);
            if (l.size() < 3) continue;

            int ki = l.get(l.size() - 1), ji = l.get(l.size() - 2), ii = l.get(l.size() - 3);
            ans = Math.min(ans, (ji - ii) + (ki - ji) + (ki - ii));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
