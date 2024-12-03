package weekly.w425.A;

import java.util.List;
import java.util.TreeMap;

/**
 * 3364. Minimum Positive Sum Subarray
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/minimum-positive-sum-subarray/
 *
 * You are given an integer array nums and two integers l and r. Your task is to find the
 * minimum sum of a subarray whose size is between l and r (inclusive) and whose sum is greater than 0.
 *
 * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = Integer.MAX_VALUE;
        for (int sz = l; sz <= r; sz++) {
            int curr = 0;
            for (int i = 0; i < nums.size(); i++) {
                curr += nums.get(i);
                if (i >= sz) curr -= nums.get(i - sz);
                if (i + 1 >= sz && curr > 0) ans = Math.min(ans, curr);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static class Optimization {
        public int minimumSumSubarray(List<Integer> nums, int l, int r) {
            // 使用前缀可以 O(1) 得到长度为 n 的子数组和, 要使得 sum[j + 1] - sum[i] 的值最小即
            //  - 枚举位置 j, 找到左侧满足子数组长度为 [l, r] 的前缀和 sum[i] < sum[j + 1] 的最大值
            int[] sum = new int[nums.size() + 1];
            for (int i = 1; i <= nums.size(); i++) sum[i] = sum[i - 1] + nums.get(i - 1);

            // 枚举右, 维护左
            int ans = Integer.MAX_VALUE;
            TreeMap<Integer, Integer> m = new TreeMap<>();
            for (int i = l; i <= nums.size(); i++) {
                m.merge(sum[i - l], 1, Integer::sum);
                if (m.lowerKey(sum[i]) != null) ans = Math.min(ans, sum[i] - m.lowerKey(sum[i]));
                if (i >= r) m.merge(sum[i - r], -1, (a, b) -> a + b <= 0 ? null : (a + b));
            }

            return ans >= ((int) 1e9) ? -1 : ans;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().minimumSumSubarray(List.of(-2, 2, -3, 1), 2, 3) == -1;
        assert new Optimization().minimumSumSubarray(List.of(1,2,3,4), 2, 3) == 3;

        assert new Solution().minimumSumSubarray(List.of(1,2,3,4), 2, 3) == 3;
    }

}
