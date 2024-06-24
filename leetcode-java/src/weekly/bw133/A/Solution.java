package weekly.bw133.A;

/**
 * 3190. Find Minimum Operations to Make All Elements Divisible by Three
 *
 * https://leetcode.cn/contest/biweekly-contest-133/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/
 *
 * You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.
 *
 * Return the minimum number of operations to make all elements of nums divisible by 3.
 */

public class Solution {

    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (var v : nums) {
            switch (v % 3) {
                case 1, 2 -> ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
