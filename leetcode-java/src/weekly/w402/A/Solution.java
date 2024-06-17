package weekly.w402.A;

/**
 * 100304. Count Pairs That Form a Complete Day I
 *
 * https://leetcode.cn/contest/weekly-contest-402/problems/count-pairs-that-form-a-complete-day-i/
 *
 * Given an integer array hours representing times in hours, return an integer denoting
 * the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.
 *
 * A complete day is defined as a time duration that is an exact multiple of 24 hours.
 *
 * For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.
 */

public class Solution {

    public int countCompleteDayPairs(int[] hours) {
        int ans = 0, n = hours.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((hours[i] + hours[j]) % 24 == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
