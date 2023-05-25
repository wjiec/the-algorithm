package problem.p1931paintingagridwiththreedifferentcolors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1931. Painting a Grid With Three Different Colors
 *
 * https://leetcode.cn/problems/painting-a-grid-with-three-different-colors/
 *
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white.
 * You can paint each cell red, green, or blue. All cells must be painted.
 *
 * Return the number of ways to color the grid with no two adjacent cells having the same color.
 * Since the answer can be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final static int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        int stateLen = (int) Math.pow(3, m);
        // {mask: colors}
        Map<Integer, int[]> states = new HashMap<>();
        for (int state = 0; state < stateLen; state++) {
            boolean ok = true;
            int[] color = new int[m];
            for (int i = 0, x = state; i < m; i++, x /= 3) {
                color[i] = x % 3;
                if (i > 0 && color[i] == color[i - 1]) {
                    ok = false; break;
                }
            }
            if (ok) states.put(state, color);
        }

        // 预处理，分析所有可能相邻的二元组，将该信息放置在 map 中
        // key 值为 mask 值，value 为可与该 mask 相邻的 mask 值列表
        Map<Integer, List<Integer>> tuples = new HashMap<>();
        for(var a : states.keySet()){
            List<Integer> tuple = new ArrayList<>();
            for(Integer b : states.keySet()) {
                boolean ok = true;
                for (int i = 0; i < m; ++i){
                    if(states.get(a)[i] == states.get(b)[i]){
                        ok = false; break;
                    }
                }
                if (ok) tuple.add(b);
            }
            tuples.put(a, tuple);
        }

        // 由于 dp[i][mask] 仅与 dp[i-1][mask'] 有关，因此，可以使用一维数组实现 dp
        int[] dp = new int[stateLen];
        for (Integer state : states.keySet()) dp[state] = 1;
        for (int i = 1; i < n; ++i){
            int[] curr = new int[stateLen];
            for (var mask : states.keySet()){
                for (var index : tuples.get(mask)){
                    curr[mask] += dp[index];
                    curr[mask] %= MOD;
                }
            }
            dp = curr;
        }

        int ans = 0;
        for(Integer mask : states.keySet()) ans = (ans + dp[mask]) % MOD;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().colorTheGrid(1, 1000) == 32634808;
        assert new Solution().colorTheGrid(1, 2) == 6;

        assert new Solution().colorTheGrid(1, 1) == 3;
        assert new Solution().colorTheGrid(2, 2) == 18;
        assert new Solution().colorTheGrid(3, 3) == 246;
        assert new Solution().colorTheGrid(4, 4) == 7812;
        assert new Solution().colorTheGrid(5, 5) == 580986;
    }

}
