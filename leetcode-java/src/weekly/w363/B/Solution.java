package weekly.w363.B;

import java.util.List;

/**
 * 2860. Happy Students
 *
 * https://leetcode.cn/contest/weekly-contest-363/problems/happy-students/
 *
 * You are given a 0-indexed integer array nums of length n where n is the total number of students
 * in the class. The class teacher tries to select a group of students so that all the students remain happy.
 *
 * The ith student will become happy if one of these two conditions is met:
 *
 * The student is selected and the total number of selected students is strictly greater than nums[i].
 * The student is not selected and the total number of selected students is strictly less than nums[i].
 * Return the number of ways to select a group of students so that everyone remains happy.
 */

public class Solution {

    public int countWays(List<Integer> nums) {
        int ans = 0, n = nums.size();
        nums.sort(Integer::compare);

        if (nums.get(n - 1) < n) ans++;
        if (nums.get(0) > 0) ans++;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < (i + 1) && nums.get(i + 1) > (i + 1)) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countWays(List.of(1, 1)) == 2;
        assert new Solution().countWays(List.of(6,0,3,3,6,7,2,7)) == 3;
    }

}
