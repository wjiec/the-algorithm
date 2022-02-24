package problem.p266palindromepermutation;

/**
 * 266. Palindrome Permutation
 *
 * https://leetcode-cn.com/problems/palindrome-permutation/
 *
 * Given a string s, return true if a permutation of the string could form a palindrome.
 */

public class Solution {

    public boolean canPermutePalindrome(String s) {
        int[] chars = new int[128];
        for (var c : s.toCharArray()) chars[c]++;

        int singles = 0;
        for (var n : chars) singles += n % 2;
        return singles <= 1;
    }

    public static void main(String[] args) {
        assert !new Solution().canPermutePalindrome("code");
        assert new Solution().canPermutePalindrome("aab");
        assert new Solution().canPermutePalindrome("carerac");
    }

}
