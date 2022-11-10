package offer2.p35;

import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差
 *
 * https://leetcode.cn/problems/569nqc/
 *
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 */

public class Solution {

    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] times = new int[n];
        for (int i = 0; i < times.length; i++) {
            times[i] = parseTime(timePoints.get(i));
        }
        Arrays.sort(times);

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, times[i] - times[i - 1]);
        }
        ans = Math.min(ans, times[0] + 1440 - times[n - 1]);
        return ans;
    }

    private int parseTime(String time) {
        int h = Integer.parseInt(time, 0, 2, 10);
        int m = Integer.parseInt(time, 3, 5, 10);
        return h * 60 + m;
    }

    public static void main(String[] args) {
        assert new Solution().findMinDifference(List.of("23:59","00:00")) == 1;
        assert new Solution().findMinDifference(List.of("00:00","23:59","00:00")) == 0;
    }

}
