package problem.p1005maximizesumofarrayafterknegations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1005. Maximize Sum Of Array After K Negations
 *
 * https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
 *
 * Given an array nums of integers, we must modify the array in the following way:
 * we choose an i and replace nums[i] with -nums[i], and we repeat this process k times in total.
 * (We may choose the same index i multiple times.)
 *
 * Return the largest possible sum of the array after modifying it in this way.
 */

public class Solution {

    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; ++i) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                ans += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (freq.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestSumAfterKNegations(new int[]{-4,-2,-3}, 4) == 5;

        assert new Solution().largestSumAfterKNegations(new int[]{-8,3,-5,-3,-5,-2}, 6) == 22;
        assert new Solution().largestSumAfterKNegations(new int[]{4,2,3}, 1) == 5;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,0,2}, 3) == 6;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,0,2}, 2) == 6;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,1,2}, 2) == 5;
        assert new Solution().largestSumAfterKNegations(new int[]{2,-3,-1,5,-4}, 2) == 13;
    }

}
