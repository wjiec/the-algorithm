package problem.p1551minimumoperationstomakearrayequal;

/**
 * 1551. Minimum Operations to Make Array Equal
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-array-equal/
 *
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e., 0 <= i < n).
 *
 * In one operation, you can select two indices x and y where 0 <= x, y < n and subtract 1 from arr[x] and
 * add 1 to arr[y] (i.e., perform arr[x] -=1 and arr[y] += 1). The goal is to make all the elements of
 * the array equal. It is guaranteed that all the elements of the array can be made equal using some operations.
 *
 * Given an integer n, the length of the array, return the minimum number of operations
 * needed to make all the elements of arr equal.
 */

public class Solution {

    public int minOperations(int n) {
        if (n % 2 == 0) {
            // 1 + 3 + 5 + ... + (n - 1)
            // (n/2) * (1+n-1) / 2
            return n * n / 4;
        }

        // 0 + 2 + 4 + ... + (n - 1)
        // (n/2+1) * (0+n-1) / 2
        return (n / 2 + 1) * (n - 1) / 2;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(1) == 0;
        assert new Solution().minOperations(2) == 1;
        assert new Solution().minOperations(3) == 2;
        assert new Solution().minOperations(4) == 4;
        assert new Solution().minOperations(5) == 6;
        assert new Solution().minOperations(6) == 9;
        assert new Solution().minOperations(7) == 12;
        assert new Solution().minOperations(8) == 16;
        assert new Solution().minOperations(9) == 20;
        assert new Solution().minOperations(10) == 25;
    }

}
