package lcp.p66;

/**
 * 66. 最小展台数量
 *
 * https://leetcode.cn/problems/600YaG/
 *
 * 力扣嘉年华将举办一系列展览活动，后勤部将负责为每场展览提供所需要的展台。
 * 已知后勤部得到了一份需求清单，记录了近期展览所需要的展台类型， demand[i][j] 表示第 i 天展览时第 j 个展台的类型。
 * 在满足每一天展台需求的基础上，请返回后勤部需要准备的 最小 展台数量。
 *
 * 注意：
 * 同一展台在不同天中可以重复使用。
 */

public class Solution {

    public int minNumBooths(String[] demand) {
        int[] max = new int[128];
        for (var item : demand) {
            int[] curr = new int[128];
            for (var c : item.toCharArray()) curr[c]++;
            for (int i = 0; i < max.length; i++) {
                max[i] = Math.max(max[i], curr[i]);
            }
        }

        int ans = 0;
        for (var v : max) ans += v;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minNumBooths(new String[]{"acd","bed","accd"}) == 6;
        assert new Solution().minNumBooths(new String[]{"abc","ab","ac","b"}) == 3;
    }

}
