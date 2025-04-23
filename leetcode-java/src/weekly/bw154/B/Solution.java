package weekly.bw154.B;

/**
 * 3513. Number of Unique XOR Triplets I
 *
 * https://leetcode.cn/contest/biweekly-contest-154/problems/number-of-unique-xor-triplets-i/
 *
 * You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [1, n].
 *
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
 *
 * Return the number of unique XOR triplet values from all possible triplets (i, j, k).
 */

public class Solution {

    // nums 是 [1, n] 的排列
    public int uniqueXorTriplets(int[] nums) {
        // a_i ^ a_j ^ a_k 且 i <= j <= k
        //  - 在 i == j == k 的情况下, 得到的异或值 = a_i
        //  - 需要额外判断是否能得到 0
        //      - 只有有 3 个元素以上, 1 ^ 2 ^ 3 = 0
        //  - 可以做到使得最高有效位及其后续的位全 1
        if (nums.length < 3) return nums.length;
        return Integer.highestOneBit(nums.length) << 1;
    }

    public static void main(String[] args) {
        assert new Solution().uniqueXorTriplets(new int[]{1, 2}) == 2;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 3}) == 4;
        assert new Solution().uniqueXorTriplets(new int[]{3, 1, 2}) == 4;
        assert new Solution().uniqueXorTriplets(new int[]{3, 1, 2, 4}) == 8;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 4, 3}) == 8;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 4, 3, 5}) == 8;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 4, 3, 5, 6}) == 8;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 4, 3, 5, 6, 7}) == 8;
        assert new Solution().uniqueXorTriplets(new int[]{1, 2, 4, 3, 5, 6, 7, 8}) == 16;
    }

}
