package daily.d230420p1187makearraystrictlyincreasing;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1187. Make Array Strictly Increasing
 *
 * https://leetcode.cn/problems/make-array-strictly-increasing/
 *
 * Given two integer arrays arr1 and arr2, return the minimum number of
 * operations (possibly zero) needed to make arr1 strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length
 * and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 */

public class Solution {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (var v : arr2) set.add(v);

        final int INF = 0x3f3f3f3f;
        int m = arr1.length, n = set.size();

        int[][] dp = new int[m + 1][Math.min(m, n) + 1];
        for (var r : dp) Arrays.fill(r, INF); dp[0][0] = -1;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= Math.min(i, n); j++) {
                // 当前值已经大于前一个值了
                if (arr1[i - 1] > dp[i - 1][j]) dp[i][j] = arr1[i - 1];
                // 找到严格大于前一个值的最小元素
                if (j > 0 && dp[i - 1][j - 1] != INF) {
                    Integer found = set.higher(dp[i - 1][j - 1]);
                    if (found != null) dp[i][j] = Math.min(dp[i][j], found);
                }
                if (i == m && dp[i][j] != INF) return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,3,2,4}) == 1;
        assert new Solution().makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{4,3,1}) == 2;
        assert new Solution().makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,6,3,3}) == -1;
    }

}
