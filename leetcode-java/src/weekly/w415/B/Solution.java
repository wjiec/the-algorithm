package weekly.w415.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3290. Maximum Multiplication Score
 *
 * https://leetcode.cn/contest/weekly-contest-415/problems/maximum-multiplication-score/
 *
 * You are given an integer array a of size 4 and another integer array b of size at least 4.
 *
 * You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3.
 *
 * Your score will be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].
 *
 * Return the maximum score you can achieve.
 */

public class Solution {

    public long maxScore(int[] a, int[] b) {
        long[][] values = new long[4][b.length];
        for (int i = 0; i < b.length; i++) {
            values[0][i] = (long) a[0] * b[i];
            values[1][i] = (long) a[1] * b[i];
            values[2][i] = (long) a[2] * b[i];
            values[3][i] = (long) a[3] * b[i];
        }

        // 从 values 的每一行中取出一个数相加, 要求所取的值最大
        return maxScore(values, 0, 0, b.length);
    }

    private final Map<Long, Long> memo = new HashMap<>();

    // 从第 i 行以及之后行的 [l, r] 中所能取到最大数值
    private long maxScore(long[][] values, int i, int l, int r) {
        if (i == values.length) return 0;

        long key = ((long) i << 48) | ((long) l << 24) | r;
        if (memo.containsKey(key)) return memo.get(key);

        long ans = Long.MIN_VALUE, mx = Long.MIN_VALUE;
        for (int j = l; j <= r - (4 - i); j++) {
            // 如果值不变大, 后面做的基本都是重复计算, 直接减枝
            if (values[i][j] > mx) {
                mx = values[i][j];
                ans = Math.max(ans, mx + maxScore(values, i + 1, j + 1, r));
            }
        }

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{3,2,5,6}, new int[]{2,-6,4,-5,-3,2,-7}) == 26;
        assert new Solution().maxScore(new int[]{-1,4,5,-2}, new int[]{-5,-1,-3,-2,-4}) == -1;
    }

}
