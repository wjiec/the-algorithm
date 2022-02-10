package daily.d210507p1486xoroperationinanarray;

/**
 * 1486. XOR Operation in an Array
 *
 * https://leetcode-cn.com/problems/xor-operation-in-an-array/
 *
 * Given an integer n and an integer start.
 *
 * Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.
 *
 * Return the bitwise XOR of all elements of nums.
 */

public class Solution {

    public int xorOperation(int n, int start) {
        int rs = start;
        for (int i = 1; i < n; i++) {
            rs ^= start + 2 * i;
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().xorOperation(5, 0) == 8;
        assert new Solution().xorOperation(4, 3) == 8;
        assert new Solution().xorOperation(1, 7) == 7;
        assert new Solution().xorOperation(10, 5) == 2;
    }

}
