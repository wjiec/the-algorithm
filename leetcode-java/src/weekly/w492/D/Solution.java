package weekly.w492.D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Minimum Cost to Partition a Binary String
 *
 * https://leetcode.cn/contest/weekly-contest-492/problems/minimum-cost-to-partition-a-binary-string/
 *
 * You are given a binary string s and two integers encCost and flatCost.
 *
 * For each index i, s[i] = '1' indicates that the ith element is sensitive, and s[i] = '0' indicates that it is not.
 *
 * The string must be partitioned into segments. Initially, the entire string forms a single segment.
 *
 * For a segment of length L containing X sensitive elements:
 *
 * If X = 0, the cost is flatCost.
 * If X > 0, the cost is L * X * encCost.
 * If a segment has even length, you may split it into two contiguous segments of equal length and
 * the cost of this split is the sum of costs of the resulting segments.
 *
 * Return an integer denoting the minimum possible total cost over all valid partitions.
 */

public class Solution {

    public long minCost(String s, int encCost, int flatCost) {
        char[] chars = s.toCharArray(); int n = s.length();
        // 计算前缀和用于快速得到分段的长度
        int[] sum = new int[n + 1]; memo = new Map[n];
        Arrays.setAll(memo, i -> new HashMap<>());
        for (int i = 0; i < chars.length; i++) sum[i + 1] = sum[i] + (chars[i] & 1);
        return minCost(sum, 0, n, encCost, flatCost);
    }

    private Map<Integer, Long>[] memo;

    public long minCost(int[] sum, int l, int r, long enc, long flat) {
        long ones = sum[r] - sum[l];
        // 如果全是 0 的话, 不分段是 flat, 分段会变成 2 * flat 不划算
        if (ones == 0) return flat;
        if (memo[l].containsKey(r)) return memo[l].get(r);

        // 如果长度为偶数, 那么我们可以考虑分段
        int len = r - l; long ans = len * ones * enc;
        if ((len & 1) == 0) {
            int mid = l + (len >> 1);
            ans = Math.min(ans, minCost(sum, l, mid, enc, flat) + minCost(sum, mid, r, enc, flat));
        }
        memo[l].put(r, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCost("1010", 2, 1) == 6;
        assert new Solution().minCost("1010", 3, 10) == 12;
        assert new Solution().minCost("00", 1, 2) == 2;
    }

}
