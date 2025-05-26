package weekly.w448.C;

/**
 * 3538. Merge Operations for Minimum Travel Time
 *
 * https://leetcode.cn/contest/weekly-contest-448/problems/merge-operations-for-minimum-travel-time/
 *
 * You are given a straight road of length l km, an integer n, an integer k, and two integer
 * arrays, position and time, each of length n.
 *
 * The array position lists the positions (in km) of signs in strictly increasing
 * order (with position[0] = 0 and position[n - 1] = l).
 *
 * Each time[i] represents the time (in minutes) required to travel 1 km
 * between position[i] and position[i + 1].
 *
 * You must perform exactly k merge operations. In one merge, you can choose
 * any two adjacent signs at indices i and i + 1 (with i > 0 and i + 1 < n) and:
 *
 * Update the sign at index i + 1 so that its time becomes time[i] + time[i + 1].
 * Remove the sign at index i.
 *
 * Return the minimum total travel time (in minutes) to travel from 0 to l after exactly k merges.
 */

public class Solution {

    // 恰好合并 k 次, 使得总旅行时间最小
    public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
        int[] sum = new int[n];
        for (int i = 0; i < n - 1; i++) sum[i + 1] = sum[i] + time[i];

        memo = new int[n - 1][n - 1][k + 1];
        return dfs(0, 0, k, position, sum);
    }

    private int[][][] memo = null;
    private final static int INF = Integer.MAX_VALUE >> 1;

    // 当前的数组是 [l, r], 剩下的 [r + 1, n) 数组还需要合并 k 次
    public int dfs(int l, int r, int k, int[] pos, int[] sum) {
        // 如果已经到达末尾了且还需要划分则不合法, 否则返回 0 表示是一个合理的划分
        if (r == pos.length - 1) return k > 0 ? INF : 0;
        if (memo[l][r][k] > 0) return memo[l][r][k];

        // 将 [l, r] 合并之后, 会使得从 [l + 1, r) 的时间都叠加到 r 上
        int ans = Integer.MAX_VALUE, st = sum[r + 1] - sum[l];
        // 现在我们是把 [l, r] 范围的数据合并到下一个组里, 枚举下一个组的范围 [r + 1, next]
        //  - [r + 1, next] 共有 next - (r + 1) + 1 个数, 构成了 next - (r + 1) + 1 - 1 个区间
        //      = next - (r + 1) + 1 - 1 = next - r - 1 + 1 - 1 = next - r - 1
        //  - 由于我们只可以合并 k 个, 所以 next - r - 1 <= k, 也就是 next < k + r + 1 + 1
        for (int next = r + 1; next < Math.min(pos.length, k + r + 2); next++) {
            // 合并 [r + 1, next] 个区间, 也就是使用将 [l + 1, r] 中的时间加到 pos[r] 上
            //  - 得到的分数是 (pos[next] - pos[r]) * st
            int curr = dfs(r + 1, next, k - (next - r - 1), pos, sum) + (pos[next] - pos[r]) * st;
            ans = Math.min(ans, curr);
        }

        return memo[l][r][k] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().minTravelTime(10, 4, 1, new int[]{0,3,8,10}, new int[]{5,8,3,6}) == 62;
    }

}
