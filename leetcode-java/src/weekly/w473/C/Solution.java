package weekly.w473.C;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Stable Subarrays With Equal Boundary and Interior Sum
 *
 * https://leetcode.cn/contest/weekly-contest-473/problems/stable-subarrays-with-equal-boundary-and-interior-sum/
 *
 * You are given an integer array capacity.
 *
 * A subarray capacity[l..r] is considered stable if:
 *
 * Its length is at least 3.
 * The first and last elements are each equal to the sum of all
 * elements strictly between them (i.e., capacity[l] = capacity[r] = capacity[l + 1] + capacity[l + 2] + ... + capacity[r - 1]).
 *
 * Return an integer denoting the number of stable subarrays.
 */

public class Solution {

    public long countStableSubarrays(int[] capacity) {
        // 找到满足条件的子数组个数
        //  - 首尾元素相同
        //  - 中间元素之和等于首尾元素
        //
        // 枚举子数组右侧位置 r, 找到位置 l 满足 (cap[l], sum(cap[l+1..r-1])) == (cap[r], cap[r]) 的数量
        long ans = 0, sum = capacity[0];
        Map<Long, Map<Integer, Integer>> m = new HashMap<>();
        for (int r = 1; r < capacity.length; r++) {
            // 当前位置的值是 v, [0..r) 的元素和是 sum
            //  - 我们需要找到左端点 l 使得 sum[r] - sum[l] == v, 也就是 sum[l] = sum[r] - v
            //  - 同时需要满足 sum[l - 1] == v
            int v = capacity[r]; long lSum = sum - v;
            ans += m.getOrDefault(lSum, Collections.emptyMap()).getOrDefault(v, 0);
            // 累加前缀和并记录数据
            m.computeIfAbsent(sum, k -> new HashMap<>()).merge(capacity[r - 1], 1, Integer::sum);
            // 计算前缀和
            sum += v;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countStableSubarrays(new int[]{9,3,3,3,9}) == 2;
        assert new Solution().countStableSubarrays(new int[]{1,2,3,4,5}) == 0;
        assert new Solution().countStableSubarrays(new int[]{-4,4,0,0,-8,-4}) == 1;
    }

}
