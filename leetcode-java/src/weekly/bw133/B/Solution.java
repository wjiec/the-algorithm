package weekly.bw133.B;

/**
 * 3191. Minimum Operations to Make Binary Array Elements Equal to One I
 *
 * https://leetcode.cn/contest/biweekly-contest-133/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/
 *
 * You are given a binary array nums.
 *
 * You can do the following operation on the array any number of times (possibly zero):
 *
 * Choose any 3 consecutive elements from the array and flip all of them.
 * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
 *
 * Return the minimum number of operations required to make all elements in nums equal to 1.
 * If it is impossible, return -1.
 */

public class Solution {

    public int minOperations(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i <= n - 3; i++) {
            if (nums[i] == 0) {
                ans++;
                nums[i] = nums[i] ^ 1;
                nums[i + 1] = nums[i + 1] ^ 1;
                nums[i + 2] = nums[i + 2] ^ 1;
            }
        }
        return (nums[n - 3] == 0 || nums[n - 2] == 0 || nums[n - 1] == 0) ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
