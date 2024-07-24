package problem.p1964findthelongestvalidobstaclecourseateachposition;

import common.Checker;

public class Solution {

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] ans = new int[obstacles.length];
        int[] dp = new int[obstacles.length + 1];
        for (int i = 0, dpSize = 0; i < obstacles.length; i++) {
            int curr = obstacles[i];
            // 寻找第一个大于 curr 的位置
            int l = 0, r = dpSize;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (dp[mid] <= curr) l = mid + 1;
                else r = mid;
            }

            if (l == dpSize) dp[dpSize++] = curr;
            else dp[l] = curr;

            ans[i] = l + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestObstacleCourseAtEachPosition(new int[]{
            5,1,5,5,1,3,4,5,1,4
        }), new int[]{1,1,2,3,2,3,4,5,3,5});

        assert Checker.check(new Solution().longestObstacleCourseAtEachPosition(new int[]{1,2,3,2}), new int[]{1,2,3,3});
        assert Checker.check(new Solution().longestObstacleCourseAtEachPosition(new int[]{2,2,1}), new int[]{1,2,1});
        assert Checker.check(new Solution().longestObstacleCourseAtEachPosition(new int[]{3,1,5,6,4,2}), new int[]{1,1,2,3,2,2});
    }

}
