package daily.d210701lcpp7chuandixinxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LCP 07. 传递信息
 *
 * https://leetcode-cn.com/problems/chuan-di-xin-xi/
 *
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
 * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 */

public class Solution {

    private final Map<Integer, List<Integer>> map = new HashMap<>();

    public int numWays(int n, int[][] relation, int k) {
        for (var rel : relation) {
            map.putIfAbsent(rel[0], new ArrayList<>());
            map.get(rel[0]).add(rel[1]);
        }
        return doWalkMap(0, 0, n, k);
    }

    public int doWalkMap(int current, int step, int n, int k) {
        if (step >= k) return current == n - 1 ? 1 : 0;

        int ans = 0;
        if (map.containsKey(current)) {
            for (var next : map.get(current)) {
                ans += doWalkMap(next, step + 1, n, k);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numWays(3, new int[][]{
            {0,1}, {0,2}, {2,1}, {1,2}, {1,0}, {2,0}
        }, 5) == 11;

        assert new Solution().numWays(5, new int[][]{
            {0,2}, {2,1}, {3,4}, {2,3}, {1,4}, {2,0}, {0,4}
        }, 3) == 3;

        assert new Solution().numWays(3, new int[][]{
            {0,2}, {2,1}
        }, 2) == 0;
    }

}
