package weekly.w492.D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
