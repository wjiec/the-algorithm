package lcp.p8;

import common.Checker;

/**
 * LCP 08. 剧情触发时间
 *
 * https://leetcode.cn/problems/ju-qing-hong-fa-shi-jian/
 *
 * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，
 * 分别是文明等级（C），资源储备（R）以及人口数量（H）。
 *
 * 在游戏开始时（第 0 天），三种属性的值均为 0。
 *
 * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。
 * 这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
 *
 * 所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，
 * 对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，
 * 则剧情会被触发。
 *
 * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
 */

public class Solution {

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[][] days = new int[increase.length + 1][3];
        for (int i = 1; i <= increase.length; i++) {
            days[i][0] = days[i - 1][0] + increase[i - 1][0];
            days[i][1] = days[i - 1][1] + increase[i - 1][1];
            days[i][2] = days[i - 1][2] + increase[i - 1][2];
        }

        int[] ans = new int[requirements.length];
        for (int i = 0; i < requirements.length; i++) {
            ans[i] = search(days, 0, 0, requirements[i][0]);
            ans[i] = search(days, ans[i], 1, requirements[i][1]);
            ans[i] = search(days, ans[i], 2, requirements[i][2]);
        }
        return ans;
    }

    private int search(int[][] days, int l, int idx, int target) {
        if (l == -1) return -1;

        int r = days.length, ans = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (days[mid][idx] >= target) {
                ans = mid;
                r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getTriggerTime(new int[][]{
            {2,8,4},{2,5,0},{10,9,8}
        }, new int[][]{
            {2,11,3},{15,10,7},{9,17,12},{8,1,14}
        }), new int[]{2,-1,3,-1});

        assert Checker.check(new Solution().getTriggerTime(new int[][]{
            {0,4,5},{4,8,8},{8,6,1},{10,10,0}
        }, new int[][]{
            {12,11,16},{20,2,6},{9,2,6},{10,18,3},{8,14,9}
        }), new int[]{-1,4,3,3,3});

        assert Checker.check(new Solution().getTriggerTime(new int[][]{
            {1,1,1}
        }, new int[][]{
            {0,0,0}
        }), new int[]{0});
    }

}
