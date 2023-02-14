package problem.p1220countvowelspermutation;

/**
 * 1220. Count Vowels Permutation
 *
 * https://leetcode.cn/problems/count-vowels-permutation/
 *
 * Given an integer n, your task is to count how many strings of length n
 * can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */

public class Solution {

    public int countVowelPermutation(int n) {
        final int MOD = 1_000_000_007;
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 1; j < n; j++) {
            long na = (e + i + u) % MOD;
            long ne = (a + i) % MOD;
            long ni = (e + o) % MOD;
            long no = (i) % MOD;
            long nu = (i + o) % MOD;

            a = na; e = ne; i = ni; o = no; u = nu;
        }
        return (int) ((a + e + i + o + u) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().countVowelPermutation(1) == 5;
        assert new Solution().countVowelPermutation(2) == 10;
        assert new Solution().countVowelPermutation(5) == 68;
    }

}
