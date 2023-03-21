package problem.p552studentattendancerecordii;

/**
 * 552. Student Attendance Record II
 *
 * https://leetcode.cn/problems/student-attendance-record-ii/
 *
 * An attendance record for a student can be represented as a string where each character signifies
 * whether the student was absent, late, or present on that day. The record only contains the
 * following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 *
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that
 * make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 */

public class Solution {

    public int checkRecord(int n) {
        int MOD = 1_000_000_007;
        // dp[i][j][k] 表示出勤 i + 1 天有 j 个缺勤且结尾有 k 个迟到的出勤记录数量
        int[][][] dp = new int[n + 1][2][3];
        // 当出勤天数为 0 时, 肯定没有缺勤和迟到
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            // 当前今天缺勤, 只能从前面的非缺勤天转移来
            for (int k = 0; k < 3; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 如果今天到场, 则可以从前一天的任意状态转移, 且迟到清零
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 如果今天迟到, 则只能从之前累计迟到天数不达3天转移来
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans = (ans + dp[n][j][k]) % MOD;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().checkRecord(2) == 8;
        assert new Solution().checkRecord(1) == 3;
        assert new Solution().checkRecord(10101) == 183236316;
    }

}
