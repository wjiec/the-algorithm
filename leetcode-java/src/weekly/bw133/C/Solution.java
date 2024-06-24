package weekly.bw133.C;

/**
 * 3192. Minimum Operations to Make Binary Array Elements Equal to One II
 *
 * https://leetcode.cn/contest/biweekly-contest-133/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/
 *
 * You are given a binary array nums.
 *
 * You can do the following operation on the array any number of times (possibly zero):
 *
 * Choose any index i from the array and flip all the elements from index i to the end of the array.
 * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
 *
 * Return the minimum number of operations required to make all elements in nums equal to 1.
 */

public class Solution {

    public int minOperations(int[] nums) {
        int ans = 0;
        for (var v : nums) {
            if ((v ^ (ans & 1)) == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
