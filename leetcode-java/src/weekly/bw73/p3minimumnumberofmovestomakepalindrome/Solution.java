package weekly.bw73.p3minimumnumberofmovestomakepalindrome;

/**
 * 5237. Minimum Number of Moves to Make Palindrome
 *
 * https://leetcode-cn.com/contest/biweekly-contest-73/problems/minimum-number-of-moves-to-make-palindrome/
 *
 * You are given a string s consisting only of lowercase English letters.
 *
 * In one move, you can select any two adjacent characters of s and swap them.
 *
 * Return the minimum number of moves needed to make s a palindrome.
 *
 * Note that the input will be generated such that s can always be converted to a palindrome.
 */

public class Solution {

    public int minMovesToMakePalindrome(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1, ans = 0;
        while (l < r) {
            if (chars[l] == chars[r]) {
                l++; r--;
            } else {
                ans += move(chars, l, r);
            }
        }
        return ans;
    }

    private int move(char[] chars, int l, int r) {
        int a = l + 1, b = r - 1;
        while (a < r && chars[a] != chars[r]) a++;
        while (b > l && chars[b] != chars[l]) b--;

        int lc = a - l, rc = r - b;
        if (lc <= rc) {
            for (; a > l; a--) chars[a] = chars[a - 1];
            chars[l] = chars[r];
        } else {
            for (; b < r; b++) chars[b] = chars[b + 1];
            chars[r] = chars[l];
        }
        return Math.min(lc, rc);
    }

    public static void main(String[] args) {
        assert new Solution().minMovesToMakePalindrome("eqvvhtcsaaqtqesvvqch") == 17;

        assert new Solution().minMovesToMakePalindrome("aabb") == 2;
        // letelt -> lete[tl] -> let[te]l
        assert new Solution().minMovesToMakePalindrome("letelt") == 2;
    }

}
