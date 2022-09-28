package daily.d220928getkthmagicnumberlcci;

import java.util.TreeSet;

/**
 * 17.09. Get Kth Magic Number LCCI
 *
 * https://leetcode.cn/problems/get-kth-magic-number-lcci/
 *
 * Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7.
 *
 * Note that 3, 5, and 7 do not have to be factors, but it should not have any other prime factors.
 * For example, the first several multiples would be (in order) 1, 3, 5, 7, 9, 15, 21.
 */

public class Solution {

    public int getKthMagicNumber(int k) {
        TreeSet<Long> ts = new TreeSet<>();
        ts.add(1L);

        long last = 0;
        for (; k > 0; k--) {
            last = ts.pollFirst();
            ts.add(last * 3);
            ts.add(last * 5);
            ts.add(last * 7);
        }
        return (int) last;
    }

    private static class DynamicProgramming {
        public int getKthMagicNumber(int k) {
            int[] dp = new int[k + 1]; dp[1] = 1;
            // p3, p5, p7 表示当前质因数应该乘以第几个值
            int p3 = 1, p5 = 1, p7 = 1;
            for (int i = 2; i <= k; i++) {
                int n3 = dp[p3] * 3, n5 = dp[p5] * 5, n7 = dp[p7] * 7;
                dp[i] = Math.min(n3, Math.min(n5, n7));
                if (dp[i] == n3) p3++;
                if (dp[i] == n5) p5++;
                if (dp[i] == n7) p7++;
            }
            return dp[k];
        }
    }

    public static void main(String[] args) {
        assert new Solution().getKthMagicNumber(5) == 9;
    }

}
