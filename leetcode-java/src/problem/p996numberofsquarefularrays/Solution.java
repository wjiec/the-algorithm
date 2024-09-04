package problem.p996numberofsquarefularrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    private int[] nums = null;
    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    public int numSquarefulPerms(int[] nums) {
        this.nums = nums; int n = nums.length;
        // 如果 nums[i] 和 nums[j] 相加是一个完全平方数, 那么就连接 i 和 j
        for (int i = 0; i < nums.length; i++) {
            g.computeIfAbsent(i, k -> new HashSet<>());
            g.computeIfAbsent(n, k -> new HashSet<>()).add(i);

            for (int j = i + 1; j < nums.length; j++) {
                int sqrt = (int) Math.sqrt(nums[i] + nums[j]);
                if (sqrt * sqrt == nums[i] + nums[j]) {
                    g.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                    g.computeIfAbsent(j, k -> new HashSet<>()).add(i);
                }
            }
        }

        // 查找从 i 出发能遍历所有节点的方案数
        return dfs(n, 0, (1 << n) - 1);
    }

    private int dfs(int curr, int state, int mask) {
        if ((state & mask) == mask) {
            return 1;
        }

        int ans = 0;
        Set<Integer> seen = new HashSet<>();
        for (var next : g.get(curr)) {
            if (seen.contains(nums[next])) continue;

            if ((state & (1 << next)) == 0) {
                ans += dfs(next, state | (1 << next), mask);
                seen.add(nums[next]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSquarefulPerms(new int[]{1,1,8,1,8}) == 1;

        assert new Solution().numSquarefulPerms(new int[]{1,17,8}) == 2;
        assert new Solution().numSquarefulPerms(new int[]{2,2,2}) == 1;
    }

}
