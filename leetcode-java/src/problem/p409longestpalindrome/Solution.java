package problem.p409longestpalindrome;

/**
 * 409. Longest Palindrome
 *
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 */

public class Solution {

    public int longestPalindrome(String s) {
        int[] chars = new int[255];
        for (var c : s.toCharArray()) {
            chars[c]++;
        }

        int rs = 0;
        for (int i = 0; i < 255; i++) {
            rs += chars[i] - chars[i] % 2;
        }

        return s.length() > rs ? (rs + 1) : rs;
    }

    public static void main(String[] args) {
        assert new Solution().longestPalindrome("abccccdd") == 7;
    }

}
