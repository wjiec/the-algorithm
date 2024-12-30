package weekly.w429.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 3396. Minimum Number of Operations to Make Elements in Array Distinct
 *
 * https://leetcode.cn/contest/weekly-contest-429/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/
 *
 * You are given an integer array nums. You need to ensure that the elements in the array are distinct.
 *
 * To achieve this, you can perform the following operation any number of times:
 *
 * Remove 3 elements from the beginning of the array. If the array has fewer
 * than 3 elements, remove all remaining elements.
 *
 * Note that an empty array is considered to have distinct elements. Return the minimum
 * number of operations needed to make the elements in the array distinct.
 */

public class Solution {

    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (var v : nums) m.merge(v, 1, Integer::sum);

        int ans = 0;
        while (!one(m)) {
            for (int i = 0; i < 3 && ans * 3 + i < nums.length; i++) {
                m.merge(nums[ans * 3 + i], -1, Integer::sum);
            }
            ans++;
        }
        return ans;
    }

    private boolean one(Map<Integer, Integer> m) {
        for (var v : m.values()) if (v > 1) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(new int[]{1,2,3,4,2,3,3,5,7}) == 2;
    }

}
