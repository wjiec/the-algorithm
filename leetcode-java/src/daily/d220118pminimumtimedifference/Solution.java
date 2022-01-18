package daily.d220118pminimumtimedifference;

import java.util.Arrays;
import java.util.List;

/**
 * 539. Minimum Time Difference
 *
 * https://leetcode-cn.com/problems/minimum-time-difference/
 *
 * Given a list of 24-hour clock time points in "HH:MM" format,
 * return the minimum minutes difference between any two time-points in the list.
 */

public class Solution {

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) return 0;
        int[] minutes = new int[timePoints.size()];
        for (int i = 0; i < minutes.length; i++) {
            minutes[i] = Integer.valueOf(timePoints.get(i).substring(0, 2), 10) * 60 +
                Integer.valueOf(timePoints.get(i).substring(3, 5), 10);
        }

        Arrays.sort(minutes);

        int ans = minutes[0] + 1440 - minutes[minutes.length - 1];
        for (int i = 1; i < minutes.length; i++) {
            ans = Math.min(ans, minutes[i] - minutes[i - 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinDifference(List.of("23:59","00:00")) == 1;
        assert new Solution().findMinDifference(List.of("00:00","23:59","00:00")) == 0;
    }

}
