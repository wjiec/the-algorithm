package problem.p2037minimumnumberofmovestoseateveryone;

import java.util.Arrays;

/**
 * 2037. Minimum Number of Moves to Seat Everyone
 *
 * https://leetcode-cn.com/problems/minimum-number-of-moves-to-seat-everyone/
 *
 * There are n seats and n students in a room. You are given an array seats of length n,
 * where seats[i] is the position of the ith seat.
 *
 * You are also given the array students of length n, where students[j] is the position of the jth student.
 *
 * You may perform the following move any number of times:
 *
 * Increase or decrease the position of the ith student by 1
 * (i.e., moving the ith student from position x to x + 1 or x - 1)
 *
 * Return the minimum number of moves required to move each student to a seat
 * such that no two students are in the same seat.
 *
 * Note that there may be multiple seats or students in the same position at the beginning.
 */

public class Solution {

    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats); Arrays.sort(students);

        int ans = 0;
        for (int i = 0; i < students.length; i++) {
            ans += Math.abs(students[i] - seats[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMovesToSeat(new int[]{3,1,5}, new int[]{2,7,4}) == 4;
        assert new Solution().minMovesToSeat(new int[]{4,1,5,9}, new int[]{1,3,2,6}) == 7;
        assert new Solution().minMovesToSeat(new int[]{2,2,6,6}, new int[]{1,3,2,6}) == 4;
    }

}
