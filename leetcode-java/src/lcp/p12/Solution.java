package lcp.p12;

/**
 * LCP 12. 小张刷题计划
 *
 * https://leetcode.cn/problems/xiao-zhang-shua-ti-ji-hua/
 *
 * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，
 * 编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
 *
 * 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，
 * 通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，
 * 小张每天最多使用一次求助。
 *
 * 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
 */

public class Solution {

    public int minTime(int[] time, int m) {
        int l = 0, r = 0;
        for (var v : time) r += v;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(time, mid, m)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private boolean check(int[] time, int limit, int threshold) {
        int days = 1, curr = 0, max = 0;
        for (int v : time) {
            int next = Math.min(max, v);
            if (next + curr <= limit) {
                curr += next;
                max = Math.max(max, v);
            } else {
                days++;
                curr = 0;
                max = v;
            }
        }
        return days <= threshold;
    }

    public static void main(String[] args) {
        assert new Solution().minTime(new int[]{82,35,6,53,37,75,69,69,53,18}, 4) == 71;
        assert new Solution().minTime(new int[]{1,2,7,4,7,7}, 2) == 7;
        assert new Solution().minTime(new int[]{1,2,3,3,3}, 2) == 3;

        assert new Solution().minTime(new int[]{1,2,3,3}, 2) == 3;
        assert new Solution().minTime(new int[]{999,999,999}, 4) == 0;
    }

}
