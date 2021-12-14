package lcp.p39a0jQkd0;

/**
 * LCP 39. 无人机方阵
 *
 * https://leetcode-cn.com/problems/0jQkd0/
 *
 * 在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：
 *
 * 调整无人机的位置布局
 * 切换无人机展示的灯光颜色
 * 给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，
 *
 * 请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。
 *
 * 注意： 调整无人机的位置布局时无人机的位置可以随意变动。
 */

public class Solution {

    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int[] map = new int[10001];
        for (var ns : source) for (var n : ns) map[n]++;
        for (var ns : target) for (var n : ns) map[n]--;

        int ans = 0;
        for (var n : map) ans += Math.abs(n);
        return ans / 2;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSwitchingTimes(new int[][]{{1,3}, {5,4}}, new int[][]{{3,1}, {6,5}}) == 1;
        assert new Solution().minimumSwitchingTimes(new int[][]{{1,2,3}, {3,4,5}}, new int[][]{{1,3,5}, {2,3,4}}) == 0;
    }

}
