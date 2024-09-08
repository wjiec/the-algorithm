package weekly.w413.C;

import java.math.BigInteger;
import java.util.*;

/**
 * 3276. Select Cells in Grid With Maximum Score
 *
 * https://leetcode.cn/contest/weekly-contest-413/problems/select-cells-in-grid-with-maximum-score/
 *
 * You are given a 2D matrix grid consisting of positive integers.
 *
 * You have to select one or more cells from the matrix such that the following conditions are satisfied:
 *
 * No two selected cells are in the same row of the matrix.
 * The values in the set of selected cells are unique.
 * Your score will be the sum of the values of the selected cells.
 *
 * Return the maximum score you can achieve.
 */

public class Solution {

    private final int[] max = new int[15];

    public int maxScore(List<List<Integer>> grid) {
        for (int i = grid.size() - 1; i >= 0; i--) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                ans = Math.max(ans, grid.get(i).get(j));
                max[i] = Math.max(max[i], max[i + 1] + grid.get(i).get(j));
            }
        }

        dfs(grid, 0, new boolean[101], 0);
        return ans;
    }

    private int ans = 0;
    private final Map<Integer, Map<BigInteger, Integer>> memo = new HashMap<>();

    private int dfs(List<List<Integer>> grid, int i, boolean[] seen, int sum) {
        if (i == grid.size()) return 0;

        var key = toKey(seen);
        var map = memo.computeIfAbsent(i, k -> new HashMap<>());
        if (map.containsKey(key)) return map.get(key);
        if (sum + max[i] < ans) return 0;

        int curr = 0;
        if (sum + max[i + 1] > ans) curr = Math.max(curr, dfs(grid, i + 1, seen, sum));
        for (var v : grid.get(i)) {
            // 选择这一行的某个数
            if (!seen[v] && v + max[i + 1] > curr) {
                seen[v] = true;
                curr = Math.max(curr, v + dfs(grid, i + 1, seen, sum + v));
                seen[v] = false;
            }

            // 或者是从这个数开始选
            if (v + max[i + 1] > ans) {
                boolean[] x = new boolean[101]; x[v] = true;
                ans = Math.max(ans, v + dfs(grid, i + 1, x, v));
            }
        }

        map.put(key, curr);
        ans = Math.max(ans, curr);
        return curr;
    }

    private BigInteger toKey(boolean[] seen) {
        BigInteger key = BigInteger.ZERO;
        for (int i = 0; i < seen.length; i++) {
            if (seen[i]) key = key.or(BigInteger.ONE.shiftLeft(i));
        }
        return key;
    }

    @SuppressWarnings({"unchecked", "SequencedCollectionMethodCanBeUsed"})
    private static class Optimization {
        private final Set<Integer>[] numbers = new Set[101];
        { Arrays.setAll(numbers, i -> new HashSet<>()); }

        public int maxScore(List<List<Integer>> grid) {
            int m = grid.size(), n = grid.get(0).size(), max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, grid.get(i).get(j));
                    numbers[grid.get(i).get(j)].add(i);
                }
            }

            return dfs(max, 0);
        }

        private final Map<Integer, Integer> memo = new HashMap<>();

        // 表示当前选或者不选 num, 同时已选择行的压缩状态
        private int dfs(int num, int selected) {
            if (num == 0) return 0;

            int key = (num << 16) | selected;
            if (memo.containsKey(key)) return memo.get(key);

            // 不选当前这个数字
            int ans = dfs(num - 1, selected);
            // 选择使用当前数字, 枚举这个数字出现的行
            for (var idx : numbers[num]) {
                if ((selected & (1 << idx)) == 0) {
                    ans = Math.max(ans, num + dfs(num - 1, selected | (1 << idx)));
                }
            }

            memo.put(key, ans);
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(List.of(
            List.of(11),
            List.of(13),
            List.of(13),
            List.of(19)
        )) == 43;

        assert new Solution().maxScore(List.of(
            List.of(1,2,3),
            List.of(4,3,2),
            List.of(1,1,1)
        )) == 8;

        assert new Solution().maxScore(List.of(
            List.of(8,7,6),
            List.of(8,3,2)
        )) == 15;
        
        assert new Solution().maxScore(List.of(
            List.of(92,11,45,88,38,13,65,85),
            List.of(52,83,3,14,82,51,27,59),
            List.of(65,69,99,27,7,70,39,43),
            List.of(43,46,22,19,75,70,57,50),
            List.of(54,36,91,80,74,43,62,61),
            List.of(35,45,19,32,92,50,93,88),
            List.of(60,15,93,10,89,32,51,11),
            List.of(82,66,42,61,78,94,66,7),
            List.of(75,56,49,78,81,61,79,50)
        )) == 797;
    }

}
