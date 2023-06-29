package weekly.w351.A;

/**
 * 2748. Number of Beautiful Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-351/problems/number-of-beautiful-pairs/
 *
 * You are given a 0-indexed integer array nums. A pair of indices i, j
 * where 0 <= i < j < nums.length is called beautiful if the first
 * digit of nums[i] and the last digit of nums[j] are coprime.
 *
 * Return the total number of beautiful pairs in nums.
 *
 * Two integers x and y are coprime if there is no integer greater than 1 that
 * divides both of them. In other words, x and y are coprime if gcd(x, y) == 1,
 * where gcd(x, y) is the greatest common divisor of x and y.
 */

public class Solution {

    public int countBeautifulPairs(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(String.valueOf(nums[i]).substring(0, 1));
            for (int j = i + 1; j < n; j++) {
                String s = String.valueOf(nums[j]);
                int y = Integer.parseInt(s.substring(s.length() - 1));
                if (gcd(x, y) == 1) ans++;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
    }

}
