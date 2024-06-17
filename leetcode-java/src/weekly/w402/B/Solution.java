package weekly.w402.B;

/**
 * 100301. Count Pairs That Form a Complete Day II
 *
 * https://leetcode.cn/contest/weekly-contest-402/problems/count-pairs-that-form-a-complete-day-ii/
 *
 * Given an integer array hours representing times in hours, return an integer denoting
 * the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.
 *
 * A complete day is defined as a time duration that is an exact multiple of 24 hours.
 *
 * For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.
 */

public class Solution {

    public long countCompleteDayPairs(int[] hours) {
        long ans = 0;
        int[] mods = new int[25];
        for (var v : hours) {
            ans += mods[v % 24];
            mods[(24 - (v % 24)) % 24]++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
