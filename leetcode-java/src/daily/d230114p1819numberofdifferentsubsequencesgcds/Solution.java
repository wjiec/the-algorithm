package daily.d230114p1819numberofdifferentsubsequencesgcds;

/**
 * 1819. Number of Different Subsequences GCDs
 *
 * https://leetcode.cn/problems/number-of-different-subsequences-gcds/description/
 *
 * You are given an array nums that consists of positive integers.
 *
 * The GCD of a sequence of numbers is defined as the greatest integer that
 * divides all the numbers in the sequence evenly.
 *
 * For example, the GCD of the sequence [4,6,16] is 2.
 * A subsequence of an array is a sequence that can be formed by removing
 * some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * Return the number of different GCDs among all non-empty subsequences of nums.
 */

public class Solution {

    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (var v : nums) max = Math.max(max, v);

        boolean[] visited = new boolean[max + 1];
        for (var v : nums) visited[v] = true;

        int ans = 0;
        for (int i = 1; i <= max; i++) {
            for (int j = 0, g = 0; j <= max; j += i) {
                if (visited[j]) {
                    if (g == 0) g = j;
                    else g = gcd(g, j);

                    if (g == i) { ans++; break; }
                }
            }
        }

        return ans;
    }

    // 最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        assert new Solution().countDifferentSubsequenceGCDs(new int[]{6,10,3}) == 5;
        assert new Solution().countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}) == 7;
    }

}
