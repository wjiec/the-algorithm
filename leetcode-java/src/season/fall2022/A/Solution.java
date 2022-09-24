package season.fall2022.A;

/**
 * 1. 气温变化趋势
 *
 * https://leetcode.cn/contest/season/2022-fall/problems/6CE719/
 *
 * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
 *
 * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
 * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
 * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
 * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。
 * 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
 */

public class Solution {

    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int[] a = new int[temperatureA.length - 1];
        int[] b = new int[temperatureB.length - 1];

        for (int i = 1; i < temperatureA.length; i++) {
            a[i - 1] = Integer.compare(temperatureA[i], temperatureA[i - 1]);
        }

        int ans = 0, curr = 0;
        for (int i = 1; i < temperatureB.length; i++) {
            b[i - 1] = Integer.compare(temperatureB[i], temperatureB[i - 1]);
            if (b[i - 1] == a[i - 1]) curr++;
            else curr = 0;
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().temperatureTrend(new int[]{21,18,18,18,31}, new int[]{34,32,16,16,17}) == 2;
    }

}
