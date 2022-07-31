package weekly.w304.B;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 6133. Maximum Number of Groups Entering a Competition
 *
 * https://leetcode.cn/contest/weekly-contest-304/problems/maximum-number-of-groups-entering-a-competition/
 *
 * You are given a positive integer array grades which represents the grades of students in a university.
 * You would like to enter all these students into a competition in ordered non-empty groups, such that
 * the ordering meets the following conditions:
 *
 * The sum of the grades of students in the ith group is less than the sum of the grades of students
 * in the (i + 1)th group, for all groups (except the last).
 * The total number of students in the ith group is less than the total number of students
 * in the (i + 1)th group, for all groups (except the last).
 *
 * Return the maximum number of groups that can be formed.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);

        int[] curr = new int[]{0, 0};
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{grades[0], 1});
        for (int i = 1; i < grades.length; i++) {
            curr[0] += grades[i]; curr[1]++;
            if (curr[0] > stack.peek()[0] && curr[1] > stack.peek()[1]) {
                stack.push(curr); curr = new int[]{0, 0};
            }
        }

        if (curr[0] > stack.peek()[0] && curr[1] > stack.peek()[1]) {
            stack.push(curr);
        }

        return stack.size();
    }

    public static void main(String[] args) {
        assert new Solution().maximumGroups(new int[]{10,6,12,7,3,5}) == 3;
        assert new Solution().maximumGroups(new int[]{8,8}) == 1;
    }

}
