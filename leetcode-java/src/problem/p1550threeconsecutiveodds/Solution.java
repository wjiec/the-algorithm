package problem.p1550threeconsecutiveodds;

/**
 * 1550. Three Consecutive Odds
 *
 * https://leetcode-cn.com/problems/three-consecutive-odds/
 *
 * Given an integer array arr, return true if there are three consecutive odd numbers in the array.
 *
 * Otherwise, return false.
 */

public class Solution {

    public boolean threeConsecutiveOdds(int[] arr) {
        int odd = 0;
        for (var n : arr) {
            if (n % 2 == 1) {
                if (++odd >= 3) return true;
            } else odd = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().threeConsecutiveOdds(new int[]{2,6,4,1});
        assert new Solution().threeConsecutiveOdds(new int[]{1,2,34,3,4,5,7,23,12});
    }

}
