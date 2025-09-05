package weekly.w461.C;

import java.util.TreeSet;

/**
 * Q3. Minimum Time to Activate String
 *
 * https://leetcode.cn/contest/weekly-contest-461/problems/minimum-time-to-activate-string/
 *
 * You are given a string s of length n and an integer array order, where order is a
 * permutation of the numbers in the range [0, n - 1].
 *
 * Starting from time t = 0, replace the character at index order[t] in s with '*' at each time step.
 *
 * A substring is valid if it contains at least one '*'.
 *
 * A string is active if the total number of valid substrings is greater than or equal to k.
 *
 * Return the minimum time t at which the string s becomes active. If it is impossible, return -1.
 */

public class Solution {

    // 每次修改一个字符, 计算修改后所有包含 * 的子字符串的数量是否大于等于 k
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        // 当字符串全变成 * 时, 任意一个子字符串都是激活状态的
        //  - 从任意位置 i 开始到达 [i, n) 都是符合条件
        //  - 也就是 n, n - 1, n - 2, ..., 1 的等差数列, 总数为 n * (1 + n) / 2
        if (n * (1L + n) / 2 < k) return -1;

        // 当字符串为 *......* 的时候, 首尾的 * 位置分别为 l 和 r, 我们往其中一个位置 i 插入一个 *, 数量的变化
        //  - 任何包含 l, r 的所有子字符串都是已经统计过的
        //  - 所以左边可以从 (l, i] 中任选一个位置, 右边可以从 [i, r) 中任选一个位置, 两边互不影响应使用乘法原理
        //  - 所以会新增 (i - l) * (r - i) 个活跃字符串
        TreeSet<Integer> seen = new TreeSet<>(); seen.add(-1); seen.add(n);
        for (int j = 0; j < n; j++) {
            int i = order[j], l = seen.lower(i), r = seen.higher(i);
            if ((k -= (i - l) * (r - i)) <= 0) return j;
            seen.add(i);
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().minTime("gkb", new int[]{1,2,0}, 4) == 0;
        assert new Solution().minTime("hgbj", new int[]{1,0,2,3}, 8) == 2;
        assert new Solution().minTime("uvju", new int[]{2,3,1,0}, 7) == 1;
        assert new Solution().minTime("enf", new int[]{1,2,0}, 5) == 1;

        assert new Solution().minTime("abc", new int[]{1,0,2}, 2) == 0;
        assert new Solution().minTime("cat", new int[]{0,2,1}, 6) == 2;
        assert new Solution().minTime("xy", new int[]{0,1}, 4) == -1;
    }

}
