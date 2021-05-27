package problem.p551studentattendancerecordi;

/**
 * 551. Student Attendance Record I
 *
 * https://leetcode-cn.com/problems/student-attendance-record-i/
 *
 * You are given a string s representing an attendance record for a student where each
 * character signifies whether the student was absent, late, or present on that day.
 * The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 *
 * The student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 *
 * Return true if the student is eligible for an attendance award, or false otherwise.
 */

public class Solution {

    public boolean checkRecord(String s) {
        int sz = s.length(), l = 0, a = 0;
        for (int i = 0; i < sz; i++) {
            switch (s.charAt(i)) {
                case 'L':
                    if (++l > 2) {
                        return false;
                    }
                    break;
                case 'A':
                    if (++a > 1) {
                        return false;
                    }
                    // no break
                default:
                    l = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().checkRecord("PPALLP");
        assert !new Solution().checkRecord("PPALLL");
    }

}
