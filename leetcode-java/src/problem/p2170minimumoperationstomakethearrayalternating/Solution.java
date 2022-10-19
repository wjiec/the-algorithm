package problem.p2170minimumoperationstomakethearrayalternating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2170. Minimum Operations to Make the Array Alternating
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating/
 *
 * You are given a 0-indexed array nums consisting of n positive integers.
 *
 * The array nums is called alternating if:
 *
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 *
 * Return the minimum number of operations required to make the array alternating.
 */

public class Solution {

    public int minimumOperations(int[] nums) {
        if (nums.length == 1) return 0;

        Map<Integer, Integer> even = new HashMap<>();
        for (int i = 0; i < nums.length; i += 2) {
            even.merge(nums[i], 1, Integer::sum);
        }

        Map<Integer, Integer> odd = new HashMap<>();
        for (int i = 1; i < nums.length; i += 2) {
            odd.merge(nums[i], 1, Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> sEven = new ArrayList<>(even.entrySet());
        sEven.sort((a, b) -> b.getValue() - a.getValue());

        List<Map.Entry<Integer, Integer>> sOdd = new ArrayList<>(odd.entrySet());
        sOdd.sort((a, b) -> b.getValue() - a.getValue());

        int o1Key = sOdd.get(0).getKey(), o1Value = sOdd.get(0).getValue();
        int e1Key = sEven.get(0).getKey(), e1Value = sEven.get(0).getValue();

        if (o1Key != e1Key) return nums.length - (o1Value + e1Value);

        int o2Value = 0, e2Value = 0;
        if (sOdd.size() > 1) o2Value = sOdd.get(1).getValue();
        if (sEven.size() > 1) e2Value = sEven.get(1).getValue();

        return nums.length - Math.max(e1Value + o2Value, e2Value + o1Value);
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(new int[]{3,1,3,2,4,3}) == 3;
        assert new Solution().minimumOperations(new int[]{1,2,2,2,2}) == 2;
    }

}
